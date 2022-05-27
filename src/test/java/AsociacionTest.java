import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import miembros.Miembro;
import miembros.TipoDocumento;
import organizaciones.Clasificacion;
import organizaciones.Organizacion;
import organizaciones.Sector;
import organizaciones.TipoOrganizacion;

public class AsociacionTest {
    Organizacion ministerio = new Organizacion("Ministerio Dr Goku", TipoOrganizacion.GUBERNAMENTAL, "Av.Libertador 2552", Clasificacion.MINISTERIO);
    Sector seguridad = new Sector(ministerio);
    Miembro goku = new Miembro("Son", "Goku", TipoDocumento.DNI, 1525135681);

    @Test
    public void unMiembroConoceSuSector() {
        goku.vincularASector(seguridad);
        assertTrue(goku.getSector().contains(seguridad));
    }

    @Test
    public void unSectorConoceSuOrganizacion() {
        assertEquals(seguridad.getOrganizacion(), ministerio);
    }
}
