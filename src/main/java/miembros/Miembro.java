package miembros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import organizaciones.Organizacion;
import organizaciones.Sector;

public class Miembro {

  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private int numeroDocumento;

  private final List<Sector> trabajos = new ArrayList<>();

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, int numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public void vincularASector(Sector sector) {
    sector.agregarMiembro(this);
    trabajos.add(sector);
  }

  public List<Organizacion> devolverOrganizaciones() {
    return trabajos.stream().map(Sector::getOrganizacion).collect(Collectors.toList());
  }

}
