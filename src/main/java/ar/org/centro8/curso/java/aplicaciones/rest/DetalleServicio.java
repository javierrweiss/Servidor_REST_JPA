package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
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

@Path("detalles/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class DetalleServicio {

    @PersistenceContext(unitName = "JPAPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info() {
        return "Servicio de Detalles activo";
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("idFactura") int idFactura,
            @QueryParam("idArticulo") int idArticulo,
            @QueryParam("precio") float precio,
            @QueryParam("cantidad") int cantidad) {
        DetallePK detallePK = new DetallePK(idFactura,idArticulo);
        Detalle detalle = new Detalle(detallePK,
                                        precio,
                                        cantidad);
        Factura factura = em.find(Factura.class, idFactura);
        Articulo articulo= em.find(Articulo.class, idArticulo);
        em.merge(factura);
        em.merge(articulo);
        em.persist(detalle);
        return Response.ok(detalle).build();
        
    }

    /*
    Test
    ?idFactura=1&idArticulo=21&precio=40000&cantidad=200
     */
    @GET
    @Path("/baja")
    @Produces(MediaType.TEXT_PLAIN)
    public Response baja(@QueryParam("idFactura") String idFactura,
                         @QueryParam("idArticulo") String idArticulo) {
        DetallePK detallePK = new DetallePK(Integer.parseInt(idFactura),
                                            Integer.parseInt(idArticulo));
        Detalle detalle = em.find(Detalle.class, detallePK);
        em.merge(detalle);
        em.remove(detalle);
        return Response.ok("true").build();
    }

    /*
    Test
    ?idFactura=1&idArticulo=21
     */
    @GET
    @Path("/getAll")
    public Response getAll() {
        List<Detalle> lista_detalles = new ArrayList<>();
        lista_detalles = em.createNamedQuery("Detalle.findAll").getResultList();
        return Response.ok(lista_detalles).build();        
    }
    
    @GET
    @Path("/getLikeFactura")
    public Response getLikeFactura(@QueryParam("idFactura") int idFactura) {
        List<Detalle> lista_detalles_por_factura = new ArrayList<>();
        lista_detalles_por_factura = em.createQuery("SELECT d FROM Detalle d WHERE d.factura.id = :idFactura", Detalle.class)
                                       .setParameter("idFactura", idFactura)
                                       .getResultList();
        return Response.ok(lista_detalles_por_factura).build();
    }
    /*
    Test
    ?idFactura=1
     */
    
}
