package dominio.Notificador;

import dominio.excepciones.NotificacionException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificarPorMail implements Notificador {

  private final String usuario;
  private final String contrasenia;

  public NotificarPorMail(String usuario, String contrasenia) {
    this.usuario = usuario;
    this.contrasenia = contrasenia;
  }

  private Session abrirSesion() {
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
  public void notificar(Contacto contacto, String asunto, String cuerpo) {
    String destinatario = contacto.getMail();
    if (destinatario.isEmpty()) {
      throw new NotificacionException("El destinario esta vacio, ingrese un destinario");
    }
    if (cuerpo == null) {
      throw new NotificacionException("El cuerpo del mensaje es nulo, ingrese un cuerpo");
    }

    Session session = abrirSesion();
    try {
      Message message = construirCorreo(session, destinatario, asunto, cuerpo);
      Transport.send(message);
    } catch (MessagingException e) {
      throw new NotificacionException("Error al enviar la notificacion por mail");
    }
    System.out.println("Soy la notificacion por whatsapp");
  }
}
