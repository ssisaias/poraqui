package com.qxd.poraqui.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxd.poraqui.model.Local;
import com.qxd.poraqui.service.LocalService;
import com.qxd.util.alert.Response;

@RestController
@RequestMapping("api/local")
public class LocalController {

	@Inject
	private LocalService localService;
	
	@GetMapping(value = "")
	public Response getAll(){
		return new Response().withDoneStatus().withObject(localService.getAll());
	}
	
	@PostMapping(value = "")
	public Response save(@RequestBody Local local){
		
		local = localService.save(local);
		
		if(local != null) {			
			return new Response().withDoneStatus().withObject(local).withSuccessMessage("Local adicionado!");
		}
		
		return new Response().withFailStatus().withObject(local).withErrorMessage("Erro ao adicionar local!");
		
	}
	
}
