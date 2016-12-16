package com.qxd.poraqui.controller;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxd.poraqui.model.Avaliacao;
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
	
	@DeleteMapping(value = "/{localId}")
	public Response save(@PathVariable("localId") Long localId){
		
		localService.remove(localService.get(localId));
		return new Response().withDoneStatus().withSuccessMessage("Local removido!");
		
	}
	
	@PostMapping(value = "/avaliar/{localId}")
	public Response addAvaliation(@RequestBody Avaliacao avaliacao, @PathVariable("localId") Long localId){
		
		avaliacao.setLocal(localService.get(localId));
		avaliacao.setData(new Date());
		avaliacao = localService.addAvaliacao(avaliacao);
		
		if(avaliacao != null) {			
			return new Response().withDoneStatus().withObject(avaliacao).withSuccessMessage("Avaliacao adicionada!");
		}
		
		return new Response().withFailStatus().withObject(avaliacao).withErrorMessage("Erro ao adicionar avaliação!");
	}
	
}
