package ar.org.centro8.curso.java.aplicaciones.test;
import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.aplicaciones.entities.DetallePK;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_DetalleRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.interfaces.I_FacturaRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.DetalleRepository;
import ar.org.centro8.curso.java.aplicaciones.jpa.repositories.FacturaRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class TestRepository {
/*
    Para que este test funcione hay que configurar una non-jta-datasource en el persistence .xmlque igualmente no estaríamos usando.
    */
    public static void main(String[] args) {
//        EntityManagerFactory emf =Persistence.createEntityManagerFactory("JPAPU");
//        I_ClienteRepository cr=new ClienteRepository(emf.createEntityManager());
//        cr.getById(1).getFacturaList().forEach(System.out::println);
//        Cliente cliente=new Cliente("Juan", "Mosa", "DNI", "123336", "Las Palmas 501", "Ninguno");
//        cr.save(cliente);
//        Cliente cliente1=new Cliente("Juliana", "Marrone", "DNI", "123339", "Modena 455", "Ninguno");
//        cr.save(cliente1);
//        Cliente cliente2=new Cliente("Dina", "Hermanos", "DNI", "6015489", "Las Carolinas 987", "Ninguno");
//        cr.save(cliente2);
//        Cliente cliente3=new Cliente("Petra", "Carrillo", "DNI", "164860", "Jean Jaures 45", "Ninguno");
//        cr.save(cliente3);
//        System.out.println("1: "+cliente+"\n 2: "+cliente1+"\n 3: "+cliente2+"\n 4: "+cliente3);
//        cr.getAll().forEach(System.out::println);
//        
////        Articulo articulo = new Articulo("Pasamontañas",200.50f, 250.00f, 200, 300, 500);
////        ar.save(articulo);
////        Articulo articulo1 = new Articulo("Pipa",450.76f, 500.00f,200, 10, 1000);
////        ar.save(articulo1);
////        Articulo articulo2 = new Articulo("Reproductor",200000.54f, 215000.00f, 20, 3, 100);
////        ar.save(articulo2);
////        Articulo articulo3 = new Articulo("MacBook Air",180000.50f, 200000.23f, 20, 1, 30);
////        ar.save(articulo3);
////        Articulo articulo4 = new Articulo("Escritorio",20340.99f, 25000.00f, 2, 1, 10);
////        ar.save(articulo4);
////        System.out.println("1: "+articulo+"\n 2: "+articulo1+"\n 3: "+articulo2+"\n 4: "+articulo3
////        +"\n 5: "+articulo4);
////        ar.getAll().forEach(System.out::println);
////        
//        I_FacturaRepository fr=new FacturaRepository(emf.createEntityManager());
//        Factura factura = new Factura('A', 1000,"20/03/2020",1000.00, cr.getById(1));
//        fr.save(factura);
//        System.out.println(factura);
//        fr.getAll().forEach(System.out::println);
//        
//        I_DetalleRepository dr=new DetalleRepository(emf.createEntityManager());
//        DetallePK detallePK = new DetallePK(factura.getId(), articulo.getId());
//        Detalle detalle = new Detalle(detallePK, 1000.00f, 10);
//        dr.save(detalle);
//        System.out.println(detalle);
//        dr.getAll().forEach(System.out::println);
   }

    
}