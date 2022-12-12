import dominio.api.Geodds;
import dominio.api.Geolocalizacion;
import dominio.excepciones.PuntoIncompatibleException;
import dominio.organizaciones.FactorEmision;
import dominio.organizaciones.TipoConsumo;
import dominio.trayectos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.transportes.APie;
import dominio.transportes.Bicicleta;
import dominio.transportes.TipoTransportePublico;
import dominio.transportes.TransportePublico;

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
  TipoConsumo electricidad;
  FactorEmision factor1;
  Geodds geodds;

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

    electricidad = new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2);
    factor1 =  new FactorEmision(10, "kgCO2eq/kWh", electricidad);
    colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 55,factor1);

    geodds = new Geodds();

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

    Punto puntoInicio = new Punto(new Direccion(1, "Mozart", "4000"));
    Punto puntoDestino = new Punto(new Direccion(1, "Mozart", "3500"));

    when(api.getDistancia(puntoInicio.getDireccion(), puntoDestino.getDireccion())).thenReturn(500.0);

    Tramo tramoAPie = new Tramo(aPie, puntoInicio, puntoDestino);


    assertEquals(500.0, tramoAPie.distancia());
  }

  @Test
  public void distanciaTotalTrayectoUnTramoEnColectivoUnoAPieYUnoEnBicicleta() {
    Punto puntoInicioBici = new Punto(new Direccion(2, "Rivadavia", "11000"));
    Punto puntoDestinoBici = new Punto(new Direccion(2, "Rivadavia", "8000"));

    when(api.getDistancia(puntoInicioBici.getDireccion(), puntoDestinoBici.getDireccion())).thenReturn(3000.0);

    Tramo tramoBici = new Tramo(bici, puntoInicioBici, puntoDestinoBici);

    Punto puntoInicioAPie = new Punto(new Direccion(3, "Nazca", "3500"));
    Punto puntoDestinoAPie = new Punto(new Direccion(3, "Nazca", "4100"));

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