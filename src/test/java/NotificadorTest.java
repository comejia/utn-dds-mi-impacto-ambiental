import Notificador.Contacto;
import Notificador.Notificador;
import Notificador.NotificarPorMail;
import Notificador.NotificarPorWhatsApp;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificadorTest {

  Notificador notificador;
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
    this.notificador = mock(Notificador.class);
  }

  @Test
  public void enviarNotifiacionTest() throws MessagingException {
    doAnswer(invocation -> {
      Object arg0 = invocation.getArgument(0);
      Object arg1 = invocation.getArgument(1);
      Object arg2 = invocation.getArgument(2);

      assertEquals(contacto, arg0);
      assertEquals("asunto", arg1);
      assertEquals("Esto es un mensaje de prueba", arg2);
      return null;
    }).when(notificador).notificar(any(Contacto.class),any(String.class),any(String.class));
    notificador.notificar(contacto, "asunto", "Esto es un mensaje de prueba");
  }

  /*@Test
  public void enviarWhatsAppTest() {
    notificarPorWhatsApp.notificar(contacto, "Prueba", "Esto es un correo de prueba");
  }

  @Test
  public void enviarMailTest() throws MessagingException {
    notificarPorMail.notificar(contacto, "Prueba", "Esto es un correo de prueba");
  }*/

}
