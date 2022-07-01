package Notificador;

import organizaciones.Organizacion;

import java.util.*;

public class NotificacionCalendarizada {

  //private  List<Organizacion> organizaciones;
  private Organizacion organizacion;
  public NotificacionCalendarizada(Organizacion organizacion){
    this.organizacion = organizacion;
  }

  public void notificacion( int dia,int hora,int minuto,int semanasFrecuencia) {
    Calendar c = new GregorianCalendar(2022, Calendar.JULY,dia,hora,minuto);
    Date momentoNotificacion = c.getTime();
    int tiempoRepeticion =  semanasFrecuencia; //CADA UNA SEMANA 60480000
    Timer temporizador = new Timer();
    temporizador.schedule(new Temporizador(this.organizacion), momentoNotificacion,tiempoRepeticion);
  }
}
