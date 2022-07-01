package organizaciones;

import java.util.List;

import miembros.Miembro;

public class Sector {

  private final Organizacion organizacion;
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
    // TODO Auto-generated method stub
    return (double)miembros.size();
  }
  
}
