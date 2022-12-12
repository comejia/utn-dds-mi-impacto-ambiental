package main;

import dominio.Notificador.Notificador;
import dominio.Notificador.NotificarPorMail;
import dominio.Notificador.NotificarPorWhatsApp;
import dominio.repositorios.RepositorioOrganizacion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class Recomendador {

  public static void main(String[] args) throws IOException {
    Properties properties = new Properties();
    properties.load(Files.newInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/mail_data.properties").toPath()));
    Notificador notificarPorWhatsApp = new NotificarPorWhatsApp();
    Notificador notificarPorMail = new NotificarPorMail("grupo5.dds2022@gmail.com", "ofzzipstsiuxdnby");

    RepositorioOrganizacion.instance.listar().forEach(o -> {
      if (!o.getContactos().isEmpty()) {
        o.getContactos().forEach(c -> {
          notificarPorMail.notificar(c, "Guia Recomendacion", "https://tpa-gopkz5e2ma-rj.a.run.app/guia-recomendaciones");
          notificarPorWhatsApp.notificar(c, "Guia Recomendacion", "https://tpa-gopkz5e2ma-rj.a.run.app/guia-recomendaciones");
        });
      }
    });
  }

}
