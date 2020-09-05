package ar.org.centro8.curso.java.aplicaciones.jpa.repositories;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ArticuloRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ArticuloRepository implements I_ArticuloRepository{
private EntityManager em;

    public ArticuloRepository(EntityManager em) {this.em = em;}

    @Override
    public void save(Articulo articulo) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(articulo);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Articulo articulo) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(articulo);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Articulo articulo) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(articulo);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Articulo> getAll() {
        List<Articulo> lista_articulos=em.createNamedQuery("Articulo.findAll")
                                         .getResultList();
        return lista_articulos;
    }
    
    
}
