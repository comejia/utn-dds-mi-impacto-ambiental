import Notificador.NotificarPorMail;
import Notificador.NotificarPorWhatsApp;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;


public class NotificadorTest {

  NotificarPorWhatsApp notificarPorWhatsApp;
  NotificarPorMail notificarPorMail;

  @Before
  public void setUp() {
    this.notificarPorWhatsApp = new NotificarPorWhatsApp();
    this.notificarPorMail = new NotificarPorMail();
  }

  @Test
  public void enviarWhatsAppTest() {
    String destinatario = "+5491155136689"; // por ahora solo funciona con este numero, para probar con otros destinatarios hay que verificarlos en twilio
    notificarPorWhatsApp.notificar(destinatario,"asunto", "Esto es un mensaje de prueba");
  }

  @Test
  public void enviarMailTest() throws MessagingException {
    String destinatario = "migue.racedo.oviedo@gmail.com"; // funciona con cualquier destinatario
    notificarPorMail.notificar(destinatario,"Prueba","Esto es un correo de prueba");
  }

}
