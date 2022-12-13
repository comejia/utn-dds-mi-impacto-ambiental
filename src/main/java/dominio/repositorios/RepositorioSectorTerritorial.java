package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.usuarios.Usuario;
import funciones.ContenidoReportes;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepositorioSectorTerritorial implements WithGlobalEntityManager {

    public static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    public void agregar(SectorTerritorial sectorTerritorial) {
        entityManager().persist(sectorTerritorial);
    }

    public static RepositorioSectorTerritorial getInstance() {
        if (instance == null) {
            instance = new RepositorioSectorTerritorial();
        }
        return instance;
    }

    public List<SectorTerritorial> listar() {
      return entityManager()
          .createQuery("from SectorTerritorial", SectorTerritorial.class)
          .getResultList();
    }
    public SectorTerritorial buscarSector(int id) {
            return entityManager().find(SectorTerritorial.class, id);
        }

  public Map<String, Double> hcPorSector(String unidad) {
        List<ContenidoReportes> contenidosAux = new ArrayList<>();
        List<SectorTerritorial> sectores = this.listar();
        Map<String, Double> contenidosFinal;

        sectores.forEach(sector -> {
            ContenidoReportes contenido = new ContenidoReportes(
                sector.getTipoDeSector().toString(),
                sector.getCalculoHCTotal(unidad)
            );
          contenidosAux.add(contenido);
        });

        contenidosFinal = contenidosAux.stream()
        .collect(Collectors.groupingBy(
            ContenidoReportes::getCabecera,
            Collectors.summingDouble(ContenidoReportes::getValor)
        ));

        return contenidosFinal;
  }
}

