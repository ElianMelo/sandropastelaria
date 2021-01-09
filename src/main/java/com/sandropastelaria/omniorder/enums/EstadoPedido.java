package com.sandropastelaria.omniorder.enums;

import java.util.ArrayList;
import java.util.List;

public enum EstadoPedido {
	ABERTO("Aberto"), 
	FECHADO("Fechado");

	private String descricao;

	private EstadoPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPedido toEnum(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (EstadoPedido x : EstadoPedido.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + descricao);
	}
	
	public static List<String> listaDescricoes() {
		List<String> lista = new ArrayList<>();
		for (EstadoPedido x : EstadoPedido.values()) {
			lista.add(x.getDescricao());
		}
		return lista;
	}
}