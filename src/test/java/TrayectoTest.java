import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import transportes.Parada;
import transportes.TipoTransportePublico;
import transportes.TransportePublico;
import trayectos.Punto;
import trayectos.Tramo;

public class TrayectoTest {
  Parada p1 = new Parada(25);
  Parada p2 = new Parada(23);
  Parada p3 = new Parada(50);
  Parada p4 = new Parada(15);
  Parada p5 = new Parada(20);

  @Test
  public void distanciaTramoColectivo() {
    List<Parada> paradas = new ArrayList<>();
    paradas.add(p1);
    paradas.add(p2);
    paradas.add(p3);
    paradas.add(p4);
    paradas.add(p5);

    TransportePublico colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 55);
    Tramo tramo = new Tramo(colectivo, new Punto(p2), new Punto(p4));
    
    assertEquals(73, tramo.distancia() );
  }
  
  
}
