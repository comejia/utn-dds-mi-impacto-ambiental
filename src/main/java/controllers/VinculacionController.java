package controllers;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.Sector;
import dominio.organizaciones.Vinculacion;
import dominio.repositorios.*;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import funciones.UsuarioSesion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spark.Spark.after;

public class VinculacionController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView getMiembroVinculacion(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    Map<String, Object> model = new HashMap<>();
    List<Organizacion> listaOrgs= RepositorioOrganizacion.instance.listar();
    List<Sector> sectores = new ArrayList<>();

    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);

    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("organizaciones", listaOrgs);
    model.put("miembros", RepositorioUsuarios.instance.listar());
    model.put("sectores", RepositorioSector.instance.listar());
    return new ModelAndView(model, "miembroVinculacion.html.hbs");
  }

  public ModelAndView getOrganizacionVinculacion(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
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
    model.put("seAgrego", "true");
    return new ModelAndView(model, "organizacionVinculacion.html.hbs");
  }
  public ModelAndView getOrganizacionVinculacionAceptadas(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Stream<Vinculacion> vinculacionesStream = RepositorioVinculaciones.instance.listar().stream();
    List<Vinculacion> vinculacionesAceptadas = vinculacionesStream.filter(vinculacion -> vinculacion.estaAprobada()).collect(Collectors.toList());
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());
    model.put("vinculaciones", vinculacionesAceptadas);
    model.put("vinculacionesPendientes", RepositorioVinculaciones.instance.listar());
    return new ModelAndView(model, "vinculaciones.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      Sector sector = RepositorioSector.instance.buscarSectores((request.queryParams("sector")));
      Organizacion organizacion = sector.getOrganizacion();

      System.out.print("Organizacion: " + request.queryParams("miembro"));
      Usuario usuario = RepositorioUsuarios.instance.buscarUsuario(request.queryParams("miembro"));
      Vinculacion vinculacion = new Vinculacion(
            organizacion,usuario);
      RepositorioVinculaciones.instance.agregar(vinculacion);
    });
    int idLogeado = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(idLogeado);
    if (usuario.getRole() == Role.ADMIN)
      response.redirect("/organizacion/vinculacion");
    else
      response.redirect("/miembros/vinculacion");
    return null;
  }

  public Void aceptar(Request request, Response response) {
    withTransaction(() -> {
      int id_empleado = Integer.parseInt(request.params("id"));
      Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);
      vinculacion.setEstado();
    });
    response.redirect("/organizacion/vinculacion/aceptadas");
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

