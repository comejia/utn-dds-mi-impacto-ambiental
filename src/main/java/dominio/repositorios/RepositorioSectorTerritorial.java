package dominio.repositorios;
import dominio.organizaciones.*;
import funciones.ContenidoReportes;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepositorioSectorTerritorial implements WithGlobalEntityManager {

    public static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    SectorTerritorial buenosAires = new SectorTerritorial("Buenos Aires",TipoSectorTerritorial.PROVINCIA);
    SectorTerritorial formosa = new SectorTerritorial("Formosa",TipoSectorTerritorial.PROVINCIA);
    SectorTerritorial jujuy = new SectorTerritorial("Jujuy",TipoSectorTerritorial.PROVINCIA);

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
        List<SectorTerritorial> sectores = new ArrayList<>();
        sectores.add(buenosAires);
        sectores.add(formosa);
        sectores.add(jujuy);
        return sectores;
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

