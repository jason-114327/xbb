package cn.itcast.core.controller;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.service.product.BrandService;
import cn.itcast.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    //查询
    @RequestMapping(value = "/product/list.do")
    public String list(Integer pageNo,String name,Long brandId,Boolean isShow,Model model){
        //品牌的结果集
        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);

        Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);

        model.addAttribute("pagination", pagination);
        model.addAttribute("name", name);
        model.addAttribute("brandId", brandId);
        if(null != isShow){
            model.addAttribute("isShow", isShow);
        }else{
            model.addAttribute("isShow", false);
        }
        return "product/list";
    }

    //去商品添加页面
    @RequestMapping(value = "/product/toAdd.do")
    public String toAdd(Model model){
        //品牌的结果集
        List<Brand> brands = brandService.selectBrandListByQuery(1);
        model.addAttribute("brands", brands);

        List<Color> colors = productService.selectColorList();
        model.addAttribute("colors", colors);
        return "product/add";
    }

}
