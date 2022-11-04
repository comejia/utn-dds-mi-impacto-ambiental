package dominio.repositorios;

import dominio.transportes.TransportePublico;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTransportePublico implements WithGlobalEntityManager {

  public static RepositorioTransportePublico instance = new RepositorioTransportePublico();
  private final List<TransportePublico> transportes = new ArrayList<>();

  public void agregar(TransportePublico transporte) {
    //entityManager().persist(transporte);
    this.transportes.add(transporte);
  }

  public List<TransportePublico> listar() {
    return transportes;
//    return entityManager()
//        .createQuery("from Transporte", Transporte.class)
//        .getResultList();
  }

  public TransportePublico buscarPorLinea(int linea) {
    System.out.println(linea);
    return transportes.stream()
        .filter(t -> t.getLinea() == linea)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Transporte inexistente"));
//    return entityManager().createQuery("from TransportePublico where linea = :linea", TransportePublico.class).getSingleResult();
  }

}
