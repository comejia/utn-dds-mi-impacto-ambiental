package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Punto;
import dominio.trayectos.Tramo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;


public class RepositorioPuntos implements WithGlobalEntityManager {

  public static RepositorioPuntos instance = new RepositorioPuntos();

  public void agregar(Punto punto) {
    entityManager().persist(punto);
  }

  public static RepositorioPuntos getInstance() {
    if (instance == null) {
      instance = new RepositorioPuntos();
    }
    return instance;
  }

  public List<Punto> listar() {
    return entityManager()
        .createQuery("from Punto", Punto.class)
        .getResultList();
  }

  public Punto buscarPunto(int id) {
    return entityManager().find(Punto.class, id);
  }

}

