package com.sandropastelaria.omniorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuncionarioController {

	@GetMapping("/funcionario-listagem")
	public String funcionariosTabela() {
		return "funcionario/funcionario-listagem";
	}
	
	@GetMapping("/funcionario-adicionar")
	public String exibeForm() {
		return "funcionario/funcionario-adicionar";
	}
	
	@GetMapping("/funcionario-atualizar")
	public String editaFuncionario() {
		return "funcionario/funcionario-atualizar";
	}
	
}
