package Controllers;

import spark.ModelAndView;

public class VinculacionController {
  public ModelAndView getMiembroVinculacion(){
    return new ModelAndView(null,"miembroVinculacion.html.hbs");
  }
  public ModelAndView getOrganizacionVinculacion(){
    return new ModelAndView(null,"organizacionVinculacion.html.hbs");
  }
}
