package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;

import java.util.List;

public interface BrandService {

    //查询分页对象
    public Pagination selectPaginationByQuery(String name, Integer isDisplay, Integer pageNo);

    // 查询结果集
    List<Brand> selectBrandListByQuery(Integer isDisplay);

    // 从redis中查询结果集
    List<Brand> selectBrandListByRedis();

    //通过ID查询品牌
    public Brand selectBrandById(Long id);

    //修改
    public void updateBrandById(Brand brand);

    //删除
    public void deletes(Long[] ids);// List<Long> ids;


}
