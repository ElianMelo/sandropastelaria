package com.sandropastelaria.omniorder.controller;

import com.sandropastelaria.omniorder.model.Pedido;
import com.sandropastelaria.omniorder.model.Produto;

import java.util.ArrayList;
import java.util.List;

import com.sandropastelaria.omniorder.dao.MesaDAO;
import com.sandropastelaria.omniorder.dao.PedidoDAO;
import com.sandropastelaria.omniorder.dao.ProdutoDAO;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.ItemPedido;
import com.sandropastelaria.omniorder.model.Mesa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {

	List<ItemPedido> itens = new ArrayList<>();
	
	@GetMapping("/pedido-listagem")
	public String funcionariosTabela() {
		return "pedido/pedido-listagem";
	}
	
	@GetMapping("/pedido-adicionar")
	public String exibeForm(Model modelo) {
		MesaDAO mesaDAO = new MesaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Mesa> mesas = mesaDAO.todos();
		List<Produto> produtos = produtoDAO.todos();
		List<Mesa> mesasFiltradas = new ArrayList<>();

		Pedido pedido = new Pedido();
		pedido.setItens(itens);

		for(Mesa mesa : mesas) {
			if(mesa.isLivre()) {
				mesasFiltradas.add(mesa);
			}
		}

		modelo.addAttribute("pedido", pedido);
		modelo.addAttribute("item", new ItemPedido());
		modelo.addAttribute("mesas", mesasFiltradas);
		modelo.addAttribute("produtos", produtos);
		return "pedido/pedido-adicionar";
	}

	@RequestMapping(value = "/pedido-adicionar", params = {"addItem"}, method = RequestMethod.POST)
	public String addItem(Model modelo, Pedido pedido, ItemPedido item, @RequestParam("addItem") String addRow) {
		MesaDAO mesaDAO = new MesaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Mesa> mesas = mesaDAO.todos();
		List<Produto> produtos = produtoDAO.todos();
		List<Mesa> mesasFiltradas = new ArrayList<>();

		for(Mesa mesa : mesas) {
			if(mesa.isLivre()) {
				mesasFiltradas.add(mesa);
			}
		}
		modelo.addAttribute("item", new ItemPedido());
		modelo.addAttribute("mesas", mesasFiltradas);
		modelo.addAttribute("produtos", produtos);

		boolean alterado = false;

		for(int i = 0; i < itens.size(); i++) {
			if(itens.get(i).equals(item)) {
				ItemPedido itemAlterado = itens.get(i);
				int qtd = itemAlterado.getQuantidade() + item.getQuantidade();
				itemAlterado.setQuantidade(qtd);
				itens.set(i, itemAlterado);
				alterado = true;

				Produto produto = produtoDAO.buscaPorId(itemAlterado.getIdProduto());
				if(produto.getQuantidade() < itemAlterado.getQuantidade() && produto.getTipoProduto() != TipoProduto.PASTEL.getDescricao()) {
					pedido.setItens(itens);
					modelo.addAttribute("pedido", pedido);
					return "pedido/pedido-adicionar";
				}
			}
		}

		Produto produto = produtoDAO.buscaPorId(item.getIdProduto());
		if(produto.getQuantidade() < item.getQuantidade() && produto.getTipoProduto() != TipoProduto.PASTEL.getDescricao()) {
			pedido.setItens(itens);
			modelo.addAttribute("pedido", pedido);
			return "pedido/pedido-adicionar";
		}

		if(!alterado) {
			itens.add(item);
		}

		pedido.setItens(itens);
		modelo.addAttribute("pedido", pedido);
		return "pedido/pedido-adicionar";
	}

	@RequestMapping(value = "/pedido-adicionar", method = RequestMethod.POST)
	public String inserirPedido(Pedido pedido) {
		PedidoDAO pedidoDao = new PedidoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		for(ItemPedido item : itens) {
			Produto produto = produtoDAO.buscaPorId(item.getIdProduto());
			if(!produto.getTipoProduto().equals(TipoProduto.PASTEL.getDescricao())) {
				produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
				if(produto.getQuantidade() < 0) {
					return "pedido/pedido-adicionar";
				}
			}
		}

		pedidoDao.inserir(pedido, itens);
		itens = new ArrayList<>();
		return "pedido/pedido-listagem";
	}
	
	@GetMapping("/pedido-atualizar")
	public String editaFuncionario() {
		return "pedido/pedido-atualizar";
	}

}
