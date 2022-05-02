package miembros;

import organizacion.Organizacion;
import organizacion.Sector;
import trayectos.Trayecto;

import java.util.HashMap;
import java.util.Map;

public class Miembro {

  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private Integer numeroDocumento;
  private Map<Organizacion, Sector> trabajos = new HashMap<>();

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public void registrarTrayecto(Trayecto trayecto) {
  }
}
