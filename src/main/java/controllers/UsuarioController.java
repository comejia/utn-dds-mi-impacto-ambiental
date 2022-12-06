package controllers;

import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Administrador;
import dominio.usuarios.Usuario;
import funciones.UsuarioNotificacion;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public ModelAndView getFormularioLogin(Request request, Response response) {
    return new ModelAndView(null, "login.html.hbs");
  }

  public ModelAndView iniciarSesion(Request request, Response response) {
    Map<String, Object> viewModel = new HashMap<>();
    String username = request.queryParams("username");
    String password = request.queryParams("password");
    Usuario usuario = RepositorioUsuarios.instance.buscarPorUsuarioYContrasenia(username, password);

    if (usuario == null) {
      UsuarioNotificacion.notificar(viewModel, "danger", "Error: ", "El nombre y/o contraseña ingresada es incorrecta");
      return new ModelAndView(viewModel, "login.html.hbs");
    }

    request.session().attribute("idUsuario", usuario.getId());
    response.redirect("/");
    return null;
  }

  public ModelAndView getFormularioRegistrarUsuario(Request request, Response response) {
    return new ModelAndView(null, "registrarUsuario.html.hbs");
  }

  public ModelAndView registrarUsuario(Request request, Response response) {
    Map<String, Object> viewModel = new HashMap<String, Object>();
    String username = request.queryParams("username");
    String password = request.queryParams("password");
    Usuario usuario = null;
    try {
      usuario = RepositorioUsuarios.instance.listar().stream().filter(u -> u.getContrasenia().equals(password) && u.getUsuario().equals(username)).findFirst().get();
      UsuarioNotificacion.notificar(viewModel, "warning", "Advertencia: ", "El usuario ya se encuentra registrado.");
      return new ModelAndView(viewModel, "login.html.hbs");
    } catch (Exception e) {
      Administrador usr = new Administrador(username, password);
      withTransaction(() -> {
        RepositorioUsuarios.instance.agregar(usr);
      });
      request.session().attribute("idUsuario", usr.getId());
      UsuarioNotificacion.notificar(viewModel, "success", "Éxito!: ", "Se ha registrado el usuario.");
      //UsuarioSesion.reconocerRol(usuario, viewModel);
      return new ModelAndView(viewModel, "home.html.hbs");
    }
  }

  private Usuario getUsuarioLogueado(Request request) {
    Integer id = request.session().attribute("idUsuario");
    if (id == null) {
      return null;
    }
    return RepositorioUsuarios.instance.getById(id);
  }

  private boolean estaLogueado(Request request) {
    return this.getUsuarioLogueado(request) != null;
  }

  public Void cerrarSesion(Request req, Response res) {

    req.session().removeAttribute("idUsuario");

    res.redirect("/");
    return null;
  }
}
