package dominio.repositorios;
import dominio.organizaciones.*;
import funciones.ContenidoReportes;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepositorioOrganizacion implements WithGlobalEntityManager {

  public static RepositorioOrganizacion instance = new RepositorioOrganizacion();

    public void agregar(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }

    public static RepositorioOrganizacion getInstance() {
        if (instance == null) {
            instance = new RepositorioOrganizacion();
        }
        return instance;
    }

    public List<Organizacion> listar() {
        return entityManager()
            .createQuery("from Organizacion", Organizacion.class)
            .getResultList();
    }


        public Organizacion buscarOrganizacion(String razonSocial){
            return entityManager().createQuery("from Organizacion O where O.razonSocial = :razonSocial", Organizacion.class)
                    .setParameter("razonSocial", razonSocial)
                    .getResultList()
                    .get(0);
        }
    public Organizacion buscarOrganizacion(int id) {
        return entityManager().find(Organizacion.class, id);
    }

    public Map<String, Double> hcPorOrganizacion(String unidad) {
        List<ContenidoReportes> contenidosAux = new ArrayList<>();
        List<Organizacion> organizaciones = this.listar();
        Map<String, Double> contenidosFinal;

        organizaciones.forEach(organizacion -> {
            ContenidoReportes contenido = new ContenidoReportes(
                organizacion.getTipoOrganizacion().toString(),
                organizacion.getHCTotal(unidad)
            );
            contenidosAux.add(contenido);
        });

        contenidosFinal = contenidosAux.stream()
            .collect(Collectors.groupingBy(
                ContenidoReportes::getCabecera,
                Collectors.summingDouble(ContenidoReportes::getValor)
            ));

        return contenidosFinal;
    }
  public void actualizar(Organizacion organizacion) {
    entityManager().merge(organizacion);
  }
}
