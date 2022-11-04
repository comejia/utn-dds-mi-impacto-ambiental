package dominio.repositorios;
import dominio.organizaciones.FactorEmision;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFactorEmision {

  private static final RepositorioFactorEmision instance = new RepositorioFactorEmision();
  private final List<FactorEmision> factoresDeEmision = new ArrayList<>();

  public static RepositorioFactorEmision getInstance() {
    return instance;
  }

  public void agregarFactorEmision(FactorEmision fe) {
    this.factoresDeEmision.add(fe);
  }

}
