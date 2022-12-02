package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class GuiaController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView guia(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesion", true);
    return new ModelAndView(model, "guia.html.hbs");
  }
}
