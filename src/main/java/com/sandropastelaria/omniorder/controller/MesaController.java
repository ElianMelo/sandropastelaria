package com.sandropastelaria.omniorder.controller;

import java.util.List;

import com.sandropastelaria.omniorder.dao.MesaDAO;
import com.sandropastelaria.omniorder.model.Mesa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MesaController {

    @RequestMapping("/mesa")
    public String controllerMesa(Model modelo) {
        MesaDAO mesaDAO = new MesaDAO();
        List<Mesa> mesas = mesaDAO.todos();
        modelo.addAttribute("mesas", mesas);
        return "mesa";
    }	

    @GetMapping("/alterar-mesa")
	public String excluirFuncionario(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "operacao", required = false) String operacao) {
        MesaDAO mesaDAO = new MesaDAO();
        Mesa mesa = mesaDAO.buscaPorId(id);

        if(operacao.equals("livre")) {
            boolean livre = mesa.isLivre();
            mesa.setLivre(!livre);
        } else {
            boolean limpa = mesa.isLimpa();
            mesa.setLimpa(!limpa);
        }

        mesaDAO.atualizar(mesa);
        return "redirect:mesa";
	}
}
