package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_FacturaRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.FacturaRepository;
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

@Path("facturas/v2")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class FacturaServicio {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    I_FacturaRepository fr = new FacturaRepository(emf.createEntityManager());
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response info(){
        return Response.ok("Servicio de Facturas activo").build();
    }
    
    @GET
    @Path("/alta")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alta(@QueryParam("letra") Character letra,
                       @QueryParam("numero") String numero,
                       @QueryParam("fecha") String fecha,
                       @QueryParam("monto") String monto,
                       @QueryParam("idCliente") int idCliente){
        I_ClienteRepository cr = new ClienteRepository(emf.createEntityManager());    
        Cliente cliente = cr.getById(idCliente);
        Factura factura = new Factura(letra,
                                      Integer.parseInt(numero),
                                      fecha,
                                      Double.parseDouble(monto),
                                      cliente);
        try {
        fr.save(factura);    
        } catch (Exception e) {
        return Response.ok("false").build();
        }
                
        return Response.ok("true").build();
    }
    
    /*
    Test
    ?letra=A&numero=15&fecha=21022019&monto=9000&idCliente=1
    */
    
    @GET
    @Path("/baja")
    @Produces(MediaType.APPLICATION_JSON)
    public Response baja(@QueryParam("letra") Character letra, @QueryParam("numero") int numero){
        Factura factura = fr.getByLetra_Numero(letra, numero);
        try {
            fr.remove(factura);
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Factura> lista_facturas = fr.getAll();
        return Response.ok(lista_facturas).build();
    }
    
    @GET
    @Path("/getLikeCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLikeCliente(@QueryParam("idCliente") int idCliente){
        I_ClienteRepository cr = new ClienteRepository(emf.createEntityManager());
        Cliente cliente =cr.getById(idCliente);
        List<Factura> lista_facturas_por_cliente = fr.getLikeCliente(cliente);
        return Response.ok(lista_facturas_por_cliente).build();
    }

    /*
    Test
    ?idCliente=2
    */
}
