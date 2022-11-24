package dominio.repositorios;

import dominio.usuarios.Administrador;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager {

  public static RepositorioUsuarios instancia = new RepositorioUsuarios();

  public static List<Administrador> usuarioLogueados = new ArrayList<>();

  public List<Administrador> listar() {
    //return usuarioLogueados;
    return entityManager()
        .createQuery("from Administrador", Administrador.class)
        .getResultList();
  }

  public Administrador getById(Long id) {
    //return usuarioLogueados.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new RuntimeException(""));
    return entityManager().find(Administrador.class, id);
  }

  public void agregar(Administrador usuario) {
    entityManager().persist(usuario);
    //usuarioLogueados.add(usuario);
  }

  public Administrador buscarPorUsuarioYContrasenia(String username, String password) {
    return listar().stream().filter(u -> u.getContrasenia().equals(password) && u.getUsuario().equals(username))
        .findFirst().get();
  }
}
