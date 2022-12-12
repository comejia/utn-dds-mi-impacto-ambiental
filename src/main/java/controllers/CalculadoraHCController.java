package controllers;

import dominio.organizaciones.Medicion;
import dominio.organizaciones.Organizacion;
import dominio.repositorios.*;
import dominio.trayectos.Trayecto;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import funciones.UsuarioSesion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CalculadoraHCController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView organizacionCalculadora(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }

    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("color-cal", true);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    //model.put("organizaciones",RepositorioOrganizacion.instance.listar());
    model.put("valor", "");

    return new ModelAndView(model, "calculadora_org_hc.html.hbs");
  }

  public ModelAndView organizacionCalcularHC(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);
    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    String unidad = request.queryParams("unidad");
    String periodicidad = request.queryParams("periodicidad");
    List<Medicion> mediciones = RepositorioMediciones.instance.listar();
    BigDecimal hcSinUnidad = mediciones.stream().filter(medicion -> Objects.equals(medicion.getPeriodicidad(), periodicidad)).map(Medicion::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal hcConUnidad = BigDecimal.valueOf(0.0);
    switch (unidad) {
      case "gCO2eq":
        hcConUnidad = hcSinUnidad.multiply(BigDecimal.valueOf(1000));
        break;
      case "kgCO2eq":
        hcConUnidad = hcSinUnidad;
        break;
      case "tnCO2eq":
        hcConUnidad = hcSinUnidad.divide(BigDecimal.valueOf(1000));
        break;
    }
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    //model.put("organizaciones", RepositorioOrganizacion.instance.listar());
    model.put("valor", hcConUnidad);

    return new ModelAndView(model, "calculadora_org_hc.html.hbs");
  }
  public ModelAndView miembroCalculadora(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }

    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("sesion", true);
    model.put("admin", false);
    model.put("valor", "");

    return new ModelAndView(model, "calculadora_miembro_hc.html.hbs");
  }

  public ModelAndView miembroCalcularHC(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);
    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    String unidad = request.queryParams("unidad");
    List<Trayecto> trayectos = RepositorioTrayectos.instance.listar();
    double hcSinUnidad = trayectos.stream().mapToDouble(trayecto -> trayecto.getHC(unidad)).sum();
    double hcConUnidad = 0.0;
    switch (unidad) {
      case "gCO2eq":
        hcConUnidad = hcSinUnidad*1000;
        break;
      case "kgCO2eq":
        hcConUnidad = hcSinUnidad;
        break;
      case "tnCO2eq":
        hcConUnidad = hcSinUnidad/1000;
        break;
    }
    Map<String, Object> model = new HashMap<>();
    int id = request.session().attribute("idUsuario");
    model.put("sesion", true);
    model.put("admin", false);
    model.put("valor", hcConUnidad);

    return new ModelAndView(model, "calculadora_miembro_hc.html.hbs");
  }
}
