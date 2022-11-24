import dominio.excepciones.UnidadIncompatibleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.organizaciones.FactorEmision;
import dominio.organizaciones.TipoConsumo;

public class FactorDeEmisionTest {

  private TipoConsumo gasNatural;
  private TipoConsumo electricidad;

  private TipoConsumo cinta;

  @BeforeEach
  public void setup() {
    this.gasNatural = new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1);
    this.electricidad = new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2);
    this.cinta = new TipoConsumo("Electricidad", "m", "Pegar", 1);
  }

  @Test
  public void factorDeEmisionEsCompatibleConDeterminadoTipoDeConsumo() {
    Assertions.assertDoesNotThrow(() -> new FactorEmision(10, "kgCO2eq/kWh", this.electricidad));
  }

  @Test
  public void factorDeEmisionDiscriminaUnidadesParecidas() {
    Assertions.assertDoesNotThrow(() -> new FactorEmision(10, "kgCO2eq/m", this.cinta));
    Assertions.assertThrows(UnidadIncompatibleException.class, () -> new FactorEmision(10, "kgCO2eq/m3", this.cinta));
  }

  @Test
  public void factorDeEmisionNoCompatibleConElTipoDeConsumoLanzaException() {
    Assertions.assertThrows(UnidadIncompatibleException.class,
        () -> new FactorEmision(10, "kgCO2eq/kWh", this.gasNatural));
  }

  @Test
  public void factorDeEmisionQueCambiaDeValorAUnoCompatibleNoLanzaException() {
    FactorEmision factorEmision = new FactorEmision(10, "kgCO2eq/kWh", this.electricidad);

    Assertions.assertDoesNotThrow(() -> factorEmision.cambiarValor(20, "gCO2eq/kWh"));
  }

  @Test
  public void factorDeEmisionQueCambiaDeValorAUnoNoCompatibleLanzaException() {
    FactorEmision factorEmision = new FactorEmision(10, "kgCO2eq/kWh", this.electricidad);

    Assertions.assertThrows(UnidadIncompatibleException.class, () -> factorEmision.cambiarValor(20, "gCO2eq/m3"));
  }
}
