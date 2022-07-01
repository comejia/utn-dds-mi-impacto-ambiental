package organizaciones;

import java.util.ArrayList;
import java.util.List;

public class SectorTerritorial {
    private List<Organizacion> organizaciones = new ArrayList<>();
    private TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial(TipoSectorTerritorial sector) {
        this.tipoSectorTerritorial = sector;
    }

    public TipoSectorTerritorial getTipoDeSector() {
        return tipoSectorTerritorial;
    }

    public double getCalculoHCTotal(String unidad) {
        //ToDo: Sacar el parametro, y agregar funcion dentro de organizacion para saber su HC
        return this.organizaciones.stream().mapToDouble(organizacion -> organizacion.getHCTotal(unidad)).sum();
    }
}
