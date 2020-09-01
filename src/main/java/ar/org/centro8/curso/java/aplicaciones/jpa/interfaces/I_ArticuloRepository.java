package ar.org.centro8.curso.java.aplicaciones.jpa.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ArticuloRepository {
void save(Articulo articulo);
void remove(Articulo articulo);
void update(Articulo articulo);
List<Articulo> getAll();

default List<Articulo> getLikeDescripcion(String descripcion){
   return getAll()
          .stream()
          .filter(d -> d.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
          .collect(Collectors.toList());
}

default Articulo getById(int id){
return getAll()
       .stream()
       .filter(i -> i.getId()==id)
       .findFirst()
       .orElse(new Articulo());
}
/**
 * Este método y el siguiente obtienen precios ubicados en el rango determinado por los parámetros 
 * costoMin y costoMax
 * @param costoMin
 * @param costoMax
 * @return 
 */
default List<Articulo> getByCosto(float costoMin, float costoMax){
 return getAll()
       .stream()
       .filter(c -> c.getCosto() > costoMin && c.getCosto() < costoMax)
       .collect(Collectors.toList());
}

default List<Articulo> getByPrecio(float precioMin, float precioMax){
return getAll()
       .stream()
       .filter(p -> p.getPrecio() > precioMin && p.getPrecio() < precioMax)
       .collect(Collectors.toList());
}


}

    
