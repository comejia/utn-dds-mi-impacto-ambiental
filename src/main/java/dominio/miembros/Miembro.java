package dominio.miembros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.excepciones.NoPerteneceAOrganizacionException;
import dominio.trayectos.Trayecto;
import dominio.excepciones.NoPuedoCompartirMiTrayecto;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.Sector;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;

@Entity
public class Miembro extends EntidadPersistente {

  private String nombre;
  private String apellido;

  @Enumerated(EnumType.STRING)
  private TipoDocumento tipoDocumento;
  private int numeroDocumento;

  @ManyToMany(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
  })
  @JoinTable(
      name = "Miembros_x_Sector",
      joinColumns = {@JoinColumn(name = "miembroId")},
      inverseJoinColumns = {@JoinColumn(name = "sectorId")}
  )
  private final List<Sector> trabajos = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "trayectoId")
  private List<Trayecto> trayectos = new ArrayList<>();

  public Miembro() {}

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, int numeroDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
  }

  public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, int numeroDocumento, List<Trayecto> trayectos) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.trayectos = trayectos;
  }

  public void vincularASector(Sector sector) {
    trabajos.add(sector);
  }

  public List<Sector> getSector() {
    return this.trabajos;
  }

  public List<Trayecto> getTrayectos() {
    return this.trayectos;
  }

  public void agregarTrayecto(Miembro miembro, Trayecto trayecto) {
    if (!trayecto.puedoCompartir() || noCompartoOrganizacionCon(miembro)) {
      throw new
          NoPuedoCompartirMiTrayecto("No se puede compartir este trayecto. Los transportes de sus tramos deben ser Vehículo Particular o Servicio Contratado");
    }

    this.trayectos.add(trayecto);
    miembro.trayectos.add(trayecto);
  }

  public void agregarTrabajo(Sector sector) {
    this.trabajos.add(sector);
  }

  public List<Organizacion> listaOrganizaciones() {
    return this.getSector().stream().map(sector -> sector.getOrganizacion()).collect(Collectors.toList());
  }

  public List<Organizacion> listaOrganizacionesCompartidas(Miembro miembro) {
    return listaOrganizaciones().stream()
        .filter(organizacion -> miembro.listaOrganizaciones().contains(organizacion))
        .collect(Collectors.toList());
  }

  public boolean noCompartoOrganizacionCon(Miembro miembro) {
    return listaOrganizacionesCompartidas(miembro).isEmpty();
  }

  public double getHcTotal(String unidad) {
    return this.trayectos.stream().mapToDouble(trayecto -> trayecto.getHC(unidad)).sum();
  }

  public double getHC(String unidad) {
    return trayectos.stream().mapToDouble(trayecto -> trayecto.getHC(unidad)).sum();
  }

  public double getHCRespectoOrganizacion(Organizacion org, String unidad) {
    List<Sector> organizaciones = this.trabajos.stream().filter(sector -> sector.perteneceAOrganizacion(org)).collect(Collectors.toList());
    if (organizaciones.isEmpty()) {
      throw new NoPerteneceAOrganizacionException("El miembro no pertenece a la organizacion");
    }
    return this.getHC(unidad) / org.getHCTotal(unidad);
  }}
