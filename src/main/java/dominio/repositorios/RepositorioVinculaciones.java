package dominio.repositorios;

import dominio.organizaciones.Vinculacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Query;
import java.util.List;

public class RepositorioVinculaciones implements WithGlobalEntityManager {
    public static RepositorioVinculaciones instance = new RepositorioVinculaciones();

    public List<Vinculacion> listar() {
        return entityManager()
                .createQuery("from Vinculacion", Vinculacion.class)
                .getResultList();
    }
    public Vinculacion getById(int id_empleado) {
        return entityManager().find(Vinculacion.class, id_empleado);
    }

    public void agregar(Vinculacion vinculacion) {
        entityManager().persist(vinculacion.getEmpleado());
        entityManager().persist(vinculacion.getOrganizacion());
        entityManager().persist(vinculacion);
    }

    public void quitar(Vinculacion vinculacion) {
        entityManager().remove(vinculacion);
    }

}
