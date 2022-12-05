package main;


import dominio.organizaciones.*;
import dominio.repositorios.*;
import dominio.transportes.*;
import dominio.trayectos.Direccion;
import dominio.trayectos.Parada;
import dominio.usuarios.Administrador;
import dominio.usuarios.Persona;
import dominio.usuarios.Role;
import dominio.usuarios.Usuario;
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
      RepositorioUsuarios.instance.agregar(administrador);
      RepositorioUsuarios.instance.agregar(persona);

      RepositorioTipoDeConsumo.instance.agregar(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
      RepositorioTipoDeConsumo.instance.agregar(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));
      //persist(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
      //persist(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));

      List<Parada> paradas = new ArrayList<>();
      paradas.add(new Parada(20));
      paradas.add(new Parada(30));
      RepositorioTransportePublico.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 160));
      RepositorioTransportePublico.instance.agregar(new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 7));
      RepositorioTransportePrivado.instance.agregar(new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA));
      RepositorioTransportePrivado.instance.agregar(new Bicicleta());

      Organizacion UTN = new Organizacion(
              "DDS", TipoOrganizacion.INSTITUCION, new Direccion("CABA", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
      RepositorioOrganizacion.instance.agregar(UTN);
      Organizacion microsoft = new Organizacion(
          "Microsoft", TipoOrganizacion.EMPRESA, new Direccion("CABA", "Carlos M. Della Paolera", "261"), Clasificacion.EMPRESA_SECTOR_PRIMARIO);
      RepositorioOrganizacion.instance.agregar(microsoft);
      Organizacion legislatura = new Organizacion(
          "Legislatura Porteña", TipoOrganizacion.GUBERNAMENTAL, new Direccion("CABA", "Peru", "160"), Clasificacion.MINISTERIO);
      RepositorioOrganizacion.instance.agregar(legislatura);

      SectorTerritorial buenosAires = new SectorTerritorial("Buenos Aires",TipoSectorTerritorial.PROVINCIA);
      RepositorioSectorTerritorial.instance.agregar(buenosAires);
      SectorTerritorial formosa = new SectorTerritorial("Formosa",TipoSectorTerritorial.PROVINCIA);
      RepositorioSectorTerritorial.instance.agregar(formosa);
      SectorTerritorial jujuy = new SectorTerritorial("Jujuy",TipoSectorTerritorial.PROVINCIA);
      RepositorioSectorTerritorial.instance.agregar(jujuy);

    Vinculacion vinculacion = new Vinculacion(UTN, administrador);
    RepositorioVinculaciones.instance.agregar(vinculacion);
        RepositorioOrganizacion.instance.agregar(new Organizacion("DDS"));
        RepositorioVinculaciones.instance.agregar(new Vinculacion(UTN, administrador));
      });
  }
}
