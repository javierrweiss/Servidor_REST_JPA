package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
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

@Path("facturas/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class FacturaServicio {
@PersistenceContext(unitName = "JPAPU", type =PersistenceContextType.TRANSACTION)
private EntityManager em;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info(){
        return "Servicio de Facturas activo";
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("letra") Character letra,
                       @QueryParam("numero") String numero,
                       @QueryParam("fecha") String fecha,
                       @QueryParam("monto") String monto,
                       @QueryParam("idCliente") String idCliente){
        Cliente cliente = em.find(Cliente.class, Integer.parseInt(idCliente));
        Factura factura = new Factura(letra,
                                      Integer.parseInt(numero),
                                      fecha,
                                      Double.parseDouble(monto),
                                      cliente
                                        );
        em.merge(cliente);
        em.persist(factura);
        return Response.ok(factura).build();
    }
    
    /*
    Test
    ?letra=A&numero=15&fecha=21/02/2019&monto=9000&idCliente=1
    
    La fecha siempre debe ir en el siguiente formato dd/mm/yy
    */
    
    @GET
    @Path("/baja")
    @Produces(MediaType.TEXT_PLAIN)
    public Response baja(@QueryParam("letra") Character letra, @QueryParam("numero") int numero){
           Factura factura = em.createNamedQuery("Factura.findByLetraYNumero", Factura.class)
                            .setParameter("letra", letra)
                            .setParameter("numero", numero)
                            .getResultList()//Pedimos una lista, ya que puede darse el caso de una factura con igual número y letra;
                            .get(0); //Obtenemos el primero, y lo pasamos al método remove abajo.
                                    
           try {
           em.merge(factura);
           em.remove(factura); 
        } catch (Exception e) {
        return Response.ok("false").build();
        }
        return Response.ok("true").build();
    }
    
    /*
    Test
    ?letra=A&numero=1000
    */
    
    @GET
    @Path("/getAll")
    public Response getAll(){
        List<Factura> lista_facturas = new ArrayList<Factura>();
        lista_facturas = em.createNamedQuery("Factura.findAll").getResultList();
        return Response.ok(lista_facturas).build();
    }
    
    @GET
    @Path("/getLikeCliente")
    public Response getLikeCliente(@QueryParam("idCliente") int idCliente){
        List<Factura> lista_facturas_por_cliente = new ArrayList<Factura>();
        lista_facturas_por_cliente = em.createQuery("SELECT f FROM Factura f WHERE f.idCliente.id = :id", Factura.class)
                                       .setParameter("id", idCliente)
                                       .getResultList();
        return Response.ok(lista_facturas_por_cliente).build();
    }

    /*
    Test
    ?idCliente=2
    */
}
