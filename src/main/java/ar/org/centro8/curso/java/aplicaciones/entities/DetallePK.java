package ar.org.centro8.curso.java.aplicaciones.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idFactura")
    private int idFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArticulo")
    private int idArticulo;

    public DetallePK() {
    }

    public DetallePK(int idFactura, int idArticulo) {
        this.idFactura = idFactura;
        this.idArticulo = idArticulo;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFactura;
        hash += (int) idArticulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if (this.idFactura != other.idFactura) {
            return false;
        }
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.org.centro8.curso.java.aplicaciones.entities.DetallePK[ idFactura=" + idFactura + ", idArticulo=" + idArticulo + " ]";
    }
    
}
