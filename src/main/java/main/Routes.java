package main;

import controllers.*;
import spark.Spark;
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

    //Rutas Spark
    Spark.get("/", homeController::home, engine);

    Spark.get("/login", usuarioController::getFormularioLogin, engine);
    Spark.post("/login", usuarioController::iniciarSesion, engine);
    Spark.get("/logout", usuarioController::cerrarSesion);

    Spark.get("/registrarUsuario", usuarioController::getFormularioRegistrarUsuario, engine);
    Spark.post("/registrarUsuario", usuarioController::registrarUsuario, engine);

    Spark.get("/miembros/vinculacion", (request, response) -> vinculacionController.getMiembroVinculacion(), engine);
    Spark.get("/organizacion/vinculacion", (request, response) -> vinculacionController.getOrganizacionVinculacion(), engine);

    Spark.get("/mediciones", medicionesController::mediciones, engine);
    Spark.get("/mediciones/particular", medicionesController::particular, engine);
    Spark.get("/mediciones/csv", medicionesController::csv, engine);
    Spark.post("/mediciones/particular", medicionesController::crear);
    Spark.post("/mediciones/csv", medicionesController::cargar);

    Spark.get("/trayectos/nuevo", trayectosController::nuevo, engine);
    Spark.get("/trayectos", trayectosController::crear);

  }
}
