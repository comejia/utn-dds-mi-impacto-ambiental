package dominio.repositorios;
import dominio.miembros.Miembro;
import dominio.organizaciones.*;
import dominio.trayectos.Tramo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;


public class RepositorioMiembros implements WithGlobalEntityManager {

  public static RepositorioMiembros instance = new RepositorioMiembros();

  public void agregar(Miembro miembro) {
    entityManager().persist(miembro);
  }

  public static RepositorioMiembros getInstance() {
    if (instance == null) {
      instance = new RepositorioMiembros();
    }
    return instance;
  }

  public List<Miembro> listar() {
    return entityManager()
        .createQuery("from Miembro", Miembro.class)
        .getResultList();
  }

  public Miembro buscarMiembro(int id) {
    return entityManager().find(Miembro.class, id);
  }

}

