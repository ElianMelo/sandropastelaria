package com.sandropastelaria.omniorder.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandropastelaria.omniorder.dao.LoginDAO;
import com.sandropastelaria.omniorder.model.Funcionario;

@Controller
public class Omniorder {


    @GetMapping("/")
    public String login(Model modelo, HttpSession session, RedirectAttributes redAttributes) {
    	Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			session.invalidate();
			redAttributes.addFlashAttribute("messagemLogout", "Sessão encerrada!");
			return "redirect:/";
		} else {
			modelo.addAttribute("funcionario", new Funcionario());
			return "login";
		}
			
    }
    
    @PostMapping("/")
    public String PostLogin(Funcionario funcionario, HttpSession session, RedirectAttributes redAttributes) {
    	LoginDAO dao = new LoginDAO();
    	Funcionario funcionarioAcessado = dao.validar(funcionario);
    	
    	if (funcionarioAcessado != null) {
    		session.setAttribute("usuarioLogado", funcionarioAcessado);
    		return "redirect:perfil";
    	} else {
    		redAttributes.addFlashAttribute("mensagemAcesso", "Login e/ou senha inválidos.");
    		return "redirect:/";
    	}
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redAttributes) {
    	session.invalidate();
    	redAttributes.addFlashAttribute("messagemLogout", "Sessão encerrada!");
    	return "redirect:/";
    }
    
    @GetMapping("/perfil")
    public String bemVindo(Model modelo, HttpSession session) {
			Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
			
			if (usuarioLogado != null) {
				modelo.addAttribute("funcionario", usuarioLogado);
				return "perfil";
			} else {
				return "redirect:/";
			}
    }
    
    @GetMapping("/403")
    public String error403() {
    	return "error/403";
    }
    
    @RequestMapping("/mesa")
    public String controllerMesa(HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Garçom")) {
				return "mesa";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
    }	
    
    @RequestMapping("/cozinha")
    public String controllerCozinha(HttpSession session) {
		Funcionario usuarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			String cargo = usuarioLogado.getCargo();
			
			if (cargo.equals("Administrador") || cargo.equals("Cozinheiro")) {
				return "cozinha";
			} else {
				return "error/403";
			}
		} else {
			return "redirect:/";
		}
    }
}
