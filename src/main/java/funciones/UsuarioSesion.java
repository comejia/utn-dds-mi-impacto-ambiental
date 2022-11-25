package funciones;

import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Administrador;
import dominio.usuarios.Usuario;
import spark.Request;

import java.util.Map;

public class UsuarioSesion {

  private UsuarioSesion() {}

  public static Usuario estaLogueado(Request request) {
    Integer id = request.session().attribute("idUsuario");
    if (UsuarioSesion.verificarVacio(id))
      return null;
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    if (UsuarioSesion.verificarVacio(usuario)) {
      return null;
    }
    return usuario;
  }

  public static void reconocerRol(Usuario usuario, Map<String, Object> modelo) {
    return;
  }

  private static boolean verificarVacio(Object object) {
    if (object == null) {
      return true;
    }
    return false;
  }
}
