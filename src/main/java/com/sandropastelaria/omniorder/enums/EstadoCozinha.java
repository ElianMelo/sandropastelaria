package com.sandropastelaria.omniorder.enums;

import java.util.ArrayList;
import java.util.List;

public enum EstadoCozinha {
	PREPARANDO("Preparando"), 
	FINALIZADO("Finalizado");

	private String descricao;

	private EstadoCozinha(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoCozinha toEnum(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (EstadoCozinha x : EstadoCozinha.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + descricao);
	}
	
	public static List<String> listaDescricoes() {
		List<String> lista = new ArrayList<>();
		for (EstadoCozinha x : EstadoCozinha.values()) {
			lista.add(x.getDescricao());
		}
		return lista;
	}
}