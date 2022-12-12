package dominio.Notificador;

import dominio.excepciones.NotificacionException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class NotificarPorWhatsApp implements Notificador {

  private static final PhoneNumber from = new PhoneNumber("whatsapp:+14155238886");
  public static final String ACCOUNT_SID = "AC7471f2bc938c1a3d499ec776bb4bac84";
  public static final String AUTH_TOKEN = "23f10df284242d15913504e923cf0667";

  @Override
  public void notificar(Contacto contacto, String asunto, String cuerpo) {
    String destinatario = contacto.getTelefono();
    if (cuerpo == null) {
      throw new NotificacionException("El cuerpo del mensaje es nulo, ingrese un cuerpo");
    }
    if (destinatario.isEmpty()) {
      throw new NotificacionException("El destinatario del mensaje está vacio, ingrese un destinatario");
    }
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message.creator(
            new PhoneNumber("whatsapp:" + destinatario),
            from,
            cuerpo)
        .create();
    System.out.println("Soy la notificacion por whatsapp");
  }
}
