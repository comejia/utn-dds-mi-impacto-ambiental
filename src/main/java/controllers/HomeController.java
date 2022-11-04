package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView home(Request request, Response response) {
    return new ModelAndView(null, "index.html.hbs");
  }
}
