package controllers;

import dominio.miembros.Miembro;
import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.organizaciones.Vinculacion;
import dominio.repositorios.*;
import dominio.usuarios.Administrador;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import funciones.UsuarioNotificacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VinculacionController implements WithGlobalEntityManager, TransactionalOps { //Revisar tema login
  public ModelAndView getMiembroVinculacion() {
    Map<String, Object> model = new HashMap<>();
    model.put("organizaciones", RepositorioOrganizacion.instance.listar());
    model.put("miembros", RepositorioUsuarios.instance.listar());
    return new ModelAndView(model, "miembroVinculacion.html.hbs");
  }

  public ModelAndView getOrganizacionVinculacion() {
    Map<String, Object> model = new HashMap<>();
    model.put("sesion", true);
    model.put("vinculaciones", RepositorioVinculaciones.instance.listar());
    return new ModelAndView(model, "organizacionVinculacion.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      Organizacion organizacion = RepositorioOrganizacion.instance.buscarOrganizacion((request.queryParams("organizacion")));
      Usuario miembro = RepositorioUsuarios.instance.buscarUsuario(request.queryParams("miembro"));

      Vinculacion vinculacion = new Vinculacion(
            organizacion,miembro);
      RepositorioVinculaciones.instance.agregar(vinculacion);
    });
    response.redirect("/miembros/vinculacion");
    return null;
  }

  public Void aceptar(Request request, Response response) {
    withTransaction(() -> {
      System.out.println("ver: " + request.params("id"));
      int id_empleado = Integer.parseInt(request.params("id"));
      Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);
      RepositorioVinculaciones.instance.quitar(vinculacion);
    });
    response.redirect("/organizacion/vinculacion");
    return null;
  }

  public Void rechazar(Request request, Response response) {
    withTransaction(() -> {
      System.out.println("rechazar: " + request.params("id"));
      int id_empleado = Integer.parseInt(request.params("id"));
      Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);
      System.out.println("vinculacion: " + vinculacion.getEmpleado());
      RepositorioVinculaciones.instance.quitar(vinculacion);
    });
    response.redirect("/organizacion/vinculacion");
    return null;
  }

  public ModelAndView getVinculacionesAceptadas(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("vinculaciones", RepositorioVinculaciones.instance.listar());
    return new ModelAndView(model, "vinculaciones.html.hbs");
  }
}

