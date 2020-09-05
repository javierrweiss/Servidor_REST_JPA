package ar.org.centro8.curso.java.aplicaciones.jpa.repositories;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_DetalleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DetalleRepository implements I_DetalleRepository {
   private final EntityManager em;

    public DetalleRepository(EntityManager em) {this.em = em;}

    @Override
    public void save(Detalle detalle) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(detalle);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Detalle detalle) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(detalle);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Detalle detalle) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(detalle);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Detalle> getAll() {
        List<Detalle> lista_detalles = new ArrayList<Detalle>();
        lista_detalles=em.createNamedQuery("Detalle.findAll").getResultList();
        return lista_detalles;
    }

    @Override
    public List<Detalle> getLikeFactura(int idFactura) {
    List<Detalle> lista_facturas = em.createNamedQuery("Detalle.findByIdFactura")
                                     .setParameter("idFactura", idFactura)
                                     .getResultList();
    return lista_facturas;
    }
    
    
   
    
}
