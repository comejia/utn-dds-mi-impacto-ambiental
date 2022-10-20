package organizaciones;

import usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class SectorTerritorial extends EntidadPersistente {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organizacionId")
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
  public List<List<Double>> getHcSector(List<String>unidades){
    return this.organizaciones.stream().map(organizacion -> organizacion.getHCPorPorcentaje(unidades))
            .collect(Collectors.toList());
  }
  public List<List<Double>> getHcEvolucion(){
    return this.organizaciones.stream().map(organizacion -> organizacion.getHc())
            .collect(Collectors.toList());
  }
}
