import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import miembros.Miembro;
import miembros.TipoDocumento;
import organizaciones.Clasificacion;
import organizaciones.Organizacion;
import organizaciones.Sector;
import organizaciones.TipoOrganizacion;
import transportes.APie;
import transportes.TipoTransportePublico;
import transportes.TransportePublico;
import trayectos.Direccion;
import trayectos.Parada;
import trayectos.Punto;
import trayectos.Tramo;
import trayectos.Trayecto;

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
    public void trayectosCompartidos() {
        Parada p1,p5;
        p1 = new Parada(25);
        p5 = new Parada(20);
        List<Parada> paradas = new ArrayList<Parada>();
        paradas.add(p1);
        paradas.add(p5);
        aPie = new APie();
        TransportePublico colectivo = new TransportePublico(TipoTransportePublico.COLECTIVO,paradas,107);

        Tramo tramoColectivo = new Tramo(colectivo, new Punto(p1), new Punto(p5));

        List<Tramo> tramos = new ArrayList<Tramo>();
        //tramos.add(tramoAPie);
        tramos.add(tramoColectivo);
        Trayecto trayecto = new Trayecto(tramos);

        goku.agregarTrayecto(vegetta,trayecto);
        assertTrue(goku.getTrayectos().contains(trayecto));
    }
}
