package dominio.repositorios;

import dominio.organizaciones.Medicion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMediciones implements WithGlobalEntityManager {

  public static RepositorioMediciones instance = new RepositorioMediciones();
  private final List<Medicion> mediciones = new ArrayList<>();

  public void agregar(Medicion medicion) {
    //entityManager().persist(medicion);
    this.mediciones.add(medicion);
  }

  public List<Medicion> listar() {
    return mediciones;
//    return entityManager()
//        .createQuery("from Medicion", Medicion.class)
//        .getResultList();
  }

}
