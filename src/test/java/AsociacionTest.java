import controllers.VinculacionController;
import dominio.excepciones.NoPuedoCompartirMiTrayecto;
import dominio.miembros.Miembro;
import dominio.miembros.TipoDocumento;
import dominio.organizaciones.*;
import dominio.repositorios.RepositorioUsuarios;
import dominio.repositorios.RepositorioVinculaciones;
import dominio.transportes.*;
import dominio.trayectos.*;
import dominio.usuarios.Administrador;
import dominio.usuarios.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  APie aPie;
VinculacionController controlador = new VinculacionController();
  Direccion direccionMinisterio = new Direccion("Capital", "Av.Libertador", "2552");

  Direccion direccionEstudio = new Direccion("Capital", "Antezana", "247");
  Organizacion ministerio = new Organizacion("Ministerio Dr Goku", TipoOrganizacion.GUBERNAMENTAL, direccionMinisterio, Clasificacion.MINISTERIO);

  Organizacion estudioContable = new Organizacion("Estudio contable", TipoOrganizacion.EMPRESA, direccionEstudio, Clasificacion.MINISTERIO);
  Sector seguridad = new Sector(ministerio, new ArrayList<>());

  Sector contaduria = new Sector(estudioContable, new ArrayList<>());
  Miembro goku = new Miembro("Son", "Goku", TipoDocumento.DNI, 1525135681);
  Miembro vegetta = new Miembro("Son", "Vegetta", TipoDocumento.DNI, 1333804417);

  @Test
  public void unMiembroConoceSuSector() {
    goku.vincularASector(seguridad);
    assertTrue(goku.getSector().contains(seguridad));
  }

  @Test
  public void unSectorConoceSuOrganizacion() {
    assertEquals(seguridad.getOrganizacion(), ministerio);
  }

  @Test
  public void trayectosCompartidosServicioContratado() {
    Parada p1, p5;
    p1 = new Parada(25);
    p5 = new Parada(20);
    ServicioContratado taxi = new ServicioContratado(TipoServicioContratado.TAXI);
    goku.vincularASector(seguridad);
    vegetta.vincularASector(seguridad);
    Tramo tramoTaxi = new Tramo(taxi, new Punto(p1), new Punto(p5));
    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramoTaxi);
    Trayecto trayecto = new Trayecto(tramos);
    goku.agregarTrayecto(vegetta, trayecto);
    assertTrue(goku.getTrayectos().contains(trayecto));
  }

  @Test
  public void trayectosCompartidosVehiculoParticular() {
    Parada p1, p5;
    p1 = new Parada(25);
    p5 = new Parada(20);
    VehiculoParticular moto = new VehiculoParticular(TipoVehiculo.MOTO, TipoCombustible.NAFTA);
    goku.vincularASector(seguridad);
    vegetta.vincularASector(seguridad);
    Tramo tramoMoto = new Tramo(moto, new Punto(p1), new Punto(p5));
    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramoMoto);
    Trayecto trayecto = new Trayecto(tramos);
    goku.agregarTrayecto(vegetta, trayecto);
    assertTrue(goku.getTrayectos().contains(trayecto));
  }

  @Test
  public void noPuedenCompartirVehiculoParticularSiNoSonDeLaMismaOrganizacion() {
    Parada p1, p5;
    p1 = new Parada(25);
    p5 = new Parada(20);
    VehiculoParticular moto = new VehiculoParticular(TipoVehiculo.MOTO, TipoCombustible.NAFTA);
    goku.vincularASector(seguridad);
    vegetta.vincularASector(contaduria);
    Tramo tramoMoto = new Tramo(moto, new Punto(p1), new Punto(p5));
    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramoMoto);
    Trayecto trayecto = new Trayecto(tramos);
    Assertions.assertThrows(NoPuedoCompartirMiTrayecto.class, () -> goku.agregarTrayecto(vegetta, trayecto));
  }

  @Test
  public void noPuedenCompartirVehiculoSiNoEsTransporteParticularOServicioContratado() {
    Parada p1, p5;
    p1 = new Parada(25);
    p5 = new Parada(20);
    List<Parada> paradas = new ArrayList<>();
    paradas.add(p1);
    paradas.add(p5);
    TransportePublico colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 55);
    goku.vincularASector(seguridad);
    vegetta.vincularASector(seguridad);
    Tramo tramoColectivo = new Tramo(colectivo, new Punto(p1), new Punto(p5));
    List<Tramo> tramos = new ArrayList<>();
    tramos.add(tramoColectivo);
    Trayecto trayecto = new Trayecto(tramos);
    Assertions.assertThrows(NoPuedoCompartirMiTrayecto.class, () -> goku.agregarTrayecto(vegetta, trayecto));
  }
}
