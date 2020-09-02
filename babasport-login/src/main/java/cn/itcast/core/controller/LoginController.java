package cn.itcast.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 单点登陆系统
 * 去登陆页面  GET
 * 提交登陆表单 POST
 * 加密MD5+十六进加密 最终杀手- 加盐（密码过于简单）有规则密码
 * @author lx
 */
@Controller
public class LoginController {

	//去登陆页面
	@RequestMapping(value = "/login.aspx",method=RequestMethod.GET)
	public String login(){
		
		
		return "login";
	}
}
