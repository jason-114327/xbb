package cn.itcast.core.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.BrandQuery;
import cn.itcast.core.dao.product.BrandDao;

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
	public Brand selectBrandById(Long id) {
		// TODO Auto-generated method stub
		return brandDao.selectBrandById(id);
	}

	@Override
	public void updateBrandById(Brand brand) {
		brandDao.updateBrandById(brand);
	}


}
