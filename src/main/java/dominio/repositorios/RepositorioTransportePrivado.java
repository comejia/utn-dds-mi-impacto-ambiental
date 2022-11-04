package dominio.repositorios;

import dominio.transportes.Transporte;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTransportePrivado implements WithGlobalEntityManager {

  public static RepositorioTransportePrivado instance = new RepositorioTransportePrivado();
  private final List<Transporte> transportes = new ArrayList<>();

  public void agregar(Transporte transporte) {
    //entityManager().persist(transporte);
    this.transportes.add(transporte);
  }

  public List<Transporte> listar() {
    return transportes;
//    return entityManager()
//        .createQuery("from Transporte", Transporte.class)
//        .getResultList();
  }

}
