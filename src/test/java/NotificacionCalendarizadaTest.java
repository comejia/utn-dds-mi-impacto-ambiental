import Notificador.Contacto;
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
  private Contacto contacto1;
  private Contacto contacto2;

  @BeforeEach
  public void setup() {
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano","Mozart","2300"), Clasificacion.UNIVERSIDAD);
    // this.contacto1 = new Contacto("migue.racedo.oviedo@gmail.com","+5491155136689");
    // organizacion.agregarContacto(contacto1);
  }

  @Test
  public void siLaFechaDadaEsAhoraTeNotifica() {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(), ahora.getHours(), ahora.getMinutes() , 10000);
  }
    @Test
  public void dadaUnaFechaSeNotificaAutomaticamente() throws InterruptedException {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(),ahora.getHours() , ahora.getMinutes()+1 ,100000);
    Thread.sleep(30000);
  }
  @Test
  public void dadaUnaFechaYUnPeriodoSeNotificaPeriodicamente() throws InterruptedException {
    NotificacionCalendarizada notificacionCalendarizada = new NotificacionCalendarizada(organizacion);
    Date ahora = new Date(System.currentTimeMillis());
    notificacionCalendarizada.notificacion(ahora.getDate(),ahora.getHours() , ahora.getMinutes() ,10000);
    Thread.sleep(30000);
  }
}
