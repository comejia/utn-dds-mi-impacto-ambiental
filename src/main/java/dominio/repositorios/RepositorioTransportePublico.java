package dominio.repositorios;

import dominio.transportes.TransportePublico;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioTransportePublico implements WithGlobalEntityManager, Repositorio<TransportePublico> {

  public static RepositorioTransportePublico instance = new RepositorioTransportePublico();

  public void agregar(TransportePublico transporte) {
    entityManager().persist(transporte);
  }

  public List<TransportePublico> listar() {
    return entityManager()
        .createQuery("from TransportePublico", TransportePublico.class)
        .getResultList();
  }

  public TransportePublico buscarPorLinea(int linea) {
    return entityManager().createQuery("from TransportePublico t where t.linea = :linea", TransportePublico.class)
        .setParameter("linea", linea)
        .getResultList()
        .get(0);
  }

}
