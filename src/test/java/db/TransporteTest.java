package db;

import org.junit.jupiter.api.AfterEach;
import dominio.transportes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import dominio.trayectos.Parada;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransporteTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  List<Parada> paradas;
  TransportePublico colectivo;
  VehiculoParticular scaloneta;
  Bicicleta bicicleta;

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @AfterEach
  public void tearDown() {
    super.tearDown();
  }

  @BeforeEach
  public void init(){
    paradas = new ArrayList<>();
    paradas.add(new Parada(20));
    colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO,paradas,160);

    scaloneta = new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA);

    bicicleta = new Bicicleta();
  }

  @Test
  public void sePuedePersistirUnTransportePublico(){
    entityManager().persist(colectivo);
  }

  @Test
  public void sePuedePersistirUnVehicularParticular(){
    persist(scaloneta);
  }

  @Test
  public void sePuedeRecuperarTransportes() {
    entityManager().persist(colectivo);
    entityManager().persist(scaloneta);

    assertEquals(2, entityManager().createQuery("from Transporte").getResultList().size());
  }

  @Test
  public void sePuedeRecuperarSoloTransportesPrivados(){
    entityManager().persist(colectivo);
    entityManager().persist(scaloneta);

    assertEquals(1, entityManager().createQuery("from TransportePrivado").getResultList().size());
  }

  @Test
  public void sePuedeRecuperarUnTransporte(){
    entityManager().persist(scaloneta);

    Transporte scaloneta1 = entityManager().createQuery("from Transporte",Transporte.class).getResultList().get(0);
    assertEquals(scaloneta1,scaloneta);
  }
}
