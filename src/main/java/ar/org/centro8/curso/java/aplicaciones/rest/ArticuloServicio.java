package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.ArticuloRepository;
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


@Path("/articulos/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ArticuloServicio {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    private final I_ArticuloRepository ar =new ArticuloRepository(emf.createEntityManager());
       
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
        try {
            ar.save(articulo);
        } catch (Exception e) {
            return Response.ok(0).build();
        }
        
       return Response.ok(articulo.getId()).build();
    }
    
    /*
    Test
    ?descripcion=Guantes&costo=1400&precio=2000&stock=120&stockMin=20&stockMax=200
    */
    
    @GET
    @Path("/baja")
    @Produces(MediaType.TEXT_HTML)
    public Response baja(@QueryParam("id") int id){
        Articulo articulo = ar.getById(id);
        try {
            ar.remove(articulo);
        } catch (Exception e) {
            return Response.ok("false").build();
        }
        
        return Response.ok("true").build();
       }
        
    /*
    Test
    ?id=3
    */
    
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Articulo> lista_articulos = ar.getAll();
        return Response.ok(lista_articulos).build();
    }
    
    
    @GET
    @Path("/getLikeDescripcion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikeDescripcion(@QueryParam("descripcion") String descripcion){
                List<Articulo> lista_articulo = ar.getLikeDescripcion(descripcion);
                return Response.ok(lista_articulo).build();
    }
    
    /*
    Test
    ?descripcion=Gorila
    */
   
}
