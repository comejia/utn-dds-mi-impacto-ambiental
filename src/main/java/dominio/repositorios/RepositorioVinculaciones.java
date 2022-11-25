package dominio.repositorios;

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

    public Vinculacion getById(int id) {
        return entityManager().find(Vinculacion.class, id);
    }

    public void agregar(Vinculacion vinculacion) {
        entityManager().persist(vinculacion);
    }

}
