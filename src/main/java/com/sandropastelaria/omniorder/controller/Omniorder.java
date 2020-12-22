package com.sandropastelaria.omniorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Omniorder {


    @RequestMapping("/")
    public String controllerLogin() {
        return "login";
    }
    
    @RequestMapping("/pedido")
    public String controllerPedido() {
        return "pedido";
    }
    
    @RequestMapping("/mesa")
    public String controllerMesa() {
        return "mesa";
    }
    
    @RequestMapping("/funcionario")
    public String controllerFuncionario() {
        return "funcionario";
    }
    
    /*@RequestMapping("/produto")
    public String controllerProduto() {
        return "produto";
    }*/
    
    @RequestMapping("/cozinha")
    public String controllerCozinha() {
        return "cozinha";
    }
}
