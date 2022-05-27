package organizaciones;

import java.util.ArrayList;
import java.util.List;

public class Organizacion {

  private String razonSocial;
  private TipoOrganizacion tipoOrganizacion;
  private String ubicacion;
  private final List<Sector> sectores = new ArrayList<>();
  private Clasificacion clasificacion;

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, String ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public void agregarSector(Sector sector) {
    this.sectores.add(sector);
  }
}
