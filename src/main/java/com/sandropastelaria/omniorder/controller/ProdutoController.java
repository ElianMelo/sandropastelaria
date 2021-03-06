package com.sandropastelaria.omniorder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandropastelaria.omniorder.dao.ProdutoDAO;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.Funcionario;
import com.sandropastelaria.omniorder.model.Produto;


@Controller
public class ProdutoController {

    
    @GetMapping("/produto-listagem")
	public String produtosTabela(Model modelo, HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Gerente de Estoque")) {
				ProdutoDAO dao = new ProdutoDAO();
				List<Produto> lista = dao.todos();
				modelo.addAttribute("listaProdutos",lista);
				return "produto/produto-listagem";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/produto-adicionar")
	public String exibeForm(Model modelo, HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Gerente de Estoque")) {
				modelo.addAttribute("produto", new Produto());
				modelo.addAttribute("listaDescricoes", TipoProduto.listaDescricoes());
				return "produto/produto-adicionar";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/produto-adicionar")
	public String processaForm(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
		return "redirect:produto-listagem";
	}
	
	@GetMapping("/excluir-produto")
	public String excluirProduto(HttpSession session, @RequestParam(value = "id", required = false) Integer p) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Gerente de Estoque")) {
				ProdutoDAO dao = new ProdutoDAO();
				dao.excluir(p);
				return "redirect:produto-listagem";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/produto-atualizar")
	public String editarProduto(Model modelo, HttpSession session,
								@RequestParam(value = "id", required = false) Integer p) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Gerente de Estoque")) {
				ProdutoDAO dao = new ProdutoDAO();
				Produto produto = dao.buscaPorId(p);
				modelo.addAttribute("produto", produto);
				modelo.addAttribute("listaDescricoes", TipoProduto.listaDescricoes());
				return "produto/produto-atualizar";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/produto-atualizar")
	public String editarProduto(Produto produto, @RequestParam(value = "id", required = false) Integer p) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.atualizar(produto);
		return "redirect:produto-listagem";
	}
    
}

