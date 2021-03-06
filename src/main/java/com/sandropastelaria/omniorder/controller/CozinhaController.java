package com.sandropastelaria.omniorder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandropastelaria.omniorder.dao.ItemPedidoDAO;
import com.sandropastelaria.omniorder.dao.PedidoDAO;
import com.sandropastelaria.omniorder.enums.EstadoCozinha;
import com.sandropastelaria.omniorder.model.Funcionario;
import com.sandropastelaria.omniorder.model.ItemPedido;
import com.sandropastelaria.omniorder.model.Pedido;

@Controller
public class CozinhaController {

    @RequestMapping("/cozinha")
	public String controllerCozinha(Model modelo, HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Cozinheiro")) {
				PedidoDAO pedidoDAO = new PedidoDAO();
				ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
				List<Pedido> pedidos = pedidoDAO.todosPreparando();

				for(int i = 0; i < pedidos.size(); i++) {
					List<ItemPedido> itensPedido = itemPedidoDAO.buscaPorIdPedido(pedidos.get(i).getIdPedido());
					pedidos.get(i).setItens(itensPedido);
				}

				modelo.addAttribute("pedidos", pedidos);
				return "cozinha";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
    }

    @GetMapping("/fechar-cozinha")
	public String fecharCozinha(@RequestParam(value = "id", required = false) Integer id) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.buscaPorId(id);

        pedido.setEstadoCozinha(EstadoCozinha.FINALIZADO.getDescricao());
        pedidoDAO.atualizar(pedido);

        return "redirect:cozinha";
    }
    
    @GetMapping("/abrir-cozinha")
	public String abrirCozinha(@RequestParam(value = "id", required = false) Integer id) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.buscaPorId(id);

        pedido.setEstadoCozinha(EstadoCozinha.PREPARANDO.getDescricao());
        pedidoDAO.atualizar(pedido);

        return "redirect:cozinha";
	}
    
}
