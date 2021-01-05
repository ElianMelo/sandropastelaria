package com.sandropastelaria.omniorder.model;

import com.sandropastelaria.omniorder.enums.EstadoCozinha;
import com.sandropastelaria.omniorder.enums.EstadoPedido;

public class Pedido {
    private Integer idPedido;
    private EstadoPedido estadoPedido;
    private EstadoCozinha estadoCozinha;
    private Integer idMesa;

    public Pedido() {
        super();
    }

    public Pedido(Integer idPedido, String estadoPedido, String estadoCozinha) {
        this.idPedido = idPedido;
        this.estadoPedido = EstadoPedido.toEnum(estadoPedido);
        this.estadoCozinha = EstadoCozinha.toEnum(estadoCozinha);
    }

    public Pedido(Integer idPedido, String estadoPedido, String estadoCozinha, Integer idMesa) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.estadoPedido = EstadoPedido.toEnum(estadoPedido);
        this.estadoCozinha = EstadoCozinha.toEnum(estadoCozinha);
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido.getDescricao();
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = EstadoPedido.toEnum(estadoPedido);
    }

    public String getEstadoCozinha() {
        return estadoCozinha.getDescricao();
    }

    public void setEstadoCozinha(String estadoCozinha) {        
        this.estadoCozinha = EstadoCozinha.toEnum(estadoCozinha);
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
        Pedido other = (Pedido) obj;
        if (idPedido == null) {
            if (other.idPedido != null)
                return false;
        } else if (!idPedido.equals(other.idPedido))
            return false;
        return true;
    }
    
}
