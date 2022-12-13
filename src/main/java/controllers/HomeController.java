package controllers;

import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView home(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    Integer id = request.session().attribute("idUsuario");
    model.put("color-ini", true);
    if (id == null) {
      return new ModelAndView(model, "index.html.hbs");
    }

    Usuario usuario = RepositorioUsuarios.instance.getById(id);

    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());

    return new ModelAndView(model, "index.html.hbs");
  }
}
