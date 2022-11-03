package Main;

import Controllers.UsuarioController;
import Controllers.VinculacionController;
import spark.Spark;
import spark.TemplateViewRoute;
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
    UsuarioController usuarioController = new UsuarioController();
    VinculacionController vinculacionController = new VinculacionController();


    //Rutas Spark
    Spark.get("/login", usuarioController::getFormularioLogin, engine);
    Spark.post("/login", usuarioController::iniciarSesion, engine);
    Spark.get("/logout", usuarioController::cerrarSesion);

    Spark.get("/registrarUsuario", usuarioController::getFormularioRegistrarUsuario, engine);
    Spark.post("/registrarUsuario", usuarioController::registrarUsuario, engine);

    Spark.get("/miembros/vinculacion", (request,response)->vinculacionController.getMiembroVinculacion(),engine);

  }
}
