package db;

import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import transportes.TipoTransportePublico;
import transportes.TransportePublico;
import trayectos.Parada;

import java.util.ArrayList;
import java.util.List;

public class TransporteTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  void sePuedePersistirUnTransportePublico(){
    List<Parada> paradas = new ArrayList<>();
    paradas.add(new Parada(20));
    TransportePublico colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO,paradas,160);
    entityManager().persist(colectivo);
  }
}
