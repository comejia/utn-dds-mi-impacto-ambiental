package controllers;

import dominio.organizaciones.Medicion;
import dominio.organizaciones.TipoConsumo;
import dominio.repositorios.RepositorioMediciones;
import dominio.repositorios.RepositorioTipoDeConsumo;
import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
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
    Map<String, Object> model = new HashMap<>();
    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instancia.getById(id);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());

    model.put("mediciones", RepositorioMediciones.instance.listar());
    model.put("display", RepositorioMediciones.instance.listar().size() > 0 ? "" : "display:none");

    return new ModelAndView(model, "mediciones.html.hbs");
  }

  public ModelAndView particular(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("tiposDeConsumos", RepositorioTipoDeConsumo.instance.listar());
    return new ModelAndView(model, "mediciones_particular.html.hbs");
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
    response.redirect("/mediciones");
    return null;
  }

  public Void cargar(Request request, Response response) {
    withTransaction(() -> {
      String[] rawBody = request.body().replaceAll("(.*Web.*)", "").replaceAll("(Content.*)", "")
          .replaceAll("\r", "").replaceAll("(\n){2,10}", "")
          .split("\n");

      for (String line : rawBody) {
        String[] lineSplited = line.split(",");
        TipoConsumo tipoConsumo = RepositorioTipoDeConsumo.instance.buscarPorTipo(lineSplited[0]);
        Medicion medicion = new Medicion(
            tipoConsumo,
            BigDecimal.valueOf(Integer.parseInt(lineSplited[1])),
            lineSplited[2],
            lineSplited[3]);
        RepositorioMediciones.instance.agregar(medicion);
      }
    });
    response.redirect("/mediciones");
    return null;
  }
}
