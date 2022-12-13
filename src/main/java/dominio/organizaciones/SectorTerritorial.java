package dominio.organizaciones;

import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class SectorTerritorial extends EntidadPersistente {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "sector_territorial_id")
  private List<Organizacion> organizaciones = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private TipoSectorTerritorial tipoSectorTerritorial;

  private String nombre;

  public SectorTerritorial() {}

  public SectorTerritorial(String nombre, TipoSectorTerritorial sector) {
    this.tipoSectorTerritorial = sector;
    this.nombre = nombre;
  }

  public SectorTerritorial(String nombre, TipoSectorTerritorial sector, List<Organizacion> organizaciones) {
    this.tipoSectorTerritorial = sector;
    this.nombre = nombre;
    this.organizaciones = organizaciones;
  }

  public TipoSectorTerritorial getTipoDeSector() {
    return tipoSectorTerritorial;
  }

  public String getNombre() {
    return nombre;
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
