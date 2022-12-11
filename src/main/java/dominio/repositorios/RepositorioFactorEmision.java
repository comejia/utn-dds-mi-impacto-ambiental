package dominio.repositorios;
import dominio.organizaciones.FactorEmision;
import dominio.organizaciones.SectorTerritorial;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioFactorEmision implements WithGlobalEntityManager {

  public static RepositorioFactorEmision instance = new RepositorioFactorEmision();

  private final List<FactorEmision> factoresDeEmision = new ArrayList<>();

  public static RepositorioFactorEmision getInstance() {
    if (instance == null) {
      instance = new RepositorioFactorEmision();
    }
    return instance;
  }

  public List<FactorEmision> listar() {
    return entityManager()
        .createQuery("from FactorEmision", FactorEmision.class)
        .getResultList();
  }

  public void agregarFactorEmision(FactorEmision fe) {
    this.factoresDeEmision.add(fe);
  }

}
