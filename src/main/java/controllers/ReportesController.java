package controllers;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.SectorTerritorial;
import dominio.repositorios.RepositorioOrganizacion;
import dominio.repositorios.RepositorioSectorTerritorial;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportesController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView reporte(Request request, Response response) {
    Map<String, Object> viewModel = new HashMap<String, Object>();

    List<Organizacion> organizacionList = RepositorioOrganizacion.getInstance().listar();
    viewModel.put("organizaciones", organizacionList);
    List<SectorTerritorial> sectorTerritorialList = RepositorioSectorTerritorial.getInstance().listar();
    viewModel.put("sectores", sectorTerritorialList);

    return new ModelAndView(viewModel, "reportes.html.hbs");
  }

  public ModelAndView generarReporte(Request request, Response response) {
    Map<String, Object> viewModel = new HashMap<String, Object>();
    Organizacion organizacion;
    SectorTerritorial sectorTerritorial;

    int tipoGraficoId = Integer.parseInt(request.queryParams("tipoGrafico"));
    int reporteId = Integer.parseInt(request.queryParams("tipoReporte"));
    String orgnizacionParam = request.queryParams("organizacion");
    String sectorTerritorialParam = request.queryParams("sectorTerritorial");

    int entidadId;
    if(orgnizacionParam != null && !orgnizacionParam.equals("")){
      entidadId = Integer.parseInt(orgnizacionParam);
    }
    if(sectorTerritorialParam != null && !sectorTerritorialParam.equals("")){
      entidadId = Integer.parseInt(sectorTerritorialParam);
    }

    switch (tipoGraficoId){
      case 1: //barra
        viewModel.put("barra", true);
        break;
      case 2: //torta
        viewModel.put("torta", true);
        break;
    }

    switch (reporteId){
      case 1: //HC total por sector territorial
        //logica en el repositorio que me devuelva el HC x sector
        break;
      case 2: //HC total por tipo de Organización
        //logica en el repositorio que me devuelva el HC x tipo de org
        break;
      case 3: //Composición de HC total de un sector territorial
        //sectorTerritorial = repo.getPorId(entidadId);
        //sectorTerritorial.getHcSector(unidades)
        break;
      case 4: //Composición de HC total de una Organización
        //organizacion = repo.getPorId(entidadId);
        //organizacion.getHCPorPorcentaje(unidades));
        break;
      case 5: //Evolución de HC total de un sector territorial
        //sectorTerritorial = repo.getPorId(entidadId);
        //sectorTerritorial.getHcEvolucion().get(sectorTerritorial.getHcEvolucion().size()-1).get(sectorTerritorial.getHcEvolucion().size()-1)
        break;
      case 6: //Evolución de HC total de una Organización
        //organizacion = repo.getPorId(entidadId);
        //organizacion.getHc().get(organizacion.getHc().size()-1)
        break;
    }

    //logica de el resultado meterlo en el grafico

    return new ModelAndView(viewModel, "reportes.html.hbs");
  }
}
