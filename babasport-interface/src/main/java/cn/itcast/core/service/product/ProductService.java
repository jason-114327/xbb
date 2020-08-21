package cn.itcast.core.service.product;


import cn.itcast.common.page.Pagination;

public interface ProductService {

    // 查询分页对象
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);

}
