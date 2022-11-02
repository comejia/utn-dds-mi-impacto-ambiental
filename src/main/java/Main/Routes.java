package Main;

import Controllers.UsuarioController;
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
    UsuarioController usuarioController = new UsuarioController();


    //Rutas Spark
    Spark.get("/login", usuarioController::getFormularioLogin, engine);
    Spark.post("/login", usuarioController::iniciarSesion, engine);
    Spark.get("/logout", usuarioController::cerrarSesion);

    Spark.get("/registrarUsuario", usuarioController::getFormularioRegistrarUsuario, engine);
    Spark.post("/registrarUsuario", usuarioController::registrarUsuario, engine);

  }
}
