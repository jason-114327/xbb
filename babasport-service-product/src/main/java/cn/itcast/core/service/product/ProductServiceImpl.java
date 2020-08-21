package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.ProductQuery;
import cn.itcast.core.dao.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
