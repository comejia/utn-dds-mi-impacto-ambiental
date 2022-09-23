package organizaciones;

import Notificador.Notificador;
import Notificador.Contacto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import excepciones.TipoConsumoInexistente;
import trayectos.Direccion;
import usuarios.EntidadPersistente;

import javax.persistence.*;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizacion extends EntidadPersistente {

  private String razonSocial;
  @Enumerated(EnumType.STRING)
  private TipoOrganizacion tipoOrganizacion;

  @OneToOne(cascade = CascadeType.ALL)
  private Direccion ubicacion;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "sector_id")
  private final List<Sector> sectores = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private Clasificacion clasificacion;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "medicion_id")
  private final List<Medicion> mediciones = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "contacto_id")
  private final List<Contacto> contactos = new ArrayList<>();

  @Transient
  private final List<Notificador> notificadores = new ArrayList<>();

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Direccion ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
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

  private void agregarMedicion(Medicion medicion) {
    this.mediciones.add(medicion);
  }

  public double getHCTotal(String unidad) {
    return this.sectores.stream().mapToDouble(sector -> sector.getHuellaCarbono(unidad)).sum();
  }

  public void notificarUnContacto(Contacto contacto, String asunto, String contenido) {
    this.notificadores.forEach(notificador -> notificador.notificar(contacto, asunto, contenido));
  }

  public void notificarGuiaRecomendaciones() {
    this.contactos.forEach(contacto -> notificarUnContacto(contacto, "Guia de recomendaciones", "link"));
  }
}
