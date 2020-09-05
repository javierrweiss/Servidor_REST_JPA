package ar.org.centro8.curso.java.aplicaciones.jpa.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
import java.util.List;

public interface I_DetalleRepository {
void save(Detalle detalle);
void remove(Detalle detalle);
void update(Detalle detalle);
List<Detalle> getAll();
List<Detalle> getLikeFactura(int idFactura);

default Detalle getbByDetallePK(DetallePK detallePK){
    return getAll()
           .stream()
           .filter(d-> d.getDetallePK().equals(detallePK))
           .findFirst()
           .orElse(new Detalle());
   }

}
