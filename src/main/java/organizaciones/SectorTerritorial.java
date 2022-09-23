package organizaciones;

import usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SectorTerritorial extends EntidadPersistente {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organizacion_id")
  private List<Organizacion> organizaciones = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private TipoSectorTerritorial tipoSectorTerritorial;

  public SectorTerritorial(TipoSectorTerritorial sector) {
    this.tipoSectorTerritorial = sector;
  }

  public TipoSectorTerritorial getTipoDeSector() {
    return tipoSectorTerritorial;
  }

  public double getCalculoHCTotal(String unidad) {
    return this.organizaciones.stream().mapToDouble(organizacion -> organizacion.getHCTotal(unidad)).sum();
  }
}
