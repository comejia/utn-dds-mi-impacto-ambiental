import dominio.Notificador.NotificacionCalendarizada;
import dominio.Notificador.NotificarPorMail;
import dominio.Notificador.NotificarPorWhatsApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.organizaciones.Clasificacion;
import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoOrganizacion;
import dominio.trayectos.Direccion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

public class NotificacionCalendarizadaTest {

  private Organizacion organizacion;

  @BeforeEach
  public void setup() throws IOException {
    Properties properties = new Properties();
    properties.load(Files.newInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/mail_data.properties").toPath()));
    this.organizacion = new Organizacion(
        "DDS", TipoOrganizacion.INSTITUCION, new Direccion(1, "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
    this.organizacion.agregarNotificador(new NotificarPorMail(properties.getProperty("user"), properties.getProperty("pass")));
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
