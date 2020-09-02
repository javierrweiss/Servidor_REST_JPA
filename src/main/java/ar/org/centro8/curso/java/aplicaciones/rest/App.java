package ar.org.centro8.curso.java.aplicaciones.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class App extends Application{
    
    
    @Override
    public Set<Class<?>> getClasses(){
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ArticuloServicio.class);
        classes.add(FacturaServicio.class);
        classes.add(ClienteServicio.class);
        classes.add(DetalleServicio.class);
        return classes;
    }
}
