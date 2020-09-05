package ar.org.centro8.curso.java.aplicaciones.entities;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-05T19:36:04")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, Float> precio;
    public static volatile SingularAttribute<Detalle, Factura> factura;
    public static volatile SingularAttribute<Detalle, Integer> cantidad;
    public static volatile SingularAttribute<Detalle, Articulo> articulo;
    public static volatile SingularAttribute<Detalle, DetallePK> detallePK;

}