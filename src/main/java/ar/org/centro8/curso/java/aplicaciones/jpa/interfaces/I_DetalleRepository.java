package ar.org.centro8.curso.java.aplicaciones.jpa.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import java.util.List;

public interface I_DetalleRepository {
void save(Detalle detalle);
void remove(Detalle detalle);
void update(Detalle detalle);
List<Detalle> getAll();

}
