package miembros;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import excepciones.NoPerteneceAOrganizacionException;
import excepciones.NoPuedoCompartirMiTrayecto;
import organizaciones.Organizacion;
import organizaciones.Sector;
import organizaciones.TipoConsumo;
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
  public double getHcTotal() {
    return this.trayectos.stream().mapToDouble(trayecto->trayecto.calcularHcTotal()).sum();
  }

  public double getHC(String unidad) {
    
    return trayectos.stream().mapToDouble(trayecto -> trayecto.getHC(unidad)).sum();
  }
  
  public double getHCRespectoOrganizacion(Organizacion org, String unidad) {
    List<Sector> organizaciones = this.trabajos.stream().filter(sector -> sector.perteneceAOrganizacion(org)).collect(Collectors.toList());
    if( organizaciones.isEmpty() ) {
      throw new NoPerteneceAOrganizacionException("El miembro no pertenece a la organizacion");
    }
    return this.getHC(unidad) / org.getHCTotal(unidad);
  }
}
