package main;

import dominio.Notificador.Contacto;
import dominio.miembros.Miembro;
import dominio.miembros.TipoDocumento;
import dominio.organizaciones.*;
import dominio.repositorios.*;
import dominio.transportes.*;
import dominio.trayectos.Direccion;
import dominio.trayectos.Parada;
import dominio.usuarios.Administrador;
import dominio.usuarios.Persona;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      Administrador administrador = new Administrador("admin", "Admin2022@");
      Persona persona = new Persona("dds", "Dds2022@");
      SectorTerritorial sectorTerritorial = new SectorTerritorial(TipoSectorTerritorial.DEPARTAMENTO, "sectorTerritorial");
      RepositorioUsuarios.instance.agregar(administrador);
      RepositorioUsuarios.instance.agregar(persona);
      RepositorioSectorTerritorial.instance.agregar(sectorTerritorial);
      RepositorioSectorTerritorial.instance.agregar(new SectorTerritorial(TipoSectorTerritorial.PROVINCIA,"unSector"));
      RepositorioSectorTerritorial.instance.agregar(new SectorTerritorial(TipoSectorTerritorial.PROVINCIA,"otroSector"));

      RepositorioTipoDeConsumo.instance.agregar(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
      RepositorioTipoDeConsumo.instance.agregar(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));

      List<Parada> paradas = new ArrayList<>();
      paradas.add(new Parada(20));
      paradas.add(new Parada(30));
      Organizacion UTN = new Organizacion(
              "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
      RepositorioOrganizacion.instance.agregar(UTN);
      RepositorioUsuarios.instance.agregar(administrador);
      RepositorioUsuarios.instance.agregar(persona);
      Vinculacion vinculacion = new Vinculacion(UTN, administrador);
      RepositorioVinculaciones.instance.agregar(vinculacion);
      RepositorioOrganizacion.instance.agregar(new Organizacion("DDS"));
      RepositorioVinculaciones.instance.agregar(new Vinculacion(UTN, administrador));
      RepositorioTransportes.instance.agregar(new APie());
      RepositorioTransportes.instance.agregar(new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA));
      RepositorioTransportes.instance.agregar(new Bicicleta());
      RepositorioTransportes.instance.agregar(new ServicioContratado(TipoServicioContratado.TAXI));
      RepositorioTransportes.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 160));
      RepositorioTransportes.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 7));

      Contacto migue = new Contacto("cmejia@frba.utn.edu.ar", "+5491155136689");
      Organizacion noti = new Organizacion(
          "NOTI", TipoOrganizacion.INSTITUCION, new Direccion("caba", "siempre viva", "666"), Clasificacion.UNIVERSIDAD);
      noti.agregarContacto(migue);

      RepositorioOrganizacion.instance.agregar(noti);
    });
  }
}
