package ar.org.centro8.curso.java.aplicaciones.jpa.repositories;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_FacturaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class FacturaRepository implements I_FacturaRepository{
    private final EntityManager em;    

    public FacturaRepository(EntityManager em) {this.em = em;}

    @Override
    public void save(Factura factura) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(factura);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Factura factura) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(factura);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Factura factura) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(factura);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Factura> getAll() {
     List<Factura> lista_facturas = new ArrayList<>();
     lista_facturas=em.createNamedQuery("Factura.findAll").getResultList();
     return lista_facturas;
    }
    
    public List<Detalle> getDetalleList(int idFactura){
        return em.createQuery("SELECT d FROM Detalle d WHERE d.factura.id = :idFactura", Detalle.class)
                .setParameter("idFactura", idFactura)
                .getResultList();
    }
        
}
