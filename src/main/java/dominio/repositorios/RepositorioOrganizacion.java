package dominio.repositorios;
import dominio.organizaciones.Clasificacion;
import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoOrganizacion;
import dominio.trayectos.Direccion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacion implements WithGlobalEntityManager {

    public static RepositorioOrganizacion instance = new RepositorioOrganizacion();

    Organizacion UTN = new Organizacion(
            "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);

    public void agregar(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }

    public List<Organizacion> listar() {
        List<Organizacion> organizaciones = new ArrayList<>();
        organizaciones.add(UTN);
        return organizaciones;
    }

    public Organizacion buscarOrganizacion(int id) {
        return entityManager()
                .createQuery("from Organizacion where id = :id", Organizacion.class)
                .getResultList().get(0);
    }
}
