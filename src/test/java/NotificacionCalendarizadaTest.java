import Notificador.NotificacionCalendarizada;
import Notificador.NotificarPorMail;
import Notificador.NotificarPorWhatsApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import organizaciones.Clasificacion;
import organizaciones.Organizacion;
import organizaciones.TipoOrganizacion;
import trayectos.Direccion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

public class NotificacionCalendarizadaTest {

  private Organizacion organizacion;
  private Properties properties;

  @BeforeEach
  public void setup() throws IOException {
    properties = new Properties();
    properties.load(Files.newInputStream(new File( System.getProperty("user.dir") + "/src/main/resources/mail_data.properties").toPath()));
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
    this.organizacion.agregarNotificador(new NotificarPorMail(System.getenv("user"), System.getenv("pass")));
    this.organizacion.agregarNotificador(new NotificarPorWhatsApp());
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
