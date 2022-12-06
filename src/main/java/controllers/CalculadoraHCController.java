package controllers;

import dominio.repositorios.RepositorioOrganizacion;
import dominio.repositorios.RepositorioTipoDeConsumo;
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

public class CalculadoraHCController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView calculadora(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }

    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("organizaciones", RepositorioOrganizacion.instance.listar());


    return new ModelAndView(model, "calculadora_hc.html.hbs");
  }
}
