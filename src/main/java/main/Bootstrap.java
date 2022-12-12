package main;


import dominio.miembros.Miembro;
import dominio.miembros.TipoDocumento;
import dominio.organizaciones.*;
import dominio.repositorios.*;
import dominio.transportes.*;
import dominio.trayectos.*;
import dominio.usuarios.Administrador;
import dominio.usuarios.Persona;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      Administrador administrador = new Administrador("admin", "Admin2022@");
      Persona persona = new Persona("dds", "Dds2022@");
      SectorTerritorial sectorTerritorial = new SectorTerritorial( "sectorTerritorial",TipoSectorTerritorial.DEPARTAMENTO);
      RepositorioUsuarios.instance.agregar(administrador);
      RepositorioUsuarios.instance.agregar(persona);
      RepositorioSectorTerritorial.instance.agregar(sectorTerritorial);
      RepositorioSectorTerritorial.instance.agregar(new SectorTerritorial("unSector",TipoSectorTerritorial.PROVINCIA));
      RepositorioSectorTerritorial.instance.agregar(new SectorTerritorial("otroSector",TipoSectorTerritorial.PROVINCIA));

      TipoConsumo gasNatural = new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1);
      TipoConsumo electricidad = new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2);
      TipoConsumo cinta = new TipoConsumo("Electricidad", "m", "Pegar", 1);
      RepositorioTipoDeConsumo.instance.agregar(gasNatural);
      RepositorioTipoDeConsumo.instance.agregar(electricidad);
      RepositorioTipoDeConsumo.instance.agregar(cinta);

      //persist(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
      //persist(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));

      FactorEmision factor1 =  new FactorEmision(10, "kgCO2eq/kWh", electricidad);
      FactorEmision factor2 =  new FactorEmision(10, "kgCO2eq/m", cinta);
      FactorEmision factor3 =  new FactorEmision(10, "gCO2eq/m3", gasNatural);
      RepositorioFactorEmision.instance.agregarFactorEmision(factor1);
      RepositorioFactorEmision.instance.agregarFactorEmision(factor2);
      RepositorioFactorEmision.instance.agregarFactorEmision(factor3);

      List<Parada> paradas = new ArrayList<>();
      Parada parada1 = new Parada(20);
      Parada parada2 = new Parada(30);
      paradas.add(parada1);
      paradas.add(parada2);
      TransportePublico colectivo160 = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 160,factor3);
      TransportePublico colectivo7 = new TransportePublico(TipoTransportePublico.COLECTIVO, paradas, 7,factor2);
      VehiculoParticular camionetaNafta = new VehiculoParticular(TipoVehiculo.CAMIONETA, TipoCombustible.NAFTA);
      VehiculoParticular autoNafta = new VehiculoParticular(TipoVehiculo.AUTO, TipoCombustible.NAFTA);
      Bicicleta bici = new Bicicleta();
      RepositorioTransportePublico.instance.agregar(colectivo160);
      RepositorioTransportePublico.instance.agregar(colectivo7);
      RepositorioTransportePrivado.instance.agregar(camionetaNafta);
      RepositorioTransportePrivado.instance.agregar(autoNafta);
      RepositorioTransportePrivado.instance.agregar(bici);

      Punto puntoInicioACamionetaNafta = new Punto(parada1);
      Punto puntoDestinoACamionetaNafta = new Punto(parada2);
      Tramo tramoACamionetaNafta = new Tramo(camionetaNafta,puntoInicioACamionetaNafta,puntoDestinoACamionetaNafta);
      Punto puntoInicioBCamionetaNafta = new Punto(parada1);
      Punto puntoDestinoBCamionetaNafta = new Punto(parada2);
      Tramo tramoBCamionetaNafta = new Tramo(camionetaNafta,puntoInicioBCamionetaNafta,puntoDestinoBCamionetaNafta);
      RepositorioPuntos.instance.agregar(puntoInicioACamionetaNafta);
      RepositorioPuntos.instance.agregar(puntoDestinoACamionetaNafta);
      RepositorioPuntos.instance.agregar(puntoInicioBCamionetaNafta);
      RepositorioPuntos.instance.agregar(puntoDestinoBCamionetaNafta);
      RepositorioTramos.instance.agregar(tramoACamionetaNafta);
      RepositorioTramos.instance.agregar(tramoBCamionetaNafta);

      Punto puntoInicioAAutoNafta = new Punto(parada2);
      Punto puntoDestinocAAutoNafta = new Punto(parada1);
      Tramo tramoAAutoNafta = new Tramo(autoNafta,puntoInicioAAutoNafta,puntoDestinocAAutoNafta);
      Punto puntoInicioBAutoNafta = new Punto(parada2);
      Punto puntoDestinoBAutoNafta = new Punto(parada1);
      Tramo tramoBAutoNafta = new Tramo(autoNafta,puntoInicioBAutoNafta,puntoDestinoBAutoNafta);
      RepositorioPuntos.instance.agregar(puntoInicioAAutoNafta);
      RepositorioPuntos.instance.agregar(puntoDestinocAAutoNafta);
      RepositorioPuntos.instance.agregar(puntoInicioBAutoNafta);
      RepositorioPuntos.instance.agregar(puntoDestinoBAutoNafta);
      RepositorioTramos.instance.agregar(tramoAAutoNafta);
      RepositorioTramos.instance.agregar(tramoBAutoNafta);

      Trayecto trayecto1 = new Trayecto(Arrays.asList(tramoACamionetaNafta,tramoBCamionetaNafta));
      Trayecto trayecto2 = new Trayecto(Arrays.asList(tramoAAutoNafta,tramoBAutoNafta));
      RepositorioTrayectos.instance.agregar(trayecto1);
      RepositorioTrayectos.instance.agregar(trayecto2);

      Organizacion UTN = new Organizacion(
          "DDS", TipoOrganizacion.INSTITUCION, new Direccion(3, "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
      RepositorioOrganizacion.instance.agregar(UTN);
      Organizacion microsoft = new Organizacion(
          "Microsoft", TipoOrganizacion.EMPRESA, new Direccion(3, "Carlos M. Della Paolera", "261"), Clasificacion.EMPRESA_SECTOR_PRIMARIO);
      RepositorioOrganizacion.instance.agregar(microsoft);
      Organizacion legislatura = new Organizacion(
          "Legislatura Porteña", TipoOrganizacion.GUBERNAMENTAL, new Direccion(3, "Peru", "160"), Clasificacion.MINISTERIO);
      RepositorioOrganizacion.instance.agregar(legislatura);

      SectorTerritorial buenosAires = new SectorTerritorial("Buenos Aires", TipoSectorTerritorial.PROVINCIA,Arrays.asList(microsoft,legislatura));
      RepositorioSectorTerritorial.instance.agregar(buenosAires);
      SectorTerritorial colonia = new SectorTerritorial("Colonia", TipoSectorTerritorial.DEPARTAMENTO, Arrays.asList(UTN,microsoft,legislatura));
      RepositorioSectorTerritorial.instance.agregar(colonia);
      SectorTerritorial lugano = new SectorTerritorial("Lugano", TipoSectorTerritorial.BARRIO, Collections.singletonList(legislatura));
      RepositorioSectorTerritorial.instance.agregar(lugano);

      Miembro jose = new Miembro("Jose", "Lopez", TipoDocumento.DNI, 1525135681,Collections.singletonList(trayecto1));
      Miembro jorge = new Miembro("Jorge", "Messi", TipoDocumento.DNI, 1333804417,Arrays.asList(trayecto1,trayecto2));
      Miembro ricardo = new Miembro("Ricardo", "Fort", TipoDocumento.DNI, 1333456317,Collections.singletonList(trayecto1));
      RepositorioMiembros.instance.agregar(jose);
      RepositorioMiembros.instance.agregar(jorge);
      RepositorioMiembros.instance.agregar(ricardo);

      Sector seguridad = new Sector(UTN, Arrays.asList(jose,jorge));
      Sector contaduria = new Sector(microsoft, Collections.singletonList(ricardo));
      Sector sistemas = new Sector(legislatura, Arrays.asList(jose,jorge,ricardo));
      RepositorioSector.instance.agregar(seguridad);
      RepositorioSector.instance.agregar(contaduria);
      RepositorioSector.instance.agregar(sistemas);

      Vinculacion vinculacion = new Vinculacion(UTN, administrador);
      RepositorioVinculaciones.instance.agregar(vinculacion);
      RepositorioVinculaciones.instance.agregar(new Vinculacion(UTN, administrador));
    });
  }
}
