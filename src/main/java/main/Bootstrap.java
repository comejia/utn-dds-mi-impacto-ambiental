package main;

import dominio.organizaciones.TipoConsumo;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      persist(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
      persist(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));
    });
  }
}
