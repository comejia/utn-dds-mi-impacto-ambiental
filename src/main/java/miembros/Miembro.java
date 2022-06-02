package miembros;

import java.util.ArrayList;
import java.util.List;

import excepciones.NoPuedoCompartirMiTrayecto;
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

  public void agregarTrayecto(Miembro miembro, Trayecto trayecto) {
    if(trayecto.puedoCompartir()) {
    this.trayectos.add(trayecto);
    miembro.trayectos.add(trayecto);}
    else throw new 
    NoPuedoCompartirMiTrayecto("Imposible compartir este trayecto pues todos los transportes de sus tramos no cumplen ser vehículo particular o servicio contratado");  
  }
}
