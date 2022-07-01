package Notificador;

import javax.mail.MessagingException;

public interface Notificador {
  void notificar(Contacto contacto, String asunto, String cuerpo) throws MessagingException;
}
