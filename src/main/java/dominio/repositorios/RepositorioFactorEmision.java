package dominio.repositorios;
import dominio.organizaciones.FactorEmision;
import dominio.organizaciones.Medicion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioFactorEmision implements WithGlobalEntityManager, Repositorio<FactorEmision> {

  public static final RepositorioFactorEmision instance = new RepositorioFactorEmision();

  public void agregar(FactorEmision medicion) {
    entityManager().persist(medicion);
  }

  public List<FactorEmision> listar() {
    return entityManager()
        .createQuery("from Medicion", FactorEmision.class)
        .getResultList();
  }

}
