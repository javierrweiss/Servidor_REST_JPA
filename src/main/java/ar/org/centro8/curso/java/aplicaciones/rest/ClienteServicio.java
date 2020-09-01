package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("clientes/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class ClienteServicio {
    @PersistenceContext(unitName = "JPAPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    
    @GET
    public String info(){
        return "Servicio de Clientes activo";
    }
    
    @GET
    @Path("alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("nombre") String nombre, 
                    @QueryParam("apellido") String apellido,
                    @QueryParam("tipoDocumento") String tipoDocumento, 
                    @QueryParam("numeroDocumento")String numeroDocumento,
                    @QueryParam("direccion") String direccion,
                    @QueryParam("comentarios") String comentarios){
        Cliente cliente = 
            new Cliente(nombre,
                        apellido,
                        tipoDocumento,
                        numeroDocumento,
                        direccion,
                        comentarios);   
        em.persist(cliente);
    return Response.ok(cliente).build();
    }
    
    /*
    Test
    alta?nombre=Juan&apellido=Moreno&tipoDocumento=DNI&numeroDocumento=1422587&direccion=Hidalgo_2015&comentarios=nada_en_particular
    */
    
    @GET
    @Path("baja")
    public Response baja(@QueryParam("id")int id){
        em.remove(em.find(Cliente.class, id));
        return Response.ok("true").build();
    }
   /*
    Test
    baja?id=2
    */
    
    
    @GET
    @Path("getAll")
    public Response getAll(){
        List<Cliente> lista_clientes = new ArrayList<>();
        lista_clientes =em.createNamedQuery("Cliente.findAll").getResultList();
        return Response.ok(lista_clientes).build();
    }
    
    @GET
    @Path("getLikeApellido")
    public Response getLikeApellido(@QueryParam("apellido") String apellido){
      List<Cliente> lista_clientes_por_apellido = new ArrayList<>();
      lista_clientes_por_apellido =em.createNamedQuery("Cliente.findByApellido")
              .setParameter("apellido", apellido).getResultList();
        return Response.ok(lista_clientes_por_apellido).build();
        }
    /*
    Test
    getLikeApellido?apellido=Moreno
    */
}
