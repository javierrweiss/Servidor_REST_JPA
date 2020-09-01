package ar.org.centro8.curso.java.aplicaciones.jpa.repositories;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ClienteRepository implements I_ClienteRepository{
    private final EntityManager em;

    public ClienteRepository(EntityManager em) {this.em = em;}
    
    @Override
    public void save(Cliente cliente) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(cliente);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Cliente cliente) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(cliente);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Cliente cliente) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(cliente);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> lista_clientes =new ArrayList<>();
        lista_clientes=em.createNamedQuery("Cliente.findAll").getResultList();
        return lista_clientes;
    }
    
    
}
