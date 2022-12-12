package dominio.organizaciones;

import dominio.Notificador.Contacto;
import dominio.excepciones.TipoConsumoInexistente;
import dominio.Notificador.Notificador;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;
import lombok.Getter;

import java.util.stream.Collectors;
import javax.persistence.*;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Organizacion extends EntidadPersistente {

  private String razonSocial;
  @Enumerated(EnumType.STRING)
  private TipoOrganizacion tipoOrganizacion;

  @Transient
  private List<Double> hcTotal = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  private Direccion ubicacion;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organizacionId")
  private final List<Sector> sectores = new ArrayList<>();


  @Enumerated(EnumType.STRING)
  private Clasificacion clasificacion;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organizacionId")
  private final List<Medicion> mediciones = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "organizacionId")
  private final List<Contacto> contactos = new ArrayList<>();

  @Transient
  private final List<Notificador> notificadores = new ArrayList<>();


  public Organizacion(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public Organizacion() {

  }

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Direccion ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public TipoOrganizacion getTipoOrganizacion() {
    return tipoOrganizacion;
  }

  public List<Double> getHc() {
    return this.hcTotal;
  }
  public void agregarSector(Sector sector) {
    this.sectores.add(sector);
  }

  public void agregarContacto(Contacto contacto) {
    this.contactos.add(contacto);
  }

  public void agregarNotificador(Notificador notificador) {
    this.notificadores.add(notificador);
  }

  public void quitarNotificador(Notificador notificador) {
    this.notificadores.remove(notificador);
  }

  public void cargarMediciones(String path, List<TipoConsumo> tiposExistentes) throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(path));
    List<String[]> filas = reader.readAll();
    filas.forEach(fila -> {
      TipoConsumo tipoConsumo = tiposExistentes.stream()
          .filter(tipo -> tipo.esMismoTipo(fila[0]))
          .findFirst()
          .orElseThrow(() -> new TipoConsumoInexistente("El Tipo de Consumo leido debe existir en el sistema"));
      agregarMedicion(new Medicion(tipoConsumo, new BigDecimal(fila[1]), fila[2], fila[3]));
    });
    reader.close();
  }

  public void agregarMedicion(Medicion medicion) {
    this.mediciones.add(medicion);
  }

  public double getHCTotal(String unidad) {
    hcTotal.add(this.sectores.stream().mapToDouble(sector -> sector.getHuellaCarbono(unidad)).sum());
    return this.sectores.stream().mapToDouble(sector -> sector.getHuellaCarbono(unidad)).sum();
  }
  public List<Double> getHCPorUnidad(List<String> unidades) {
    return unidades.stream().map(unidad -> getHCTotal(unidad)).collect(Collectors.toList());
  }


  public Double sumaDeHc(List<String> unidades) {
    return getHCPorUnidad(unidades).stream().mapToDouble(hc -> hc).sum();
  }
  public List<Double> getHCPorPorcentaje(List<String> unidades) {
    return getHCPorUnidad(unidades).stream().map(hc ->  hc *100 / sumaDeHc(unidades)).collect(Collectors.toList());
  }
  public void notificarUnContacto(Contacto contacto, String asunto, String contenido) {
    this.notificadores.forEach(notificador -> notificador.notificar(contacto, asunto, contenido));
  }

  public void notificarGuiaRecomendaciones() {
    this.contactos.forEach(contacto -> notificarUnContacto(contacto, "Guia de recomendaciones", "link"));
  }

}
