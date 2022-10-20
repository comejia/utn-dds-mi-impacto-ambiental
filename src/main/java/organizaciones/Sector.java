package organizaciones;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import miembros.Miembro;
import usuarios.EntidadPersistente;

import javax.persistence.*;

@Entity
public class Sector extends EntidadPersistente {

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "organizacionId")
  private final Organizacion organizacion;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "Miembros_X_Sector")
  private List<Miembro> miembros;

  public Sector(Organizacion organizacion, List<Miembro> miembros) {
    this.organizacion = organizacion;
    this.organizacion.agregarSector(this);
    this.miembros = miembros;
  }

  public boolean perteneceAOrganizacion(Organizacion org) {
    return this.organizacion.equals(org);
  }

  public Organizacion getOrganizacion() {
    return this.organizacion;
  }

  public void agregarMiembro(Miembro miembro) {
    this.miembros.add(miembro);
  }

  public double getHuellaCarbono(String unidad) {
    return this.miembros.stream().mapToDouble(miembro -> miembro.getHC(unidad)).sum();
  }

  public double getHuellaCarbonoSobreMiembros(String unidad) {
    return this.getHuellaCarbono(unidad) / this.getCantidadIntegrantes();
  }

  public double getCantidadIntegrantes() {
    return miembros.size();
  }
}
