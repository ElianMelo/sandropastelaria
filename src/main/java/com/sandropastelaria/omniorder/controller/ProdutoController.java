package com.sandropastelaria.omniorder.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandropastelaria.omniorder.dao.ProdutoDAO;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.Produto;


@Controller
public class ProdutoController {

    
    @GetMapping("/produto")
	public String produtosTabela(Model modelo, @RequestParam(value = "operacao", required = false) String operacao) {
    	if (operacao == "listar") {
    		System.out.println("listar");
    	} else if (operacao == "adicionar") {
    		System.out.println("adicionar");
    	} else if (operacao == "atualizar") {
    		System.out.println("atualizar");
    	}
    	
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.todos();
		modelo.addAttribute("listaProdutos",lista);
		
		//modelo.addAttribute("listaDescricoes", TipoProduto.listaDescricoes());
		return "produto"; 
	}
	
	@GetMapping("/cadastra-produto")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("produtoCriacao", new Produto());
		return "form-produto";
	}
	
	@PostMapping("/cadastra-produto")
	public String processaForm(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
		return "redirect:/produto";
	}
	
	@GetMapping("/excluir-produto")
	public String excluirProduto(@RequestParam(value = "id", required = false) Integer p) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
		return "redirect:/produto";
	}
	
	@GetMapping("/editar-produto")
	public String editarProduto(Model modelo, @RequestParam(value = "id", required = false) Integer p) {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscaPorId(p);
		modelo.addAttribute("produtoEdicao", produto);
		return "form-produto";
	}
	
	@PostMapping("/editar-produto")
	public String editarProduto(Produto produto, @RequestParam(value = "id", required = false) Integer p) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.atualizar(produto);
		return "redirect:/produto";
	}
    
}

