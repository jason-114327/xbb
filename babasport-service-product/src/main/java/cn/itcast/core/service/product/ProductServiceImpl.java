package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.*;
import cn.itcast.core.dao.product.ColorDao;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.dao.product.SkuDao;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * 商品管理实现类
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    // 分页对象
    @Override
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {
        StringBuilder params = new StringBuilder();

        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(Pagination.cpn(pageNo));
        productQuery.setOrderByClause("id desc");
        ProductQuery.Criteria criteria = productQuery.createCriteria();
        if(name != null){
            criteria.andNameLike("%"+name+"%");
            params.append("name=").append(name);
        }
        if(brandId != null){
            criteria.andBrandIdEqualTo(brandId);
            params.append("&brandId=").append(brandId);
        }
        if(isShow != null){
            criteria.andIsShowEqualTo(isShow);
            params.append("&isShow=").append(isShow);
        }else{
            criteria.andIsShowEqualTo(false);
            params.append("&isShow=").append(false);
        }
        Pagination pagination = new Pagination(productQuery.getPageNo(),
                productQuery.getPageSize(),
                productDao.countByExample(productQuery),
                productDao.selectByExample(productQuery));

        String url = "/product/list.do";
        pagination.pageView(url,params.toString());
        return pagination;
    }

    //加载颜色
    @Autowired
    private ColorDao colorDao;

    //颜色结果集
    public List<Color> selectColorList(){
        ColorQuery colorQuery = new ColorQuery();
        colorQuery.createCriteria().andParentIdNotEqualTo(0L);
        return colorDao.selectByExample(colorQuery);
    }

    @Autowired
    private SkuDao skuDao;
    @Autowired
    private Jedis jedis;

    //商品保存
    public void insertProduct(Product product) {
        Long pno = jedis.incr("pno");// 获取缓存值且+1
        product.setId(pno);
        //架状态 后台程序写的
        product.setIsShow(false);
        //删除  后台程序写的不删除
        product.setIsDel(true);
        productDao.insertSelective(product);
        //返回ID
        //保存SKU
        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");
        //颜色
        for (String color : colors) {
            for (String size : sizes) {
                //保存SKU
                Sku sku = new Sku();
                //商品ＩＤ
                sku.setProductId(product.getId());
                //颜色
                sku.setColorId(Long.parseLong(color));
                //尺码
                sku.setSize(size);
                //市场价
                sku.setMarketPrice(999f);
                //售价
                sku.setPrice(666f);
                //运费
                sku.setDeliveFee(8f);
                //库存
                sku.setStock(0);
                //限制
                sku.setUpperLimit(200);
                //时间
                sku.setCreateTime(new Date());

                skuDao.insertSelective(sku);

            }
        }
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private SolrServer solrServer;
    //上架（使用了solr）
    public void isShow(Long[] ids){
        Product product = new Product();
        //上架
        product.setIsShow(true);
        for (final Long id : ids) {
            product.setId(id);
            //商品状态的变更
            productDao.updateByPrimaryKeySelective(product);

            productDao.updateByPrimaryKeySelective(product);
            //发送消息 到ActiveMQ中   brandId
//			jmsTemplate.send("brandId", messageCreator);
            jmsTemplate.send(new MessageCreator(){

                @Override
                public Message createMessage(Session session) throws JMSException {
                    // TODO Auto-generated method stub
                    return session.createTextMessage(String.valueOf(id));
                }

            });

            //TODO 静态化
        }
    }
}
