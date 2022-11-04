package dominio.Notificador;

public interface Notificador {
  void notificar(Contacto contacto, String asunto, String cuerpo);
}
