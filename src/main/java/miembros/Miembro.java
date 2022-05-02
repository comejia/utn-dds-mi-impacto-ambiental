package miembros;

import organizaciones.Organizacion;
import organizaciones.Sector;

import java.util.HashMap;
import java.util.Map;

public class Miembro {

  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private Integer numeroDocumento;
  private final Map<Sector, Organizacion> trabajos = new HashMap<>();

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public void vincularASector(Sector sector) {
    sector.agregarMiembro(this);
    trabajos.put(sector, sector.getOrganizacion());
  }

}
