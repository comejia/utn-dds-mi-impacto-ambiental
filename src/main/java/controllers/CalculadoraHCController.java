package controllers;

import dominio.repositorios.RepositorioOrganizacion;
import dominio.repositorios.RepositorioTipoDeConsumo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CalculadoraHCController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView calculadora(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("organizaciones", RepositorioOrganizacion.instance.listar());
    return new ModelAndView(model, "calculadora_hc.html.hbs");
  }
}
