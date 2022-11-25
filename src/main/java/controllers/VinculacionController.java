package controllers;

import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.organizaciones.Vinculacion;
import dominio.repositorios.*;
import dominio.usuarios.Administrador;
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
    return new ModelAndView(null, "miembroVinculacion.html.hbs");
  }

  public ModelAndView getOrganizacionVinculacion() {
    return new ModelAndView(null, "organizacionVinculacion.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      String idEmpleado = request.params(":id_empleado");
      String idOrganizacion = request.params(":id_organizacion");
      Organizacion organizacion = RepositorioOrganizacion.instance.buscarOrganizacion(Integer.parseInt(idOrganizacion));
      Usuario empleado = RepositorioUsuarios.instance.buscarEmpleado(Integer.parseInt(idEmpleado));
      Vinculacion vinculacion = new Vinculacion(
            organizacion,empleado);
      RepositorioVinculaciones.instance.agregar(vinculacion);
    });
    response.redirect("/vinculaciones");
    return null;
  }

  public Void aceptar(Request request, Response response) {
    int id_empleado = Integer.parseInt(request.params("empleado_id"));
    Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);;
    RepositorioVinculaciones.instance.agregar(vinculacion);
    response.redirect("/vinculaciones");
    return null;
  }

  public Void rechazar(Request request, Response response) {
    int id_empleado = Integer.parseInt(request.params("empleado_id"));
    Vinculacion vinculacion = RepositorioVinculaciones.instance.getById(id_empleado);;
    RepositorioVinculaciones.instance.quitar(vinculacion);
    response.redirect("/vinculaciones");
    return null;
  }
}

