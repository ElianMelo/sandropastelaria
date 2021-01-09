package com.sandropastelaria.omniorder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sandropastelaria.omniorder.enums.EstadoCozinha;
import com.sandropastelaria.omniorder.enums.EstadoPedido;

public class Pedido {
    private Integer idPedido;
    private EstadoPedido estadoPedido;
    private EstadoCozinha estadoCozinha;
    private Integer idMesa;
    private Date horaInicio;
    private Date horaFim;
    private List<ItemPedido> itens;

    public Pedido() {
        super();
        this.itens = new ArrayList<>();
    }

    public Pedido(Integer idPedido, String estadoPedido, String estadoCozinha) {
        this.idPedido = idPedido;
        this.estadoPedido = EstadoPedido.toEnum(estadoPedido);
        this.estadoCozinha = EstadoCozinha.toEnum(estadoCozinha);
    }

    public Pedido(Integer idPedido, String estadoPedido, String estadoCozinha, Date horaInicio, Integer idMesa) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.horaInicio = horaInicio;
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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedido item) {
        this.itens.add(item);
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
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

    @Override
    public String toString() {
        return "Pedido [estadoCozinha=" + estadoCozinha + ", estadoPedido=" + estadoPedido + ", horaInicio="
                + horaInicio + ", idMesa=" + idMesa + ", idPedido=" + idPedido + ", itens=" + itens + "]";
    }
    
}
