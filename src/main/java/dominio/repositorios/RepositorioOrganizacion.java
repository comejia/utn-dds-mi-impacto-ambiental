package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Direccion;
import dominio.usuarios.Usuario;
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
            return entityManager().find(Organizacion.class, id);
        }
    public Organizacion buscarOrganizacion(String razonSocial) {

        return entityManager().createQuery("from Organizaion O where O.razonSocial = :razonSocial", Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList()
                .get(0);
    }
    }

