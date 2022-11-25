package dominio.repositorios;
import dominio.organizaciones.Clasificacion;
import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoOrganizacion;
import dominio.trayectos.Direccion;
import dominio.usuarios.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
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

    public Organizacion buscarOrganizacion(String razonSocial) {
        System.out.print(razonSocial);
        return entityManager()
                .createQuery("from Organizacion O where O.razonSocial =:razonSocial", Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList().get(0);
    }
    public Organizacion buscarOrganizacion(int id) {
            return entityManager().find(Organizacion.class, id);
        }
    }

