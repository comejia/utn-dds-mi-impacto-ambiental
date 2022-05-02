package organizacion;

import java.util.List;

public class Organizacion {
  private String razonSocial;
  private TipoOrganizacion tipoOrganizacion;
  private Ubicacion ubicacion;
  private List<Sector> sectores;
  private Clasificacion clasificacion;

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Ubicacion ubicacion, List<Sector> sectores, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
  }
}
