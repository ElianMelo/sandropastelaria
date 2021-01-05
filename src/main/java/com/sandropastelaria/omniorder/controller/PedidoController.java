package com.sandropastelaria.omniorder.controller;

import com.sandropastelaria.omniorder.model.Pedido;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {
	
	@GetMapping("/pedido-listagem")
	public String funcionariosTabela() {
		return "pedido/pedido-listagem";
	}
	
	@GetMapping("/pedido-adicionar")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("pedido", new Pedido());
		return "pedido/pedido-adicionar";
	}

	@RequestMapping(value = "/pedido-adicionar", params = {"addItem"}, method = RequestMethod.POST)
	public String addItem(final Pedido pedido, @RequestParam("addItem") String addRow) {
		Integer id = pedido.getIdMesa();
		id += 1;
		pedido.setIdMesa(id);
		return "pedido/pedido-adicionar"; // retorno com a nova list
	}
	
	@GetMapping("/pedido-atualizar")
	public String editaFuncionario() {
		return "pedido/pedido-atualizar";
	}

}
