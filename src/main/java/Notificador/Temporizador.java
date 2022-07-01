package Notificador;


import organizaciones.Organizacion;

import java.util.TimerTask;

public class Temporizador extends TimerTask {
  Organizacion organizacion;

  public Temporizador(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  @Override
  public void run() {
    System.out.print("Soy la tarea calendarizada\n");
  }
}
