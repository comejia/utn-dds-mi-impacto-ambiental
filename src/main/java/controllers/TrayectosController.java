package controllers;

import dominio.repositorios.RepositorioTransportes;
import dominio.repositorios.RepositorioTrayectos;
import dominio.repositorios.RepositorioUsuarios;
import dominio.transportes.*;
import dominio.trayectos.Direccion;
import dominio.trayectos.Punto;
import dominio.trayectos.Tramo;
import dominio.trayectos.Trayecto;
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

public class TrayectosController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView trayectos(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    Map<String, Object> model = new HashMap<>();
    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    model.put("color-tra", true);
    model.put("sesion", true);
    model.put("admin", usuario.getRole() == Role.ADMIN);
    model.put("nombreUsuario", usuario.getUsuario());

    model.put("trayectos", RepositorioTrayectos.instance.listar());

    return new ModelAndView(model, "trayectos.html.hbs");
  }

  public ModelAndView nuevo(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    Map<String, Object> modelo = new HashMap<>();
    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    modelo.put("color-tra", true);
    modelo.put("sesion", true);
    modelo.put("admin", usuario.getRole() == Role.ADMIN);
    modelo.put("nombreUsuario", usuario.getUsuario());
    modelo.put("transportes", RepositorioTransportes.instance.listar());
    return new ModelAndView(modelo, "trayectos_nuevo.html.hbs");
  }

  public Void crear(Request request, Response response) {
    List<Tramo> tramos = new ArrayList<>();
    String inicio = request.queryParams("inicio");
    String calleInicio = inicio.split(" ")[0];
    String alturaInicio = inicio.split(" ")[1];
    String fin = request.queryParams("fin");
    String calleFin = fin.split(" ")[0];
    String alturaFin = fin.split(" ")[1];
    withTransaction(() -> {
      Transporte transporte = getTransporte(request.queryParams("tipoTransporte"));

      Punto puntoInicio = new Punto(new Direccion(1, calleInicio, alturaInicio));
      Punto puntoFin = new Punto(new Direccion(1, calleFin, alturaFin));
      tramos.add(new Tramo(transporte, puntoInicio, puntoFin));
      Trayecto trayecto = new Trayecto(tramos);
      RepositorioTrayectos.instance.agregar(trayecto);
    });
    response.redirect("/trayectos");
    return null;
  }

  private Transporte getTransporte(String tipo) {
    if(tipo.contains("LINEA")) {
      return RepositorioTransportes.instance.buscarPorLinea(Integer.parseInt(tipo.split(" ")[2]));
    }
    switch (tipo) {
      case "A PIE":
        return new APie();
      case "BICICLETA":
        return new Bicicleta();
      case "TAXI":
        return new ServicioContratado(TipoServicioContratado.TAXI);
      case "REMIS":
        return new ServicioContratado(TipoServicioContratado.REMIS);
      case "CAMIONETA":
        return new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA);
      default:
        throw new RuntimeException("Error al recuperar un Transporte");
    }
  }

}
