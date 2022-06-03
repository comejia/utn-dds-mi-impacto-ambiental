import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import API.Geodds;
import excepciones.PuntoIncompatibleException;
import transportes.APie;
import transportes.Bicicleta;
import transportes.TipoTransportePublico;
import transportes.TransportePublico;
import trayectos.Direccion;
import trayectos.Parada;
import trayectos.Punto;
import trayectos.Tramo;
import trayectos.Trayecto;

public class TrayectoTest {

  Parada p1, p2, p3, p4, p5;

  APie aPie;
  Bicicleta bici;
  Geodds appi;

  TransportePublico colectivo;

  @BeforeEach
  public void initParadasYPuntos() {
    p1 = new Parada(25);
    p2 = new Parada(23);
    p3 = new Parada(50);
    p4 = new Parada(15);
    p5 = new Parada(20);

    List<Parada> paradas = new ArrayList<>();
    paradas.add(p1);
    paradas.add(p2);
    paradas.add(p3);
    paradas.add(p4);
    paradas.add(p5);

    appi = mock(Geodds.class);
    aPie = new APie();
    aPie.setAppi(appi);

    bici = new Bicicleta();
    bici.setAppi(appi);

    colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 55);

  }

  @Test
  public void paradasInvalidasEnUnTramoEnTransportePublicoLanzanUnaException() {
    Parada paradaInvalida = new Parada(10);

    Tramo tramoEnColectivo = new Tramo(colectivo, new Punto(p3), new Punto(paradaInvalida));

    assertThrows(PuntoIncompatibleException.class, () -> tramoEnColectivo.distancia());
  }

  @Test
  public void distanciaTramoColectivo() throws JsonProcessingException {

    Tramo tramoColectivo = new Tramo(colectivo, new Punto(p2), new Punto(p4));

    assertEquals(73, tramoColectivo.distancia());
  }

  @Test
  public void distanciaAPieSeCalculaConAppiExterna() throws JsonProcessingException {

    Punto puntoInicio = new Punto(new Direccion("Lugano", "Mozart", "4000"));
    Punto puntoDestino = new Punto(new Direccion("Lugano", "Mozart", "3500"));

    when(appi.getDistancia(puntoInicio.getDireccion(), puntoDestino.getDireccion())).thenReturn(500.0);

    Tramo tramoAPie = new Tramo(aPie, puntoInicio, puntoDestino);

    assertEquals(500.0, tramoAPie.distancia());
  }

  @Test
  public void distanciaTotalTrayectoUnTramoEnColectivoUnoAPieYUnoEnBicicleta() throws JsonProcessingException {
    Punto puntoInicioBici = new Punto(new Direccion("Flores", "Rivadavia", "11000"));
    Punto puntoDestinoBici = new Punto(new Direccion("Flores", "Rivadavia", "8000"));

    when(appi.getDistancia(puntoInicioBici.getDireccion(), puntoDestinoBici.getDireccion())).thenReturn(3000.0);

    Tramo tramoBici = new Tramo(bici, puntoInicioBici, puntoDestinoBici);

    Punto puntoInicioAPie = new Punto(new Direccion("Villa del Parque", "Nazca", "3500"));
    Punto puntoDestinoAPie = new Punto(new Direccion("Villa del Parque", "Nazca", "4100"));

    when(appi.getDistancia(puntoInicioAPie.getDireccion(), puntoDestinoAPie.getDireccion())).thenReturn(600.0);

    Tramo tramoAPie = new Tramo(aPie, puntoInicioAPie, puntoDestinoAPie);

    Tramo tramoColectivo = new Tramo(colectivo, new Punto(p1), new Punto(p5));

    List<Tramo> tramos = new ArrayList<Tramo>();
    tramos.add(tramoBici);
    tramos.add(tramoAPie);
    tramos.add(tramoColectivo);

    Trayecto trayecto = new Trayecto(tramos);

    assertEquals(3713.0, trayecto.distanciaTotal());
  }
}