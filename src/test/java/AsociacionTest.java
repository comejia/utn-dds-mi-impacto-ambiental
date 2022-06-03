import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import organizaciones.*;
import transportes.*;
import trayectos.*;
import miembros.*;
import java.util.ArrayList;
import java.util.List;

public class AsociacionTest {
    APie aPie;
    Organizacion ministerio = new Organizacion("Ministerio Dr Goku", TipoOrganizacion.GUBERNAMENTAL, "Av.Libertador 2552", Clasificacion.MINISTERIO);
    Sector seguridad = new Sector(ministerio);
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
        Parada p1,p5;
        p1 = new Parada(25);
        p5 = new Parada(20);
        ServicioContratado taxi = new ServicioContratado(TipoServicioContratado.TAXI);
        goku.vincularASector(seguridad);
        vegetta.vincularASector(seguridad);
        Tramo tramoTaxi = new Tramo(taxi, new Punto(p1), new Punto(p5));
        List<Tramo> tramos = new ArrayList<Tramo>();
        tramos.add(tramoTaxi);
        Trayecto trayecto = new Trayecto(tramos);
        goku.agregarTrayecto(vegetta,trayecto);
        assertTrue(goku.getTrayectos().contains(trayecto));
    }

    @Test
    public void trayectosCompartidosVehiculoParticular() {
        Parada p1,p5;
        p1 = new Parada(25);
        p5 = new Parada(20);
        VehiculoParticular moto = new VehiculoParticular(TipoVehiculo.MOTO,TipoCombustible.NAFTA);
        goku.vincularASector(seguridad);
        vegetta.vincularASector(seguridad);
        Tramo tramoMoto= new Tramo(moto, new Punto(p1), new Punto(p5));
        List<Tramo> tramos = new ArrayList<Tramo>();
        tramos.add(tramoMoto);
        Trayecto trayecto = new Trayecto(tramos);
        goku.agregarTrayecto(vegetta,trayecto);
        assertTrue(goku.getTrayectos().contains(trayecto));
    }
}
