package controllers;

import dominio.repositorios.RepositorioMediciones;
import dominio.repositorios.RepositorioUsuarios;
import dominio.repositorios.RepositorioVinculaciones;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class VinculacionController {
  public ModelAndView getMiembroVinculacion() {
    return new ModelAndView(null, "miembroVinculacion.html.hbs");
  }

  public ModelAndView getOrganizacionVinculacion() {
    Map<String, Object> model = new HashMap<>();
    model.put("sesion", true);
    model.put("vinculaciones", RepositorioVinculaciones.instance.listar());
    return new ModelAndView(model, "organizacionVinculacion.html.hbs");
  }
}
