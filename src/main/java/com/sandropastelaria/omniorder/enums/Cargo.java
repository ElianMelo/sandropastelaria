package com.sandropastelaria.omniorder.enums;

import java.util.ArrayList;
import java.util.List;

public enum Cargo {
	GARCOM("Garçom"), 
	COZINHEIRO("Cozinheiro"),
	GERENTEESTOQUE("Gerente de Estoque"),
	ADMINISTRADOR("Administrador");

	private String descricao;

	private Cargo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Cargo toEnum(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (Cargo x : Cargo.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + descricao);
	}
	
	public static List<String> listaDescricoes() {
		List<String> lista = new ArrayList<>();
		for (Cargo x : Cargo.values()) {
			lista.add(x.getDescricao());
		}
		return lista;
	}
}
