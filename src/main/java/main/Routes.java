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
    Spark.port(9090); //TODO: Modificar al momento de pushear
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
    Spark.get("/miembros-vinculacion", vinculacionController::getMiembroVinculacion, engine);
    Spark.get("/organizaciones-vinculacion", vinculacionController::getOrganizacionVinculacion, engine);
    Spark.post("/organizaciones-vinculacion/nuevo", vinculacionController::crear);

    Spark.get("/organizaciones-vinculacion/rechazado/:id", vinculacionController::rechazar);
    Spark.get("/organizaciones-vinculacion/aceptado/:id", vinculacionController::aceptar);
    Spark.get("/organizaciones-vinculacion/aceptadas", vinculacionController::getOrganizacionVinculacionAceptadas,engine);

    Spark.get("/mediciones", medicionesController::mediciones, engine);
    Spark.get("/mediciones-particular", medicionesController::particular, engine);
    Spark.get("/mediciones-csv", medicionesController::csv, engine);
    Spark.post("/mediciones-particular/nuevo", medicionesController::crear);
    Spark.post("/mediciones-csv/nuevo", medicionesController::cargar);

    Spark.get("/trayectos", trayectosController::trayectos, engine);
    Spark.get("/trayectos/nuevo", trayectosController::nuevo, engine);
    Spark.post("/trayectos", trayectosController::crear);

    Spark.get("/guia-recomendaciones", guiaController::guia, engine);

    Spark.get("/reportes", reportesController::reporte, engine);
    Spark.post("/reportes", reportesController::generarReporte, engine);

    Spark.get("/organizacion/calculadora-hc", calculadoraHCController::organizacionCalculadora, engine);
    Spark.post("/organizacion/calculadorHC", calculadoraHCController::organizacionCalcularHC, engine);
    Spark.get("/miembro/calculadora-hc", calculadoraHCController::miembroCalculadora, engine);
    Spark.post("/miembro/calculadorHC", calculadoraHCController::miembroCalcularHC, engine);


    Spark.after("/*",(request, response) -> PerThreadEntityManagers.getEntityManager().clear());
  }
}
