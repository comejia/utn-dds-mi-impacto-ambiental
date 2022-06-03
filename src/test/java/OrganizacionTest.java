import com.opencsv.exceptions.CsvException;
import excepciones.TipoConsumoInexistente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizaciones.*;

import java.util.ArrayList;
import java.util.List;

public class OrganizacionTest {

  private final List<TipoConsumo> tiposExistentes = new ArrayList<>();
  private Organizacion organizacion;

  @BeforeEach
  public void setup() {
    this.tiposExistentes.add(new TipoConsumo(
        "Gas Natural", "m3", "Combustión fija", 1));
    this.tiposExistentes.add(new TipoConsumo(
        "Electricidad", "kWh", "Electricidad adquirida", 2));
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, "Mozart", Clasificacion.UNIVERSIDAD);
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
}
