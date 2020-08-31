package cn.itcast.core.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.BrandQuery;
import cn.itcast.core.dao.product.BrandDao;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 品牌管理实现类
 * 
 * @author jason
 * @date 2020年7月6日 下午11:37:05
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	// 查询分页对象
	@Override
	public Pagination selectPaginationByQuery(String name, Integer isDisplay, Integer pageNo) {
		BrandQuery brandQuery = new BrandQuery();
		// 当前页
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		// 每页条数
		brandQuery.setPageSize(3);
		
		StringBuilder params = new StringBuilder();
		
		// 查询条件
		if (null != name) {
			brandQuery.setName(name);
			params.append("name=").append(name);
		}
		if (null != name) {
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay=").append(isDisplay);
		} else {
			brandQuery.setIsDisplay(1);
			params.append("&isDisplay=").append(1);
		}

		Pagination pagination = new Pagination(brandQuery.getPageNo(), brandQuery.getPageSize(),
				brandDao.selectCount(brandQuery));
		
		// 设置结果集
		pagination.setList(brandDao.selectBrandListByQuery(brandQuery));
		
		// 分页展示
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	@Override
	public List<Brand> selectBrandListByQuery(Integer isDisplay) {
		BrandQuery query = new BrandQuery();
		query.setIsDisplay(isDisplay);
		return brandDao.selectBrandListByQuery(query);
	}

	// 从redis中查询
	@Override
	public List<Brand> selectBrandListByRedis(){
		List<Brand> brands = new ArrayList<>();
		// redis中查
		Map<String,String> hgetAll = jedis.hgetAll("brand");
		Set<Map.Entry<String,String>> entrySet = hgetAll.entrySet();
		for (Map.Entry<String,String> entry:entrySet){
			Brand brand = new Brand();
			brand.setId(Long.parseLong(entry.getKey()));
			brand.setName(entry.getValue());
			brands.add(brand);
		}
		return brands;
	}

	@Override
	public Brand selectBrandById(Long id) {
		// TODO Auto-generated method stub
		return brandDao.selectBrandById(id);
	}

	@Autowired
	Jedis jedis;
	@Override
	public void updateBrandById(Brand brand) {
		// 修改redis
		jedis.hset("brand",String.valueOf(brand.getId()),brand.getName());
		brandDao.updateBrandById(brand);
	}

	@Override
	public void deletes(Long[] ids) {
		brandDao.deletes(ids);
	}

}
