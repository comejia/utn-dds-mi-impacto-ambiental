package db;

import dominio.transportes.TipoCombustible;
import dominio.transportes.TipoVehiculo;
import dominio.transportes.VehiculoParticular;
import dominio.trayectos.Direccion;
import dominio.trayectos.Punto;
import dominio.trayectos.Tramo;
import dominio.trayectos.Trayecto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrayectoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  VehiculoParticular scaloneta;
  Punto A;
  Punto B;
  Tramo AB;
  Tramo BA;
  List<Tramo> tramos;
  Trayecto trayecto;

  @BeforeEach
  public void init(){
    scaloneta = new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA);

    A = new Punto(new Direccion(1,"Yrigoyen","1234"));
    B = new Punto(new Direccion(1,"Yrigoyen","4321"));

    AB = new Tramo(scaloneta,A,B);
    BA = new Tramo(scaloneta,B,A);

    tramos = new ArrayList<>();
    tramos.add(AB);
    tramos.add(BA);

    trayecto = new Trayecto(tramos);

  }


  @Test
  public void sePuedePersistirUnTramo(){
    entityManager().persist(AB);
    entityManager().close();
  }

  @Test
  public void sePuedePersistirUnTrayecto(){
    entityManager().persist(trayecto);
    entityManager().close();
  }

  @Test
  public void sePuedeRecuperarTramos(){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(AB);
    entityManager().persist(BA);
    tx.commit();
    assertEquals(2, entityManager().createQuery("from Tramo").getResultList().size());
    entityManager().close();
  }

  @Test
  public void sePuedeRecuperarUnTrayecto(){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(trayecto);
    tx.commit();
    Trayecto trayecto1 = entityManager().createQuery("from Trayecto",Trayecto.class).getResultList().get(0);
    assertEquals(trayecto1,trayecto);
  }
}
