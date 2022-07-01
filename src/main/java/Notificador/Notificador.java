package Notificador;

import javax.mail.MessagingException;

public interface Notificador {
  void notificar(String destinatario, String asunto, String cuerpo) throws MessagingException;
}
