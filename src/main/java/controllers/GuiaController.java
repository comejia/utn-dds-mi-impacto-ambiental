package controllers;

import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import funciones.UsuarioSesion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class GuiaController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView guia(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);
    Map<String, Object> model = new HashMap<>();

    if (usuarie == null) {
//      response.redirect("/login");
//      return null;
      model.put("cron", true);
      model.put("color-gui", true);
      return new ModelAndView(model, "guia.html.hbs");
    }

    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("color-gui", true);
    return new ModelAndView(model, "guia.html.hbs");
  }
}
