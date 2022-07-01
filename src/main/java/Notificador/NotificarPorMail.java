package Notificador;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;

public class NotificarPorMail implements Notificador {

  private final String usuario = "grupo5.dds2022@gmail.com";
  private final String contrasenia = "ofzzipstsiuxdnby";

  private Session abrirSesion() {
    // Propiedades de la sesion con TLS
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS
    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    return Session.getInstance(prop,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(usuario, contrasenia);
          }
        });
  }

  private Message construirCorreo(Session session, String destinatario, String asunto, String cuerpo) throws MessagingException {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(usuario));
    message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(destinatario)
    );
    message.setSubject(asunto);
    message.setText(cuerpo);
    return message;
  }

  @Override
  public void notificar(String destinatario, String asunto, String cuerpo) throws MessagingException {
    if (!Objects.equals(destinatario, "") && destinatario != null) {
      Session session = abrirSesion();
      Message message = construirCorreo(session, destinatario, asunto, cuerpo);
      Transport.send(message);
    }
  }

}
