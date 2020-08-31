package cn.itcast.core.controller;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.service.SearchService;
import cn.itcast.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台商品
 */
@Controller
public class ProductController {

    // 去首页 入口
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @Autowired
    private SearchService searchService;
    @Autowired
    private BrandService brandService;
    //搜索
    @RequestMapping(value = "/search")
    public String search(Integer pageNo,String keyword,Long brandId,String price,Model model) throws Exception{
        //查询品牌结果集 Redis中查
        List<Brand> brands = brandService.selectBrandListByRedis();
        model.addAttribute("brands", brands);
        model.addAttribute("brandId", brandId);
        model.addAttribute("price", price);

        //已选条件 容器 Map
        Map<String,String> map = new HashMap<String,String>();
        //品牌
        if(null != brandId){
            for (Brand brand : brands) {
                if(brandId == brand.getId()){
                    map.put("品牌", brand.getName());
                    break;
                }
            }
        }
        //价格  0-99     1600
        if(null != price){
            if(price.contains("-")){
                map.put("价格", price);
            }else{
                map.put("价格", price + "以上");
            }
        }

        model.addAttribute("map", map);

        Pagination pagination = searchService.selectPaginationByQuery(pageNo,keyword,brandId,price);
        model.addAttribute("pagination", pagination);
        return "search";
    }
}
