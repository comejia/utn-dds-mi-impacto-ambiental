import Notificador.NotificacionCalendarizada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizaciones.Clasificacion;
import organizaciones.Organizacion;
import organizaciones.TipoOrganizacion;
import trayectos.Direccion;

import java.util.Date;

public class NotificacionCalendarizadaTest {

  private Organizacion organizacion;

  @BeforeEach
  public void setup() {
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
  }

  @Test
  public void siLaFechaDadaEsAhoraTeNotifica() {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(), ahora.getHours(), ahora.getMinutes(), 10000);
  }

  @Test
  public void dadaUnaFechaSeNotificaAutomaticamente() throws InterruptedException {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(), ahora.getHours(), ahora.getMinutes() + 1, 100000);
    Thread.sleep(30000);
  }

  @Test
  public void dadaUnaFechaYUnPeriodoSeNotificaPeriodicamente() throws InterruptedException {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(), ahora.getHours(), ahora.getMinutes(), 10000);
    Thread.sleep(30000);
  }
}
