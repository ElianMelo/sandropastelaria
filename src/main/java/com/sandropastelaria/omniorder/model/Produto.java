package com.sandropastelaria.omniorder.model;

import com.sandropastelaria.omniorder.enums.TipoProduto;

public class Produto {
	private String nomeProduto;
	private Integer quantidade;
	private TipoProduto tipoProduto;
	private Integer idProduto;
	
	public Produto() {
		super();
	}
	
	public Produto(String nomeProduto, Integer quantidade, TipoProduto tipoProduto, Integer idProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.tipoProduto = tipoProduto;
		this.idProduto = idProduto;
	}
	
	public Produto(String nomeProduto, Integer quantidade, TipoProduto tipoProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.tipoProduto = tipoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getTipoProduto() {
		if(this.tipoProduto != null) {
			return this.tipoProduto.getDescricao();
		}
		return "";
	}
	
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = TipoProduto.toEnum(tipoProduto);
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}
	
}
