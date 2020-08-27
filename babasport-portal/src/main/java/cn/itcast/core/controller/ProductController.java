package cn.itcast.core.controller;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    //搜索
    @RequestMapping(value = "/search")
    public String search(Integer pageNo, String keyword, Model model) throws Exception{
        Pagination pagination = searchService.selectPaginationByQuery(pageNo,keyword);
        model.addAttribute("pagination", pagination);
        return "search";
    }
}
