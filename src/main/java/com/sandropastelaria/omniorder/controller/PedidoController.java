package com.sandropastelaria.omniorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidoController {
	
	@GetMapping("/pedido-listagem")
	public String funcionariosTabela() {
		return "pedido/pedido-listagem";
	}
	
	@GetMapping("/pedido-adicionar")
	public String exibeForm() {
		return "pedido/pedido-adicionar";
	}
	
	@GetMapping("/pedido-atualizar")
	public String editaFuncionario() {
		return "pedido/pedido-atualizar";
	}

}
