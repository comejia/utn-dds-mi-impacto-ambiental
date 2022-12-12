package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Punto;
import funciones.ContenidoReportes;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepositorioSector implements WithGlobalEntityManager {

  public static RepositorioSector instance = new RepositorioSector();

  public void agregar(Sector sector) {
    entityManager().persist(sector);
  }

  public static RepositorioSector getInstance() {
    if (instance == null) {
      instance = new RepositorioSector();
    }
    return instance;
  }

  public List<Sector> listar() {
    return entityManager()
        .createQuery("from Sector", Sector.class)
        .getResultList();
  }

  public Sector buscarSector(int id) {
    return entityManager().find(Sector.class, id);
  }
}

