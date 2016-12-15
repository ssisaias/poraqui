package com.qxd.poraqui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qxd.poraqui.enumtype.TipoAcessibilidade;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("views/index").addObject("acessibilidades", TipoAcessibilidade.values());
	}
	
}
