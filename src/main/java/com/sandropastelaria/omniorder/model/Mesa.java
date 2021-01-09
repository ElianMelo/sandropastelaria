package com.sandropastelaria.omniorder.model;

public class Mesa {
    private Integer idMesa;
    private boolean limpa;
    private boolean livre;

    public Mesa() {
        super();
    }

    public Mesa(Integer idMesa, boolean limpa, boolean livre) {
        this.idMesa = idMesa;
        this.limpa = limpa;
        this.livre = livre;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public boolean isLimpa() {
        return limpa;
    }

    public void setLimpa(boolean limpa) {
        this.limpa = limpa;
    }

    public boolean isLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idMesa == null) ? 0 : idMesa.hashCode());
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
        Mesa other = (Mesa) obj;
        if (idMesa == null) {
            if (other.idMesa != null)
                return false;
        } else if (!idMesa.equals(other.idMesa))
            return false;
        return true;
    }
    
}
