package dominio.repositorios;

import dominio.trayectos.Trayecto;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTrayectos implements WithGlobalEntityManager {

  public static RepositorioTrayectos instance = new RepositorioTrayectos();
  private final List<Trayecto> trayectos = new ArrayList<>();

  public void agregar(Trayecto trayecto) {
    //entityManager().persist(trayecto);
    this.trayectos.add(trayecto);
  }

  public List<Trayecto> listar() {
    return trayectos;
//    return entityManager()
//        .createQuery("from Trayecto", Trayecto.class)
//        .getResultList();
  }

}
