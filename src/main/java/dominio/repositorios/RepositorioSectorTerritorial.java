package dominio.repositorios;

import dominio.Notificador.Contacto;
import dominio.organizaciones.SectorTerritorial;
import dominio.trayectos.Direccion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioSectorTerritorial implements WithGlobalEntityManager {
    public static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    public List<SectorTerritorial> listar() {
        return entityManager()
                .createQuery("from SectorTerritorial", SectorTerritorial.class)
                .getResultList();
    }

    public SectorTerritorial getById(int id_sectorTerritorial) {
        return entityManager().find(SectorTerritorial.class, id_sectorTerritorial);
    }

    public void agregar(SectorTerritorial sectorTerritorial) {
        entityManager().persist(sectorTerritorial);
    }

    public void quitar(SectorTerritorial unSectorTerritorial) {
        entityManager().remove(unSectorTerritorial);
    }

}
