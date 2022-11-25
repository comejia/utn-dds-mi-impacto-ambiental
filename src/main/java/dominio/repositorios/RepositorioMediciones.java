package dominio.repositorios;

import dominio.organizaciones.Medicion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioMediciones implements WithGlobalEntityManager, Repositorio<Medicion> {

  public static RepositorioMediciones instance = new RepositorioMediciones();

  public void agregar(Medicion medicion) {
    entityManager().persist(medicion);
  }

  public List<Medicion> listar() {
    return entityManager()
        .createQuery("from Medicion", Medicion.class)
        .getResultList();
  }

}
