package com.lingueco.app;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private String viewPath = "adminController/main";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		
		model.addAttribute("action", "default");
		
		return this.viewPath;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) {
		
		model.addAttribute("action", "user");
		
		return this.viewPath;
	}
}
