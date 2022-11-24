package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ReportesController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView reporte(Request request, Response response) {
    return new ModelAndView(null, "reportes.html.hbs");
  }
}
