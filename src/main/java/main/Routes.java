package main;

import controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

  public static void main(String[] args) {

    System.out.println("Corriendo bootstrap...");
    new Bootstrap().run();

    System.out.println("Iniciando servidor...");
    Spark.port(9090);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    //new de todos los controllers
    HomeController homeController = new HomeController();
    UsuarioController usuarioController = new UsuarioController();
    VinculacionController vinculacionController = new VinculacionController();
    MedicionesController medicionesController = new MedicionesController();
    TrayectosController trayectosController = new TrayectosController();
    GuiaController guiaController = new GuiaController();
    ReportesController reportesController = new ReportesController();
    CalculadoraHCController calculadoraHCController = new CalculadoraHCController();

    DebugScreen.enableDebugScreen();

    //Rutas Spark
    Spark.get("/", homeController::home, engine);

    Spark.get("/login", usuarioController::getFormularioLogin, engine);
    Spark.post("/login", usuarioController::iniciarSesion, engine);
    Spark.get("/logout", usuarioController::cerrarSesion);

    Spark.get("/usuario/nuevo", usuarioController::getFormularioRegistrarUsuario, engine);
    Spark.post("/usuario/nuevo", usuarioController::registrarUsuario, engine);

    Spark.get("/miembros/vinculacion", (request, response) -> vinculacionController.getMiembroVinculacion(), engine);
    Spark.get("/organizacion/vinculacion", (request, response) -> vinculacionController.getOrganizacionVinculacion(), engine);

    Spark.get("/mediciones", medicionesController::mediciones, engine);
    Spark.get("/medicion-particular", medicionesController::particular, engine);
    Spark.get("/medicion-csv", medicionesController::csv, engine);
    Spark.post("/medicion-particular/nuevo", medicionesController::crear);
    Spark.post("/medicion-csv/nuevo", medicionesController::cargar);

    Spark.get("/trayectos", trayectosController::trayectos, engine);
    Spark.get("/trayectos/nuevo", trayectosController::nuevo, engine);
    Spark.post("/trayectos", trayectosController::crear);

    Spark.get("/guia-recomendaciones", guiaController::guia, engine);

    Spark.get("/reportes", reportesController::reporte, engine);

    Spark.get("/calculadora-hc", calculadoraHCController::calculadora, engine);


    Spark.after((request, response) -> PerThreadEntityManagers.getEntityManager().clear());

//    Spark.get("/blog", (request, response) -> {
//      //String cookie = request.cookie("contador");
//      String cookie = request.session().attribute("contador");
//      int nro = cookie == null ? 0 : Integer.parseInt(cookie);
//      //response.cookie("contador", String.valueOf(nro + 1));
//      request.session().attribute("contador", String.valueOf(nro + 1));
//      return new ModelAndView(request.session().attribute("contador"), "bb.hmlt.hbs");
//    }, engine);

//    Spark.before((request, response) -> {
//      PerThreadEntityManagers.getEntityManager().clear();
//
//      if (!request.pathInfo().startsWith("/login") && request.session().attribute("user_id") == null) {
//        response.redirect("/login");
//      }
//    });

//    Spark.before("/admin", (request, response) -> {
//      PerThreadEntityManagers.getEntityManager().clear();
//
//      if (!request.pathInfo().startsWith("/login") && request.session().attribute("user_id") == null) {
//        response.redirect("/login");
//      }
//    });
  }
}
