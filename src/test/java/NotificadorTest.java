import Notificador.Contacto;
import Notificador.NotificarPorMail;
import Notificador.NotificarPorWhatsApp;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class NotificadorTest {

  NotificarPorWhatsApp notificarPorWhatsApp;
  NotificarPorMail notificarPorMail;
  Contacto contacto;
  Properties properties;

  @Before
  public void setUp() throws IOException {
    properties = new Properties();
    properties.load(Files.newInputStream(new File( System.getProperty("user.dir") + "/src/main/resources/mail_data.properties").toPath()));
    this.notificarPorWhatsApp = new NotificarPorWhatsApp();
    this.notificarPorMail = new NotificarPorMail(properties.getProperty("user"), properties.getProperty("pass"));
    this.contacto = new Contacto("migue.racedo.oviedo@gmail.com", "+5491155136689");
  }

  @Test
  public void enviarWhatsAppTest() {
    notificarPorWhatsApp.notificar(contacto, "asunto", "Esto es un mensaje de prueba");
  }

  @Test
  public void enviarMailTest() throws MessagingException {
    notificarPorMail.notificar(contacto, "Prueba", "Esto es un correo de prueba");
  }

}
