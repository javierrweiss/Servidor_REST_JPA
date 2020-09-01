package ar.org.centro8.curso.java.aplicaciones.jpa.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import java.util.List;
import java.util.function.Supplier;

public interface I_FacturaRepository {
void save(Factura factura);
void remove(Factura factura);
void update(Factura factura);
List<Factura> getAll();
Supplier<Factura> F= () -> new Factura();
     
default Factura getById(int id){
  return getAll()
         .stream()
         .filter(f-> f.getId()== id)
         .findFirst()
         .orElse(new Factura());
}

//Al ser clave unique, van juntas
default Factura getByLetra_Numero(Character letra, int numero){
 return getAll()
        .stream()
        .filter(l -> l.getLetra()==letra && l.getNumero()==numero)
        .findFirst()
        .orElse(new Factura());
}

default Factura getByFecha(String fecha){
return getAll()
       .stream()
       .filter(f -> f.getFecha().equalsIgnoreCase(fecha))
       .findFirst()
       .orElse(new Factura());
}


}

