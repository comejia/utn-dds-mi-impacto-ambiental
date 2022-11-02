package Funciones;

import Dominio.repositorios.RepositorioUsuarios;
import Dominio.usuarios.Administrador;
import spark.Request;

import java.util.Map;

public class UsuarioSesion {

  private UsuarioSesion(){}

  public static Administrador estaLogueado(Request request) {
    Long id = request.session().attribute("idUsuario");
    if (UsuarioSesion.verificarVacio(id))
      return null;
    Administrador usuario = RepositorioUsuarios.instancia.getById(id);
    if (UsuarioSesion.verificarVacio(usuario)) {
      return null;
    }
    return usuario;
  }

  public static void reconocerRol(Administrador usuario, Map<String, Object> modelo) {
    return;
  }

  private static boolean verificarVacio(Object object) {
    if (object == null) {
      return true;
    }
    return false;
  }
}
