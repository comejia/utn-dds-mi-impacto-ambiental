package Notificador;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class NotificarPorWhatsApp implements Notificador{

  private static final PhoneNumber from = new PhoneNumber("whatsapp:+14155238886"); // numero de telefono registrado en twilio
  public static final String ACCOUNT_SID = "AC7471f2bc938c1a3d499ec776bb4bac84";
  public static final String AUTH_TOKEN = "23f10df284242d15913504e923cf0667";

  @Override
  public void notificar(String destinatario, String asunto, String cuerpo) {
    // El formato correcto para un numero de argentina es "+5411" + el numero, por ejemplo +541133914938
    if (!destinatario.isEmpty()) {
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      Message.creator(
          new PhoneNumber("whatsapp:" + destinatario),
          from,
          cuerpo).
          create();
    }
  }
}
