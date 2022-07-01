package Notificador;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class NotificacionCalendarizada {
  public void notificacion(){
    Date horaActual = new Date(System.currentTimeMillis());
    Calendar c = Calendar.getInstance();
    c.setTime(horaActual);
    if(c.get(Calendar.HOUR_OF_DAY)>10 | c.get(Calendar.DAY_OF_MONTH)!=1){
      c.set(Calendar.DAY_OF_MONTH,1);
      if(c.get(Calendar.MONTH)==Calendar.DECEMBER){
        c.set(Calendar.YEAR,c.get(Calendar.YEAR)+1);
        c.set(Calendar.MONTH, 1);
      }else {
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
      }
    }
    c.set(Calendar.HOUR_OF_DAY, 10);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

    Date momentoNotificacion = c.getTime();
    int tiempoRepeticion = 241920000; //CADA UN MES
    Timer temporizador = new Timer();
    temporizador.schedule(new Temporizador(), momentoNotificacion,241920000);
  }
}
