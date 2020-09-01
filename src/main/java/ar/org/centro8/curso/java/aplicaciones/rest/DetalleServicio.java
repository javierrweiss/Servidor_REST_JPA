package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
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

@Path("detalles/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class DetalleServicio {

    @PersistenceContext(unitName = "JPAPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @GET
    public String info() {
        return "Servicio de Detalles activo";
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("idFactura") String idFactura,
            @QueryParam("idArticulo") String idArticulo,
            @QueryParam("precio") String precio,
            @QueryParam("cantidad") String cantidad) {
        DetallePK detallePK = new DetallePK(Integer.parseInt(idFactura),
                Integer.parseInt(idArticulo));
        Detalle detalle = new Detalle(detallePK,
                Float.parseFloat(precio),
                Integer.parseInt(cantidad));
        em.persist(detalle);
        return Response.ok(detalle).build();
        
    }

    /*
    Test
    ?idFactura=1&idArticulo=1&precio=40000&cantidad=200
     */
    @GET
    @Path("/baja")
    public Response baja(@QueryParam("idFactura") String idFactura,
                         @QueryParam("idArticulo") String idArticulo) {
        DetallePK detallePK = new DetallePK(Integer.parseInt(idFactura),
                                            Integer.parseInt(idArticulo));
        em.remove(em.find(Detalle.class, detallePK));
        return Response.ok("true").build();
    }

    /*
    Test
    ?idFactura=1&idArticulo=1
     */
    @GET
    @Path("/getAll")
    public Response getAll() {
        List<Detalle> lista_detalles = new ArrayList<>();
        lista_detalles =em.createNamedQuery("Detalle.findAll").getResultList();
        return Response.ok(lista_detalles).build();        
    }
    
    @GET
    @Path("/getLikeFactura")
    public Response getLikeFactura(@QueryParam("idFactura") int idFactura) {
        List<Detalle> lista_detalles_por_factura = new ArrayList<>();
        lista_detalles_por_factura = em.createNamedQuery("Detalle.findByIdFactura")
                                       .setParameter("idFactura", idFactura)
                                       .getResultList();
        return Response.ok(lista_detalles_por_factura).build();
    }
    /*
    Test
    ?idFactura=1
     */
    
}
