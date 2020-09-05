package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.ClienteRepository;
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

@Path("clientes/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ClienteServicio {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    private final I_ClienteRepository cr = new ClienteRepository(emf.createEntityManager());
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response info(){
        return Response.ok("Servicio de Clientes activo").build();
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
        try {
            cr.save(cliente);
        } catch (Exception e) {
            return Response.ok(0).build();
        }
        
    return Response.ok(cliente.getId()).build();
    }
    
    /*
    Test
    alta?nombre=Juan&apellido=Moreno&tipoDocumento=DNI&numeroDocumento=1422587&direccion=Hidalgo_2015&comentarios=nada_en_particular
    */
    
    @GET
    @Path("baja")
    @Produces(MediaType.APPLICATION_JSON)
    public Response baja(@QueryParam("id")int id){
        Cliente cliente = cr.getById(id);
        try {
            cr.remove(cliente);
        } catch (Exception e) {
            return Response.ok("false").build();
        }
        
        return Response.ok("true").build();
    }
   /*
    Test
    baja?id=2
    */
    
    
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Cliente> lista_clientes = cr.getAll();
        return Response.ok(lista_clientes).build();
    }
    
    @GET
    @Path("getLikeApellido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikeApellido(@QueryParam("apellido") String apellido){
      List<Cliente> lista_clientes_por_apellido = cr.getLikeApellido(apellido);
        return Response.ok(lista_clientes_por_apellido).build();
        }
    /*
    Test
    getLikeApellido?apellido=Moreno
    */
}
