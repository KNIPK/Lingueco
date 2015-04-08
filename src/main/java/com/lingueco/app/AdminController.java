package com.lingueco.app;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private String viewPath = "adminController/";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		
		return this.viewPath + "main";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) {
		
		return this.viewPath + "user";
	}
}
