import dominio.Notificador.Contacto;
import dominio.excepciones.TipoConsumoInexistente;
import dominio.organizaciones.Clasificacion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.organizaciones.TipoOrganizacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.trayectos.Direccion;

import java.util.ArrayList;
import java.util.List;

public class OrganizacionTest {

  private final List<TipoConsumo> tiposExistentes = new ArrayList<>();
  private Organizacion organizacion;
  private Contacto contacto1;
  private Contacto contacto2;

  @BeforeEach
  public void setup() {
    this.tiposExistentes.add(new TipoConsumo(
        "Gas Natural", "m3", "Combustión fija", 1));
    this.tiposExistentes.add(new TipoConsumo(
        "Electricidad", "kWh", "Electricidad adquirida", 2));
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, new Direccion(1, "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
    this.contacto1 = new Contacto("migue.racedo.oviedo@gmail.com", "+5491155136689");
    this.contacto2 = new Contacto("migueracedooviedo@frba.utn.edu.ar", "+5491155136689");
    organizacion.agregarContacto(contacto1);
    organizacion.agregarContacto(contacto2);
  }

  @Test
  public void cargaDeMedicionesConTiposDeConsumoExistentes() {
    String csv = "src/main/resources/mediciones/datos_actividades.csv";
    Assertions.assertDoesNotThrow(() -> organizacion.cargarMediciones(csv, tiposExistentes));
  }

  @Test
  public void cargaDeMedicionesConTiposDeConsumoInexistenteLanzaException() {
    String csv = "src/main/resources/mediciones/da_tipos_inexistentes.csv";
    Assertions.assertThrows(TipoConsumoInexistente.class, () -> this.organizacion.cargarMediciones(csv, tiposExistentes));
  }

  @Test
  public void notificarContactos() {
    organizacion.notificarGuiaRecomendaciones();
  }
}
