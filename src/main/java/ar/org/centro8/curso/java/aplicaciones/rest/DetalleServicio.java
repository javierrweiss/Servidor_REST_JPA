package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_DetalleRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.DetalleRepository;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class DetalleServicio {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("JPAPU");
    I_DetalleRepository dr = new DetalleRepository(emf.createEntityManager());
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response info() {
        return Response.ok("Servicio de Detalles activo").build();
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("idFactura") int idFactura,
            @QueryParam("idArticulo") int idArticulo,
            @QueryParam("precio") String precio,
            @QueryParam("cantidad") String cantidad) {
        DetallePK detallePK = new DetallePK(idFactura,idArticulo);
        Detalle detalle = new Detalle(detallePK,
                Float.parseFloat(precio),
                Integer.parseInt(cantidad));
        try {
            dr.save(detalle);
        } catch (Exception e) {
            return Response.ok("false").build();
        }
        
        return Response.ok("true").build();
        
    }

    /*
    Test
    ?idFactura=1&idArticulo=1&precio=40000&cantidad=200
     */
    @GET
    @Path("/baja")
    @Produces(MediaType.APPLICATION_JSON)
    public Response baja(@QueryParam("idFactura") String idFactura,
                         @QueryParam("idArticulo") String idArticulo) {
        DetallePK detallePK = new DetallePK(Integer.parseInt(idFactura),
                                            Integer.parseInt(idArticulo));
        Detalle detalle = dr.getbByDetallePK(detallePK);
        try {
            dr.remove(detalle);
        } catch (Exception e) {
            return Response.ok("false").build();
        }
        
        return Response.ok("true").build();
    }

    /*
    Test
    ?idFactura=1&idArticulo=1
     */
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Detalle> lista_detalles = dr.getAll();
        return Response.ok(lista_detalles).build();        
    }
    
    @GET
    @Path("/getLikeFactura")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikeFactura(@QueryParam("idFactura") int idFactura) {
        List<Detalle> lista_detalles_por_factura = dr.getLikeFactura(idFactura);
        return Response.ok(lista_detalles_por_factura).build();
    }
    /*
    Test
    ?idFactura=1
     */
    
}
