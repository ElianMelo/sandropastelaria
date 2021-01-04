package com.sandropastelaria.omniorder.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandropastelaria.omniorder.dao.FuncionarioDAO;
import com.sandropastelaria.omniorder.enums.Cargo;
import com.sandropastelaria.omniorder.model.Funcionario;

@Controller
public class FuncionarioController {

	@GetMapping("/funcionario-listagem")
	public String funcionariosTabela(Model modelo) {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.todos();
		modelo.addAttribute("funcionarios", funcionarios);
		return "funcionario/funcionario-listagem";
	}
	
	@GetMapping("/funcionario-adicionar")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("funcionario", new Funcionario());
		modelo.addAttribute("listaCargo", Cargo.listaDescricoes());
		return "funcionario/funcionario-adicionar";
	}
	
	@PostMapping("/funcionario-adicionar")
	public String processaForm(Funcionario funcionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.inserir(funcionario);
		return "redirect:funcionario-listagem";
	}
	
	@GetMapping("/funcionario-excluir")
	public String excluirFuncionario(@RequestParam(value = "id", required = false) Integer id) {
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.excluir(id);
		return "redirect:funcionario-listagem";
	}
	
	@GetMapping("/funcionario-atualizar")
	public String editaFuncionarioForm(Model modelo, @RequestParam(value = "id", required = false) Integer id) {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscaPorId(id);
		modelo.addAttribute("funcionario", funcionario);
		modelo.addAttribute("listaCargo", Cargo.listaDescricoes());
		return "funcionario/funcionario-atualizar";
	}
	
	@PostMapping("/funcionario-atualizar")
	public String editaFuncionario(Funcionario funcionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.atualizar(funcionario);
		return "redirect:funcionario-listagem";
	}
	
}
