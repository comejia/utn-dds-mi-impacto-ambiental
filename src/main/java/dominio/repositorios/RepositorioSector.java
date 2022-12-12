package dominio.repositorios;

import dominio.organizaciones.Sector;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioSector implements WithGlobalEntityManager {


    public static RepositorioSector instance = new RepositorioSector();


    public void agregar(Sector sector) {
        //entityManager().persistAll(sector.getMiembros()); // arreglar
        entityManager().persist(sector);
    }

    public List<Sector> listar() {
        return entityManager()
                .createQuery("from Sector", Sector.class)
                .getResultList();
    }

    public List<Sector> listar2(String razonSocial){
        return entityManager().createQuery("from Sector s where s.organizacion.razonSocial = :razonSocial", Sector.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList();
    }

    public Sector buscarSectores(String nombre){
        return entityManager().createQuery("from Sector s where s.nombre = :nombre", Sector.class)
                .setParameter("nombre", nombre)
                .getResultList()
                .get(0);
    }
}

