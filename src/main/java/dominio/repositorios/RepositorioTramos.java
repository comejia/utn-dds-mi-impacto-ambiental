package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Tramo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;


public class RepositorioTramos implements WithGlobalEntityManager {

  public static RepositorioTramos instance = new RepositorioTramos();

  public void agregar(Tramo tramo) {
    entityManager().persist(tramo);
  }

  public static RepositorioTramos getInstance() {
    if (instance == null) {
      instance = new RepositorioTramos();
    }
    return instance;
  }

  public List<Tramo> listar() {
    return entityManager()
        .createQuery("from Tramo", Tramo.class)
        .getResultList();
  }

  public Tramo buscarTramo(int id) {
    return entityManager().find(Tramo.class, id);
  }

}

