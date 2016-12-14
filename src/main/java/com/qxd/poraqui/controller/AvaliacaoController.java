package com.qxd.poraqui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qxd.util.alert.AlertSet;

@Controller
public class AvaliacaoController {

	@RequestMapping(value = "/")
	public ModelAndView cadastrarForm(){
		return new ModelAndView("views/index").addObject(
				"alertas", 
				new AlertSet()
					.withLongInfo("Exemplo mensagem de informação!")
					.withShortSuccess("Exemplo de mensagem de sucesso!")
					.withShortWarning("Exemplo de mensagem de aviso!")
					.withLongError("Exemplo de mensagem de erro!")
		);
	}
	
	
}
