package controllers;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.Vinculacion;
import dominio.repositorios.*;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VinculacionController implements WithGlobalEntityManager, TransactionalOps {


  public ModelAndView getMiembroVinculacion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("organizaciones", RepositorioOrganizacion.instance.listar());
    model.put("miembros", RepositorioUsuarios.instance.listar());
    return new ModelAndView(model, "miembroVinculacion.html.hbs");
  }

  public ModelAndView getOrganizacionVinculacion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Stream<Vinculacion> vinculacionesStream = RepositorioVinculaciones.instance.listar().stream();
    List<Vinculacion> vinculacionesPendientes = vinculacionesStream.filter(vinculacion -> !vinculacion.estaAprobada()).collect(Collectors.toList());
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("vinculaciones", RepositorioVinculaciones.instance.listar());
    model.put("vinculacionesPendientes", vinculacionesPendientes);
    return new ModelAndView(model, "organizacionVinculacion.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      Organizacion organizacion = RepositorioOrganizacion.instance.buscarOrganizacion((request.queryParams("organizacion")));
      Usuario usuario = RepositorioUsuarios.instance.buscarUsuario(request.queryParams("miembro"));
      Vinculacion vinculacion = new Vinculacion(
            organizacion,usuario);
      RepositorioVinculaciones.instance.agregar(vinculacion);
    });
    response.redirect("/organizacion/vinculacion");
    return null;
  }

  public Void aceptar(Request request, Response response) {
    withTransaction(() -> {
      int id_empleado = Integer.parseInt(request.params("id"));
      Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);
      vinculacion.setEstado();
    });
    response.redirect("/organizacion/vinculacion");
    return null;
  }


  public Void rechazar(Request request, Response response) {
    withTransaction(() -> {
      int id_empleado = Integer.parseInt(request.params("id"));
      Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);
      RepositorioVinculaciones.instance.quitar(vinculacion);
    });
    response.redirect("/organizacion/vinculacion");
    return null;
  }

}

