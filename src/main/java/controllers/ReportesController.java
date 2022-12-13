package controllers;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.SectorTerritorial;
import dominio.repositorios.RepositorioOrganizacion;
import dominio.repositorios.RepositorioSectorTerritorial;
import dominio.repositorios.RepositorioUsuarios;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
import funciones.ContenidoReportes;
import funciones.UsuarioSesion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class ReportesController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView reporte(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }

    Map<String, Object> viewModel = new HashMap<String, Object>();

    List<Organizacion> organizacionList = RepositorioOrganizacion.getInstance().listar();
    viewModel.put("organizaciones", organizacionList);
    List<SectorTerritorial> sectorTerritorialList = RepositorioSectorTerritorial.getInstance().listar();
    viewModel.put("sectores", sectorTerritorialList);

    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    viewModel.put("color-rep", true);
    viewModel.put("sesion", true);
    viewModel.put("admin", usuario.getRole() == Role.ADMIN);
    viewModel.put("nombreUsuario", usuario.getUsuario());

    return new ModelAndView(viewModel, "reportes.html.hbs");
  }

  public ModelAndView generarReporte(Request request, Response response) {
    Usuario usuarie = UsuarioSesion.estaLogueado(request);

    if (usuarie == null) {
      response.redirect("/login");
      return null;
    }
    Map<String, Object> viewModel = new HashMap<>();
    Integer id = request.session().attribute("idUsuario");
    Usuario usuario = RepositorioUsuarios.instance.getById(id);
    viewModel.put("sesion", true);
    viewModel.put("admin", usuario.getRole() == Role.ADMIN);
    viewModel.put("nombreUsuario", usuario.getUsuario());
    Organizacion organizacion;
    SectorTerritorial sectorTerritorial;
    Map<String, Double> contenido = new HashMap<>();

    String unidad = request.queryParams("unidad");
    int reporteId = Integer.parseInt(request.queryParams("tipoReporte"));
    String orgnizacionParam = request.queryParams("organizacion");
    String sectorTerritorialParam = request.queryParams("sectorTerritorial");

    int entidadId = 0;
    if(orgnizacionParam != null && !orgnizacionParam.equals("")){
      entidadId = Integer.parseInt(orgnizacionParam);
    }
    if(sectorTerritorialParam != null && !sectorTerritorialParam.equals("")){
      entidadId = Integer.parseInt(sectorTerritorialParam);
    }

    List<String> unidades = Arrays.asList("kgCO2eq/kWh","gCO2eq/m3");

    switch (reporteId){
      case 1: //HC total por sector territorial
        contenido =  RepositorioSectorTerritorial.getInstance().hcPorSector(unidad);
        viewModel.put("esSector", true);
        break;
      case 2: //HC total por tipo de Organización
        contenido =  RepositorioOrganizacion.getInstance().hcPorOrganizacion(unidad);
        break;
      case 3: //Composición de HC total de un sector territorial
        sectorTerritorial = RepositorioSectorTerritorial.getInstance().buscarSector(entidadId);
        List<List<Double>> aux = sectorTerritorial.getHcSector(unidades);
        viewModel.put("esSector", true);
        break;
      case 4: //Composición de HC total de una Organización
        organizacion = RepositorioOrganizacion.getInstance().buscarOrganizacion(entidadId);
        List<Double> aux2 = organizacion.getHCPorPorcentaje(unidades);
        break;
      case 5: //Evolución de HC total de un sector territorial
        sectorTerritorial = RepositorioSectorTerritorial.getInstance().buscarSector(entidadId);
        contenido.put(
            sectorTerritorialParam,
            sectorTerritorial.getHcEvolucion().get(sectorTerritorial.getHcEvolucion().size()-1).get(sectorTerritorial.getHcEvolucion().size()-1)
        );
        viewModel.put("esSector", true);
        break;
      case 6: //Evolución de HC total de una Organización
        organizacion = RepositorioOrganizacion.getInstance().buscarOrganizacion(entidadId);
        contenido.put(
            orgnizacionParam,
            organizacion.getHc().get(organizacion.getHc().size()-1)
        );
        break;
    }

    viewModel.put("contenido", contenido);
    viewModel.put("unidad", unidad);

    List<Organizacion> organizacionList = RepositorioOrganizacion.getInstance().listar();
    viewModel.put("organizaciones", organizacionList);
    List<SectorTerritorial> sectorTerritorialList = RepositorioSectorTerritorial.getInstance().listar();
    viewModel.put("sectores", sectorTerritorialList);
    viewModel.put("sesion", true);
    viewModel.put("admin", usuario.getRole() == Role.ADMIN);
    viewModel.put("nombreUsuario", usuario.getUsuario());

    return new ModelAndView(viewModel, "reportes.html.hbs");
  }
}
