package controllers;

import dominio.repositorios.RepositorioTransportePublico;
import dominio.repositorios.RepositorioTrayectos;
import dominio.transportes.*;
import dominio.trayectos.Direccion;
import dominio.trayectos.Punto;
import dominio.trayectos.Tramo;
import dominio.trayectos.Trayecto;
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

  public ModelAndView nuevo(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("publicos", RepositorioTransportePublico.instance.listar());
    return new ModelAndView(modelo, "trayectos_nuevo.html.hbs");
  }

  public Void crear(Request request, Response response) {
    List<Tramo> tramos = new ArrayList<>();
    withTransaction(() -> {
      Transporte transporte = getTransporte(request.queryParams("tipoTransporte"));

      Punto puntoInicio = new Punto(new Direccion("", request.queryParams("inicio"), ""));
      Punto puntoFin = new Punto(new Direccion("", request.queryParams("fin"), ""));
      tramos.add(new Tramo(transporte, puntoInicio, puntoFin));
      Trayecto trayecto = new Trayecto(tramos);
      RepositorioTrayectos.instance.agregar(trayecto);
    });
    response.redirect("/");
    return null;
  }

  private Transporte getTransporte(String tipo) {
    if(tipo.contains("Linea")) {
      return RepositorioTransportePublico.instance.buscarPorLinea(Integer.parseInt(tipo.split(" ")[1]));
    }
    switch (tipo) {
      case "A Pie":
        return new APie();
      case "Bicicleta":
        return new Bicicleta();
      case "Taxi":
        return new ServicioContratado(TipoServicioContratado.TAXI);
      case "Remis":
        return new ServicioContratado(TipoServicioContratado.REMIS);
      default:
        throw new RuntimeException("Error al recuperar un Transporte");
    }
  }

}
