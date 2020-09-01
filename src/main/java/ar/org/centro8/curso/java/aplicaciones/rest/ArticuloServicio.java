package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
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


@Path("/articulos/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class ArticuloServicio {
/*
    Con un el contenedor web provisto por GlassFish manejando las transacciones a través de JTA, no necesito
    invocar mis repositorios (lo que implicaría hacerme cargo manualmente del ciclo de vida de los beans).
    Con la anotación @PersistenceContext el contenedor se encarga inyectar el EntityManager y de abrir y cerrar las
    transacciones.
    Este artículo me me ayudó mucho:https://dzone.com/articles/resource-local-vs-jta-transaction-types-and-payara 
    */

  @PersistenceContext(unitName ="JPAPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
  
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response info(){
        return Response.ok("Servicio de Articulos activo").build();
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("descripcion") String descripcion, 
                    @QueryParam("costo") String costo,
                    @QueryParam("precio") String precio,
                    @QueryParam("stock") String stock,
                    @QueryParam("stockMin") String stockMin,
                    @QueryParam("stockMax") String stockMax){
                   Articulo articulo = new Articulo(descripcion,
                                         Float.parseFloat(costo),
                                         Float.parseFloat(precio),
                                         Integer.parseInt(stock), 
                                         Integer.parseInt(stockMin), 
                                         Integer.parseInt(stockMax));
                   em.persist(articulo);
    return Response.ok(articulo).build();
    }
    //Se me dificulta cumplir con el documento de servicio. No puedo retornar el id solamente.
    
    /*
    Test
    ?descripcion=Guantes&costo=1400&precio=2000&stock=120&stockMin=20&stockMax=200
    */
    
    @GET
    @Path("/baja")
    @Produces(MediaType.TEXT_HTML)
    public Response baja(@QueryParam("id") int id){
        em.remove(em.find(Articulo.class, id));
        return Response.ok("true").build();
       }
        
    /*
    Test
    ?id=3
    */
    
    @GET
    @Path("/getAll")
    public Response getAll(){
        List<Articulo> lista_articulos = new ArrayList<>();
        lista_articulos =em.createNamedQuery("Articulo.findAll").getResultList();
        return Response.ok(lista_articulos).build();
    }
    
    
    @GET
    @Path("/getLikeDescripcion")
    public Response getLikeDescripcion(@QueryParam("descripcion") String descripcion){
            List<Articulo> lista_articulos_por_descripcion = new ArrayList<>();
            lista_articulos_por_descripcion =
                    em.createNamedQuery("Articulo.findByDescripcion", Articulo.class)
                            .setParameter("descripcion", descripcion).getResultList();
               return Response.ok(lista_articulos_por_descripcion).build();
    }
    
    /*
    Test
    ?descripcion=Gorila
    */
    //No funciona con aproximaciones, tiene que se literal la coincidencia.
   
}
