package dominio.Notificador;


import dominio.organizaciones.Organizacion;

import java.util.TimerTask;

public class Temporizador extends TimerTask {
  Organizacion organizacion;

  public Temporizador(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  @Override
  public void run() {
    System.out.print("Soy la tarea calendarizada\n");
    organizacion.notificarGuiaRecomendaciones();
  }
}
//TO DO -> PLANIFICACION EXTERNA