package main;

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
      Miembro ramon = new Miembro("Ramon","Salazar", TipoDocumento.DNI,154502234);
      Miembro ramona = new Miembro("Ramona","Salazar", TipoDocumento.DNI,154502235);
      List<Miembro> miembros = new ArrayList<>();
      List<Miembro> mimbre = new ArrayList<>();
      miembros.add(ramon);
      mimbre.add(ramona);

      paradas.add(new Parada(20));
      paradas.add(new Parada(30));
      Organizacion UTN = new Organizacion(
              "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
      Organizacion UTN2 = new Organizacion(
              "DDS2", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
      Sector sector = new Sector(UTN,new ArrayList<>(),"sector");
      ramon.vincularASector(sector);
      Sector sector2 = new Sector(UTN,new ArrayList<>(),"sector2");
      ramona.vincularASector(sector2);
      Sector sector3 = new Sector(UTN,new ArrayList<>(),"sector3");
      Sector sector4 = new Sector(UTN2,new ArrayList<>(),"sector4");
      RepositorioSector.instance.agregar(sector);
      UTN.agregarSector(sector2);
      UTN2.agregarSector(sector4);
      UTN.agregarSector(sector3);
      UTN2.agregarSector(sector);
      //RepositorioSector.instance.agregar(sector2);
      //RepositorioSector.instance.agregar(sector3);
      //RepositorioSector.instance.agregar(sector4);
      RepositorioOrganizacion.instance.agregar(UTN);
      RepositorioUsuarios.instance.agregar(administrador);
      RepositorioUsuarios.instance.agregar(persona);
      Vinculacion vinculacion = new Vinculacion(UTN, administrador);
      RepositorioVinculaciones.instance.agregar(vinculacion);
      RepositorioOrganizacion.instance.agregar(new Organizacion("DDS"));
      RepositorioOrganizacion.instance.agregar(UTN2);
      RepositorioVinculaciones.instance.agregar(new Vinculacion(UTN, administrador));
      RepositorioTransportes.instance.agregar(new APie());
      RepositorioTransportes.instance.agregar(new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA));
      RepositorioTransportes.instance.agregar(new Bicicleta());
      RepositorioTransportes.instance.agregar(new ServicioContratado(TipoServicioContratado.TAXI));
      RepositorioTransportes.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 160));
      RepositorioTransportes.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 7));
    });
  }
}
