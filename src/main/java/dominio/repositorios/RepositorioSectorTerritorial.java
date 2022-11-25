package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Direccion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;


public class RepositorioSectorTerritorial implements WithGlobalEntityManager {

    public static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    SectorTerritorial buenosAires = new SectorTerritorial(TipoSectorTerritorial.PROVINCIA);

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
        return sectores;
    }
    public SectorTerritorial buscarSector(int id) {
            return entityManager().find(SectorTerritorial.class, id);
        }
    }

