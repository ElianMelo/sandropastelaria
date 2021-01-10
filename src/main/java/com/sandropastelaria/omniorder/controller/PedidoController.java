package com.sandropastelaria.omniorder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandropastelaria.omniorder.dao.ItemPedidoDAO;
import com.sandropastelaria.omniorder.dao.MesaDAO;
import com.sandropastelaria.omniorder.dao.PedidoDAO;
import com.sandropastelaria.omniorder.dao.ProdutoDAO;
import com.sandropastelaria.omniorder.enums.EstadoPedido;
import com.sandropastelaria.omniorder.enums.TipoProduto;
import com.sandropastelaria.omniorder.model.Funcionario;
import com.sandropastelaria.omniorder.model.ItemPedido;
import com.sandropastelaria.omniorder.model.Mesa;
import com.sandropastelaria.omniorder.model.Pedido;
import com.sandropastelaria.omniorder.model.Produto;

@Controller
public class PedidoController {

	List<ItemPedido> itens = new ArrayList<>();
	List<ItemPedido> itensAtualizar = new ArrayList<>();
	
	// Listagem de Pedidos
	@GetMapping("/pedido-listagem")
	public String listaPedidos(Model modelo, HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				List<Pedido> pedidos = pedidoDAO.todosAberto();

				for(int i = 0; i < pedidos.size(); i++) {
					List<ItemPedido> itensPedido = itemPedidoDAO.buscaPorIdPedido(pedidos.get(i).getIdPedido());
					pedidos.get(i).setItens(itensPedido);
				}

				modelo.addAttribute("pedidos", pedidos);
				return "pedido/pedido-listagem";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	// Prepara ambiente adicionar pedido
	@GetMapping("/pedido-adicionar")
	public String exibeForm(Model modelo, HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
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
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}

	// Adiciona item ao adicionado
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

	@GetMapping("/excluir-item")
	public String removeItem(Model modelo, Pedido pedido, HttpSession session, ItemPedido item, @RequestParam("id") Integer id) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
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
			
				
				for(int i = 0; i < itens.size(); i++) {
					if(itens.get(i).getIdProduto() == id) {
						itens.remove(i);
						break;
					}
				}

				pedido.setItens(itens);
				modelo.addAttribute("pedido", pedido);
				return "redirect:pedido-adicionar";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}

	// Adiciona um novo pedido
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
		return "redirect:pedido-listagem";
	}

	// Prepara ambiente pra atualizar
	@GetMapping("/pedido-atualizar")
	public String editaPedidoForm(Model modelo, HttpSession session, @RequestParam(value = "id", required = false) Integer id) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				MesaDAO mesaDAO = new MesaDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				List<Mesa> mesas = mesaDAO.todos();
				List<Produto> produtos = produtoDAO.todos();

				Pedido pedido = pedidoDAO.buscaPorId(id);
				List<ItemPedido> itensPedidoAtualizar = itemPedidoDAO.buscaPorIdPedido(id);
				itensAtualizar = itensPedidoAtualizar;
				pedido.setItens(itensPedidoAtualizar);

				modelo.addAttribute("pedido", pedido);
				modelo.addAttribute("item", new ItemPedido());
				modelo.addAttribute("mesas", mesas);
				modelo.addAttribute("produtos", produtos);
				return "pedido/pedido-atualizar";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}

	// Adiciona item ao adicionado
	@RequestMapping(value = "/pedido-atualizar", params = {"addItem"}, method = RequestMethod.POST)
	public String addItemAlterar(Model modelo, Pedido pedido, ItemPedido item, @RequestParam("addItem") String addRow) {
		MesaDAO mesaDAO = new MesaDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Mesa> mesas = mesaDAO.todos();
		List<Produto> produtos = produtoDAO.todos();

		modelo.addAttribute("item", new ItemPedido());
		modelo.addAttribute("mesas", mesas);
		modelo.addAttribute("produtos", produtos);

		boolean alterado = false;

		for(int i = 0; i < itensAtualizar.size(); i++) {
			if(itensAtualizar.get(i).equals(item)) {
				ItemPedido itemAlterado = itensAtualizar.get(i);
				int qtd = itemAlterado.getQuantidade() + item.getQuantidade();
				itemAlterado.setQuantidade(qtd);
				itensAtualizar.set(i, itemAlterado);
				alterado = true;

				Produto produto = produtoDAO.buscaPorId(itemAlterado.getIdProduto());
				if(produto.getQuantidade() < itemAlterado.getQuantidade() && produto.getTipoProduto() != TipoProduto.PASTEL.getDescricao()) {
					pedido.setItens(itensAtualizar);
					modelo.addAttribute("pedido", pedido);
					return "pedido/pedido-atualizar";
				}
			}
		}

		Produto produto = produtoDAO.buscaPorId(item.getIdProduto());
		if(produto.getQuantidade() < item.getQuantidade() && produto.getTipoProduto() != TipoProduto.PASTEL.getDescricao()) {
			pedido.setItens(itensAtualizar);
			modelo.addAttribute("pedido", pedido);
			return "pedido/pedido-atualizar";
		}

		if(!alterado) {
			itensAtualizar.add(item);
		}

		pedido.setItens(itensAtualizar);
		modelo.addAttribute("pedido", pedido);
		return "pedido/pedido-atualizar";
	}

	@GetMapping("/excluir-item-atualizar")
	public String removeItemAlterado(Model modelo, Pedido pedido, HttpSession session, ItemPedido item, @RequestParam("id") Integer id, @RequestParam("idPedido") Integer idPedido) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
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
			
				
				for(int i = 0; i < itensAtualizar.size(); i++) {
					if(itensAtualizar.get(i).getIdProduto() == id) {
						itensAtualizar.remove(i);
						break;
					}
				}

				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				itemPedidoDAO.excluir(idPedido, id);

				pedido.setItens(itensAtualizar);
				modelo.addAttribute("pedido", pedido);
				String resultado = "redirect:pedido-atualizar?id=" + idPedido;
				return resultado;
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/pedido-atualizar")
	public String editaPedido(Pedido pedido) {
		PedidoDAO pedidoDao = new PedidoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		for(ItemPedido item : itensAtualizar) {
			Produto produto = produtoDAO.buscaPorId(item.getIdProduto());
			if(!produto.getTipoProduto().equals(TipoProduto.PASTEL.getDescricao())) {
				produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
				if(produto.getQuantidade() < 0) {
					return "pedido/pedido-atualizar";
				}
			}
		}

		pedidoDao.atualizar(pedido, itensAtualizar);
		itensAtualizar = new ArrayList<>();
		return "redirect:pedido-listagem";
	}

	@GetMapping("/fechar-pedido")
	public String fecharPedido(HttpSession session, @RequestParam(value = "id", required = false) Integer id) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
		        Pedido pedido = pedidoDAO.buscaPorId(id);

				pedido.setEstadoPedido(EstadoPedido.FECHADO.getDescricao());
		        pedidoDAO.atualizar(pedido);

		        return "redirect:pedido-listagem";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/excluir-pedido")
	public String excluirPedido(HttpSession session, @RequestParam(value = "id", required = false) Integer id) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();

			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				pedidoDAO.excluir(id);
				return "redirect:pedido-listagem";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
	}

}
