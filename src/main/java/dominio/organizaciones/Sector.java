package dominio.organizaciones;

import java.util.List;

import dominio.miembros.Miembro;
import dominio.usuarios.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Sector extends EntidadPersistente {

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "organizacionId")
  private Organizacion organizacion;

  @ManyToMany(mappedBy = "trabajos")
  private List<Miembro> miembros;

  private String nombre;

  public Sector() {}

  public Sector(Organizacion organizacion, List<Miembro> miembros, String nombre) {
    this.organizacion = organizacion;
    this.organizacion.agregarSector(this);
    this.miembros = miembros;
    this.nombre = nombre;
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

  public List<Miembro> getMiembros() {
    return miembros;
  }
}
