package dominio.repositorios;

import dominio.organizaciones.TipoConsumo;
import dominio.transportes.Transporte;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTipoDeConsumo implements WithGlobalEntityManager {

  public static RepositorioTipoDeConsumo instance = new RepositorioTipoDeConsumo();
  private final List<TipoConsumo> tiposConsumos = new ArrayList<>();

  public TipoConsumo buscarPorTipo(String tipo) {
    return tiposConsumos.stream()
        .filter(t -> t.esMismoTipo(tipo))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Tipo de Consumo inexistente"));
//    return entityManager().createQuery("from TipoConsumo where tipo = " + tipo, TipoConsumo.class).getSingleResult();
  }

  public void agregar(TipoConsumo tipoConsumo) {
    //entityManager().persist(tipoConsumo);
    this.tiposConsumos.add(tipoConsumo);
  }

  public List<TipoConsumo> listar() {
    return tiposConsumos;
//    return entityManager()
//        .createQuery("from TipoConsumo", TipoConsumo.class)
//        .getResultList();
  }

}
