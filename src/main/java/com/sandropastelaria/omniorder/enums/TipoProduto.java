package com.sandropastelaria.omniorder.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoProduto {
	MATERIAPRIMA("Matéria prima"), 
	COMIDA("Comida"),
	DOCE("Doce"),
	BEBIDA("Bebida"),
	PASTEL("Pastel");

	private String descricao;

	private TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoProduto toEnum(String descricao) {

		if (descricao == null) {
			return null;
		}

		for (TipoProduto x : TipoProduto.values()) {
			if (descricao.equals(x.getDescricao())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + descricao);
	}
	
	public static List<String> listaDescricoes() {
		List<String> lista = new ArrayList<>();
		for (TipoProduto x : TipoProduto.values()) {
			lista.add(x.getDescricao());
		}
		return lista;
	}
}