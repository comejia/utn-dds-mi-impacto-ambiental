package miembros;

import java.util.HashMap;
import java.util.Map;
import organizaciones.Organizacion;
import organizaciones.Sector;
import trayectos.Tramo;

public class Miembro {

  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private int numeroDocumento;

  private final Map<Sector, Organizacion> trabajos = new HashMap<>();

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, int numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public void vincularASector(Sector sector) {
    sector.agregarMiembro(this);
    trabajos.put(sector, sector.getOrganizacion());
  }

  public Map<Sector, Organizacion> devolverTrabajos() {
    return trabajos;
  }

}
