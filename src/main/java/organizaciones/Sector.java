package organizaciones;

import java.util.ArrayList;
import java.util.List;
import miembros.Miembro;

public class Sector {
  private final Organizacion organizacion;
  private final List<Miembro> miembros = new ArrayList<>();

  public Sector(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public Organizacion getOrganizacion() {
    return this.organizacion;
  }

  public void agregarMiembro(Miembro miembro) {
    this.miembros.add(miembro);
  }

}
