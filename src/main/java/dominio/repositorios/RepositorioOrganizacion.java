package dominio.repositorios;
import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioOrganizacion implements WithGlobalEntityManager {

    public static RepositorioOrganizacion instance = new RepositorioOrganizacion();

    public void agregar(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }

    public List<Organizacion> listar() {
        return entityManager()
                .createQuery("from Organizacion", Organizacion.class)
                .getResultList();
    }
}
