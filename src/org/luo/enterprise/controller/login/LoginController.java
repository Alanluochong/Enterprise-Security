package org.luo.enterprise.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	@RequestMapping(value="/toIndex",method=RequestMethod.GET)
	public String toIndex(){
		return "index";
	}
	@RequestMapping(value="/nopower",method=RequestMethod.GET)
	public String toNopower(){
		return "nopower";
	}
}
