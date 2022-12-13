package dominio.Notificador;

import dominio.organizaciones.Organizacion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class NotificacionCalendarizada {

  private Organizacion organizacion;

  public NotificacionCalendarizada(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public void notificacion(int dia, int hora, int minuto, int semanasFrecuencia) {
    //Calendar c = new GregorianCalendar(2022, Calendar.JULY, dia, hora, minuto);
    Date momentoNotificacion = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            //= c.getTime();
    Timer temporizador = new Timer();
    temporizador.schedule(new Temporizador(this.organizacion),momentoNotificacion,100000);
  }


}
