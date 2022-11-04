package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class GuiaController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView guia(Request request, Response response) {
    return new ModelAndView(null, "guia.html.hbs");
  }
}
