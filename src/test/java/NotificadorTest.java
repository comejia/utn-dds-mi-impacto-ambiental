import Notificador.Contacto;
import Notificador.NotificarPorMail;
import Notificador.NotificarPorWhatsApp;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;


public class NotificadorTest {

  NotificarPorWhatsApp notificarPorWhatsApp;
  NotificarPorMail notificarPorMail;
  Contacto contacto;

  @Before
  public void setUp() {
    this.notificarPorWhatsApp = new NotificarPorWhatsApp();
    this.notificarPorMail = new NotificarPorMail();
    this.contacto = new Contacto("migue.racedo.oviedo@gmail.com","+5491155136689");
  }

  @Test
  public void enviarWhatsAppTest() {
    // por ahora solo funciona con este numero, para probar con otros destinatarios hay que verificarlos en twilio
    notificarPorWhatsApp.notificar(contacto,"asunto", "Esto es un mensaje de prueba");
  }

  @Test
  public void enviarMailTest() throws MessagingException {
    // funciona con cualquier destinatario
    notificarPorMail.notificar(contacto,"Prueba","Esto es un correo de prueba");
  }

}
