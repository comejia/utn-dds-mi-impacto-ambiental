package repositorios;

import organizaciones.TipoConsumo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTipoDeConsumo {

  private static final RepositorioTipoDeConsumo instance = new RepositorioTipoDeConsumo();
  private final List<TipoConsumo> tipoConsumos = new ArrayList<>();

  public static RepositorioTipoDeConsumo getInstance() {
    return instance;
  }

  public TipoConsumo getTipoConsumo(String tipo) {
    return tipoConsumos.stream()
        .filter(t -> t.esMismoTipo(tipo))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Tipo de Consumo inexistente"));
  }

}
