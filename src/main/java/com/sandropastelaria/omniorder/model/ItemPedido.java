package com.sandropastelaria.omniorder.model;

public class ItemPedido {
    private Integer idPedido;
    private Integer idProduto;
    private Integer quantidade;

    public ItemPedido() {
        super();
	}

    public ItemPedido(Integer idPedido, Integer idProduto, Integer quantidade) {
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
    }
    
}
