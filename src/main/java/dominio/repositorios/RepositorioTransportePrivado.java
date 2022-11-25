package dominio.repositorios;

import dominio.transportes.Transporte;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTransportePrivado implements WithGlobalEntityManager, Repositorio<Transporte> {

  public static RepositorioTransportePrivado instance = new RepositorioTransportePrivado();

  public void agregar(Transporte transporte) {
    entityManager().persist(transporte);
  }

  public List<Transporte> listar() {
    return entityManager()
        .createQuery("from Transporte", Transporte.class)
        .getResultList();
  }

}
