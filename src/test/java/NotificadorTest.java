import dominio.Notificador.Contacto;
import dominio.Notificador.Notificador;
import dominio.Notificador.NotificarPorMail;
import dominio.Notificador.NotificarPorWhatsApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  @BeforeEach
  public void setUp() throws IOException {
    Properties properties = new Properties();
    properties.load(Files.newInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/mail_data.properties").toPath()));
    this.notificarPorWhatsApp = new NotificarPorWhatsApp();
    this.notificarPorMail = new NotificarPorMail(properties.getProperty("user"), properties.getProperty("pass"));
    this.contacto = new Contacto("migue.racedo.oviedo@gmail.com", "+5491155136689");
    this.notificador = mock(Notificador.class);
  }

  @Test
  public void enviarNotificacionTest() {
    doAnswer(invocation -> {
      Object arg0 = invocation.getArgument(0);
      Object arg1 = invocation.getArgument(1);
      Object arg2 = invocation.getArgument(2);

      assertEquals(contacto, arg0);
      assertEquals("asunto", arg1);
      assertEquals("Esto es un mensaje de prueba", arg2);
      return null;
    }).when(notificador).notificar(any(Contacto.class), any(String.class), any(String.class));
    notificador.notificar(contacto, "asunto", "Esto es un mensaje de prueba");
  }

}
