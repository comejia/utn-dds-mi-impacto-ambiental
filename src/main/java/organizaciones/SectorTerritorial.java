package organizaciones;

import java.util.ArrayList;
import java.util.List;

public class SectorTerritorial {
    private List<Organizacion> organizaciones = new ArrayList<>();
    private TipoSectorTerritorial tipoSectorTerritorial;

    public double getCalculoHCTotal(TipoConsumo tipo) {
        //ToDo: Sacar el parametro, y agregar funcion dentro de organizacion para saber su HC
        return this.organizaciones.stream().mapToDouble(organizacion -> organizacion.getCalculoHCTotal(tipo)).sum();
    }
}
