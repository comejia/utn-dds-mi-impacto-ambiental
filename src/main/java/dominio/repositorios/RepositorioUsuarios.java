package dominio.repositorios;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.Vinculacion;
import dominio.usuarios.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager {

  public static RepositorioUsuarios instance = new RepositorioUsuarios();

  public List<Usuario> listar() {
    return entityManager()
        .createQuery("from Usuario", Usuario.class)
        .getResultList();
  }

  public Usuario getById(int id) {
    return entityManager().find(Usuario.class, id);
  }
  public void agregar(Usuario usuario) {
    entityManager().persist(usuario);
  }

  public Usuario buscarPorUsuarioYContrasenia(String username, String password) {
    return listar().stream()
        .filter(u -> BCrypt.checkpw(password, u.getContrasenia()) && u.getUsuario().equals(username))
        .findFirst().orElse(null);
  }

  public Usuario buscarEmpleado(int id_empleado) {
      return entityManager()
              .createQuery("from Usuario where id = :id_empleado", Usuario.class)
              .getResultList().get(0);
    }
  public Usuario buscarEmpleado(String usuario) {
    return entityManager()
            .createQuery("from Usuario where usuario = :usuario", Usuario.class)
            .setParameter("usuario",usuario)
            .getResultList().get(0);
  }
}
