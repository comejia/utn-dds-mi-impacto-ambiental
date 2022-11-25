package dominio.repositorios;

import dominio.organizaciones.TipoConsumo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioTipoDeConsumo implements WithGlobalEntityManager, Repositorio<TipoConsumo> {

  public static RepositorioTipoDeConsumo instance = new RepositorioTipoDeConsumo();

  public void agregar(TipoConsumo tipoConsumo) {
    entityManager().persist(tipoConsumo);
  }

  public List<TipoConsumo> listar() {
    return entityManager()
        .createQuery("from TipoConsumo", TipoConsumo.class)
        .getResultList();
  }

  public TipoConsumo buscarPorTipo(String tipo) {
    return entityManager().createQuery("from TipoConsumo t where t.tipo = :tipo", TipoConsumo.class)
        .setParameter("tipo", tipo)
        .getResultList()
        .get(0);
  }

}
