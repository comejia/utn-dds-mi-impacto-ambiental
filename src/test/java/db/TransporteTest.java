package db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import transportes.*;
import trayectos.Parada;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransporteTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  List<Parada> paradas;
  TransportePublico colectivo;
  VehiculoParticular scaloneta;
  Bicicleta bicicleta;

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
    entityManager().close();
  }

  @Test
  public void sePuedePersistirUnVehicularParticular(){
    persist(scaloneta);
    entityManager().close();
  }

  @Test
  public void sePuedeRecuperarTransportes() {
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(colectivo);
    persist(scaloneta);
    tx.commit();
    assertEquals(2, entityManager().createQuery("from Transporte").getResultList().size());
    entityManager().close();
  }

  @Test
  public void sePuedeRecuperarSoloTransportesPrivados(){
    EntityTransaction tx = entityManager().getTransaction();
    tx.begin();
    entityManager().persist(bicicleta);
    entityManager().persist(colectivo);
    persist(scaloneta);
    tx.commit();
    assertEquals(2, entityManager().createQuery("from TransportePrivado").getResultList().size());
    entityManager().close();
  }

}
