package controllers;

import dominio.organizaciones.Medicion;
import dominio.organizaciones.TipoConsumo;
import dominio.repositorios.RepositorioMediciones;
import dominio.repositorios.RepositorioTipoDeConsumo;
import dominio.usuarios.Administrador;
import funciones.UsuarioSesion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MedicionesController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView mediciones(Request request, Response response) {
//    Administrador usuario = UsuarioSesion.estaLogueado(request);
//    if (usuario == null) {
//      response.redirect("/login");
//      return null;
//    }
//    Map<String, Object> model = new HashMap<>();
//    UsuarioSesion.reconocerRol(usuario, model);
//    model.put("sesion", "Estoy logueado");
//    model.put("nombreUsuario", usuario.getUsuario());

    return new ModelAndView(null, "mediciones.html.hbs");
  }

  public ModelAndView particular(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("tiposDeConsumos", RepositorioTipoDeConsumo.instance.listar());
    return new ModelAndView(modelo, "mediciones_particular.html.hbs");
  }

  public ModelAndView csv(Request request, Response response) {
    return new ModelAndView(null, "mediciones_csv.html.hbs");
  }

  public Void crear(Request request, Response response) {
    withTransaction(() -> {
      TipoConsumo tipoConsumo = RepositorioTipoDeConsumo.instance.buscarPorTipo(request.queryParams("tipoDeConsumo"));
      Medicion medicion = new Medicion(
          tipoConsumo,
          BigDecimal.valueOf(Integer.parseInt(request.queryParams("valor"))),
          request.queryParams("periodicidad"),
          request.queryParams("periodoImputacion"));
      RepositorioMediciones.instance.agregar(medicion);
    });
    response.redirect("/");
    return null;
  }

  public Void cargar(Request request, Response response) {
    withTransaction(() -> {
    });
    response.redirect("/");
    return null;
  }
}
