import excepciones.UnidadIncompatibleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizaciones.FactorEmision;
import organizaciones.TipoConsumo;

public class FactorDeEmisionTest {

  private TipoConsumo gasNatural;
  private TipoConsumo electricidad;

  private TipoConsumo cinta;

  @BeforeEach
  public void setup() {
    this.gasNatural = new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1);
    this.electricidad = new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2);
    this.cinta = new TipoConsumo("Electricidad","m","Pegar",1);
  }

  @Test
  public void factorDeEmisionEsCompatibleConDeterminadoTipoDeConsumo() {
    Assertions.assertDoesNotThrow(() -> new FactorEmision(10, "kgCO2eq/kWh", this.electricidad));
  }

  @Test
  public void factorDeEmisionDiscriminaUnidadesParecidas() {
    Assertions.assertDoesNotThrow(() -> new FactorEmision(10, "m", this.cinta));
    Assertions.assertThrows(UnidadIncompatibleException.class, () -> new FactorEmision(10, "m3", this.cinta));
  }
  @Test
  public void factorDeEmisionNoCompatibleConElTipoDeConsumoLanzaException() {
    Assertions.assertThrows(UnidadIncompatibleException.class,
        () -> new FactorEmision(10, "kgCO2eq/kWh", this.gasNatural));
  }
}
