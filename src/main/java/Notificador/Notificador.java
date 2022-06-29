package Notificador;

import organizaciones.Organizacion;

public interface Notificador {
  void notificar(Organizacion organizacion, String mensaje);
}
