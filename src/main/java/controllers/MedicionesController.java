package controllers;

import dominio.repositorios.RepositorioTipoDeConsumo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MedicionesController {
  public ModelAndView mediciones(Request request, Response response) {
    return new ModelAndView(null, "mediciones.html.hbs");
  }

  public ModelAndView particular(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("tiposDeConsumos", RepositorioTipoDeConsumo.getInstance().listar());
    return new ModelAndView(modelo, "mediciones_particular.html.hbs");
  }

  public ModelAndView csv(Request request, Response response) {
    return new ModelAndView(null, "mediciones_csv.html.hbs");
  }
}
