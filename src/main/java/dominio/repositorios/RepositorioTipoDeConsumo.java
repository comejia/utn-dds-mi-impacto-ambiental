package dominio.repositorios;

import dominio.organizaciones.TipoConsumo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTipoDeConsumo implements WithGlobalEntityManager {

  private static final RepositorioTipoDeConsumo instance = new RepositorioTipoDeConsumo();
  private final List<TipoConsumo> tipoConsumos = new ArrayList<TipoConsumo>() {{
    add(new TipoConsumo("Gas Natural", "m3", "Combustión fija", 1));
    add(new TipoConsumo("Electricidad", "kWh", "Electricidad adquirida", 2));
  }};

  public static RepositorioTipoDeConsumo getInstance() {
    return instance;
  }

  public TipoConsumo getTipoConsumo(String tipo) {
    return tipoConsumos.stream()
        .filter(t -> t.esMismoTipo(tipo))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Tipo de Consumo inexistente"));
  }

  public List<TipoConsumo> listar() {
    return tipoConsumos;
//    return entityManager()
//        .createQuery("from TipoConsumo", TipoConsumo.class)
//        .getResultList();
  }

}
