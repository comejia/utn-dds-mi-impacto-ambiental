import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import miembros.Miembro;
import miembros.TipoDocumento;
import organizaciones.Clasificacion;
import organizaciones.Organizacion;
import organizaciones.Sector;
import organizaciones.TipoOrganizacion;



public class TestAsociacion {
    Organizacion ministerio = new Organizacion("Ministerio Dr Goku", TipoOrganizacion.GUBERNAMENTAL, "Av.Libertador 2552", Clasificacion.MINISTERIO);
    Sector seguridad = new Sector(ministerio);
    Miembro goku = new Miembro("Son", "Goku", TipoDocumento.DNI, 1525135681);

    @Test
    public void unSectorConoceASuMiembro() {
        ministerio.agregarSector(seguridad);
        seguridad.agregarMiembro(goku);
        
        assertEquals(seguridad.verMiembros().get(0),goku);
    }

    @Test
    public void unMiembroConoceSuSector() {
        goku.vincularASector(seguridad);
        assertEquals(goku.devolverTrabajos(),seguridad);}
}
