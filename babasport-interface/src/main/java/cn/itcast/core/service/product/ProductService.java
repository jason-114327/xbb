package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Product;

import java.util.List;

public interface ProductService {

    // 查询分页对象
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);

    //颜色结果集
    public List<Color> selectColorList();

    //商品保存
    public void insertProduct(Product product);

    //上架
    public void isShow(Long[] ids);
}
