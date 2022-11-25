package dominio.repositorios;

import dominio.transportes.Transporte;
import dominio.transportes.TransportePublico;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioTransportes implements WithGlobalEntityManager, Repositorio<Transporte> {

  public static RepositorioTransportes instance = new RepositorioTransportes();

  public void agregar(Transporte transporte) {
    entityManager().persist(transporte);
  }

  public List<Transporte> listar() {
    return entityManager()
        .createQuery("from Transporte", Transporte.class)
        .getResultList();
  }

  public Transporte buscarPorLinea(int linea) {
    return entityManager().createQuery("from Transporte t where t.linea = :linea", Transporte.class)
        .setParameter("linea", linea)
        .getResultList()
        .get(0);
  }

}
