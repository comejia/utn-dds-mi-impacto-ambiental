package organizaciones;

public class Sector {

  private final Organizacion organizacion;

  public Sector(Organizacion organizacion) {
    this.organizacion = organizacion;
    this.organizacion.agregarSector(this);
  }

  public Organizacion getOrganizacion() {
    return this.organizacion;
  }

}
