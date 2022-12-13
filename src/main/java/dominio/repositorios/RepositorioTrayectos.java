package dominio.repositorios;

import dominio.trayectos.Tramo;
import dominio.trayectos.Trayecto;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTrayectos implements WithGlobalEntityManager, Repositorio<Trayecto> {

  public static RepositorioTrayectos instance = new RepositorioTrayectos();

  public void agregar(Trayecto trayecto) {
    entityManager().persist(trayecto);
  }

  public List<Trayecto> listar() {
    return entityManager()
        .createQuery("from Trayecto", Trayecto.class)
        .getResultList();
  }

}
