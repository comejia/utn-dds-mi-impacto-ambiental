package miembros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import excepciones.NoPuedoCompartirMiTrayecto;
import organizaciones.Organizacion;
import organizaciones.Sector;
import trayectos.Trayecto;

public class Miembro {

  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private int numeroDocumento;

  private final List<Sector> trabajos = new ArrayList<>();
  private List<Trayecto> trayectos = new ArrayList<>();

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, int numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public void vincularASector(Sector sector) {
    trabajos.add(sector);
  }

  public List<Sector> getSector() {
    return this.trabajos;
  }
  public List<Trayecto> getTrayectos(){return this.trayectos;}

  public void agregarTrayecto(Miembro miembro, Trayecto trayecto) {
    if(trayecto.puedoCompartir() && !noCompartoOrganizacionCon(miembro)) {
    this.trayectos.add(trayecto);
    miembro.trayectos.add(trayecto);}
    else throw new 
    NoPuedoCompartirMiTrayecto("Imposible compartir este trayecto pues todos los transportes de sus tramos no cumplen ser vehículo particular o servicio contratado");  
  }

  public List<Organizacion> listaOrganizaciones() {
    return this.getSector().stream().map(sector -> sector.getOrganizacion()).collect(Collectors.toList());
  }

  public List<Organizacion> listaOrganizacionesCompartidas(Miembro miembro) {
   return listaOrganizaciones().stream().filter(organizacion -> miembro.listaOrganizaciones().contains(organizacion)).collect(Collectors.toList());
  }
  public boolean noCompartoOrganizacionCon(Miembro miembro) {
  return listaOrganizacionesCompartidas(miembro).isEmpty();
  }
}
