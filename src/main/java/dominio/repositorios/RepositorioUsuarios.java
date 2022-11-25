package dominio.repositorios;

import dominio.usuarios.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager, Repositorio<Usuario> {

  public static RepositorioUsuarios instancia = new RepositorioUsuarios();

  public void agregar(Usuario usuario) {
    entityManager().persist(usuario);
  }

  public List<Usuario> listar() {
    return entityManager()
        .createQuery("from Usuario", Usuario.class)
        .getResultList();
  }

  public Usuario getById(int id) {
    return entityManager().find(Usuario.class, id);
  }

  public Usuario buscarPorUsuarioYContrasenia(String username, String password) {
    return listar().stream()
        .filter(u -> BCrypt.checkpw(password, u.getContrasenia()) && u.getUsuario().equals(username))
        .findFirst().orElse(null);
  }
}
