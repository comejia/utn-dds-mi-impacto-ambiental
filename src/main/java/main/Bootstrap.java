package main;

import dominio.organizaciones.Organizacion;
import dominio.organizaciones.TipoConsumo;
import dominio.repositorios.RepositorioTipoDeConsumo;
import dominio.repositorios.RepositorioTransportePrivado;
import dominio.repositorios.RepositorioTransportePublico;
import dominio.repositorios.RepositorioUsuarios;
import dominio.transportes.*;
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
      RepositorioUsuarios.instancia.agregar(administrador);
      RepositorioUsuarios.instancia.agregar(persona);

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

    });
  }
}
