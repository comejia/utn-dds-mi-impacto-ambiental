import API.Geolocalizacion;
import excepciones.PuntoIncompatibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transportes.APie;
import transportes.Bicicleta;
import transportes.TipoTransportePublico;
import transportes.TransportePublico;
import trayectos.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TrayectoTest {

  Parada p1, p2, p3, p4, p5;
  APie aPie;
  Bicicleta bici;
  Geolocalizacion api;
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

    api = mock(Geolocalizacion.class);
    aPie = new APie();
    aPie.setApi(api);

    bici = new Bicicleta();
    bici.setApi(api);

    colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 55);

  }

  @Test
  public void paradasInvalidasEnUnTramoEnTransportePublicoLanzanUnaException() {
    Parada paradaInvalida = new Parada(10);

    Tramo tramoEnColectivo = new Tramo(colectivo, new Punto(p3), new Punto(paradaInvalida));

    assertThrows(PuntoIncompatibleException.class, () -> tramoEnColectivo.distancia());
  }

  @Test
  public void distanciaTramoColectivo() {

    Tramo tramoColectivo = new Tramo(colectivo, new Punto(p2), new Punto(p4));

    assertEquals(73, tramoColectivo.distancia());
  }

  @Test
  public void distanciaAPieSeCalculaConAppiExterna() {

    Punto puntoInicio = new Punto(new Direccion("Lugano", "Mozart", "4000"));
    Punto puntoDestino = new Punto(new Direccion("Lugano", "Mozart", "3500"));

    when(api.getDistancia(puntoInicio.getDireccion(), puntoDestino.getDireccion())).thenReturn(500.0);

    Tramo tramoAPie = new Tramo(aPie, puntoInicio, puntoDestino);

    assertEquals(500.0, tramoAPie.distancia());
  }

  @Test
  public void distanciaTotalTrayectoUnTramoEnColectivoUnoAPieYUnoEnBicicleta() {
    Punto puntoInicioBici = new Punto(new Direccion("Flores", "Rivadavia", "11000"));
    Punto puntoDestinoBici = new Punto(new Direccion("Flores", "Rivadavia", "8000"));

    when(api.getDistancia(puntoInicioBici.getDireccion(), puntoDestinoBici.getDireccion())).thenReturn(3000.0);

    Tramo tramoBici = new Tramo(bici, puntoInicioBici, puntoDestinoBici);

    Punto puntoInicioAPie = new Punto(new Direccion("Villa del Parque", "Nazca", "3500"));
    Punto puntoDestinoAPie = new Punto(new Direccion("Villa del Parque", "Nazca", "4100"));

    when(api.getDistancia(puntoInicioAPie.getDireccion(), puntoDestinoAPie.getDireccion())).thenReturn(600.0);

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