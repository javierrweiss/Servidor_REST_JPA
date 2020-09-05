package ar.org.centro8.curso.java.aplicaciones.jpa.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import java.util.List;

public interface I_FacturaRepository {
void save(Factura factura);
void remove(Factura factura);
void update(Factura factura);
List<Factura> getAll();
List<Factura> getLikeCliente(Cliente IdCliente);
     
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

