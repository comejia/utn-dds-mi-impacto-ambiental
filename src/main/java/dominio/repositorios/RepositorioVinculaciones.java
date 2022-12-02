package dominio.repositorios;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.organizaciones.Vinculacion;
import dominio.usuarios.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioVinculaciones implements WithGlobalEntityManager {
    public static RepositorioVinculaciones instance = new RepositorioVinculaciones();

    public List<Vinculacion> listar() {
        return entityManager()
                .createQuery("from Vinculacion", Vinculacion.class)
                .getResultList();
    }

    public Vinculacion buscarEmpleado(String id_empleado) {
        return entityManager().createQuery("from Vinculacion v where v.miembroID = :id_empleado", Vinculacion.class)
                .setParameter("id_org", id_empleado).getResultList().get(0); // buscar vinculacion segun empleado
    }

    public Vinculacion getById(int id_empleado) {
        return entityManager().find(Vinculacion.class, id_empleado);
    }

    public void agregar(Vinculacion vinculacion) {
        entityManager().persist(vinculacion);
    }

    public void quitar(Vinculacion vinculacion) {
        entityManager().createNativeQuery("delete from Vinculacion where id = :id")
                .setParameter("id", vinculacion.getId())
                .executeUpdate();
    }

}
