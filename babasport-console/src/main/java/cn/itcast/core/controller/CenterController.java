package cn.itcast.core.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.bean.TestTb;
import cn.itcast.core.service.TestTbService;

@RequestMapping(value = "/control")
@Controller
public class CenterController {
	
	// 首页
	@RequestMapping(value="/index.do")
	public String index(Model model) {
		
		return "index";
	}
	
	// 头
	@RequestMapping(value="/top.do")
	public String top(Model model) {
		
		return "top";
	}
	
	// 身体
	@RequestMapping(value="/main.do")
	public String main(Model model) {
		
		return "main";
	}
	
	// 身体：左
	@RequestMapping(value="/left.do")
	public String left(Model model) {
		
		return "left";
	}
	
	// 身体：右
	@RequestMapping(value="/right.do")
	public String right(Model model) {
		
		return "right";
	}
	
	// 身体：商品
	@RequestMapping(value="/frame/product_main.do")
	public String product_main(Model model) {
		
		return "frame/product_main";
	}
	
	// 身体：商品
	@RequestMapping(value="/frame/product_left.do")
	public String product_left(Model model) {
		
		return "frame/product_left";
	}
	
	// 身体：商品
	@RequestMapping(value="/frame/product/list.do")
	public String productList(Model model) {
		
		return "product/list";
	}
 
	/*@Autowired
	private TestTbService testTbService;
	*/
	// 入口
	/**
	 * 返回值介绍
	 * ModelAndView : 跳转视图 + 数据
	 * void：异步时ajax
	 * String：跳转页面 + 视图
	 */
	/*@RequestMapping(value="/test/index.do")
	public String index(Model model) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "世界你好");
		model.addAllAttributes(map);
		
		TestTb testTb = new TestTb();
		testTb.setName("张三");
		testTb.setBirthday(new Date());
		testTbService.insertTestTb(testTb);
		
		return "index";
	}*/
}
