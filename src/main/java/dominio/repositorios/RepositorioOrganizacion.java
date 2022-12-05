package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Direccion;
import dominio.usuarios.Usuario;
import funciones.ContenidoReportes;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepositorioOrganizacion implements WithGlobalEntityManager {

    public static RepositorioOrganizacion instance = new RepositorioOrganizacion();

    Organizacion UTN = new Organizacion(
            "DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
    Organizacion microsoft = new Organizacion(
        "Microsoft", TipoOrganizacion.EMPRESA, new Direccion("CABA", "Carlos M. Della Paolera", "261"), Clasificacion.EMPRESA_SECTOR_PRIMARIO);
    Organizacion legislatura = new Organizacion(
        "Legislatura Porteña", TipoOrganizacion.GUBERNAMENTAL, new Direccion("CABA", "Peru", "160"), Clasificacion.MINISTERIO);

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
        List<Organizacion> organizaciones = new ArrayList<>();
        organizaciones.add(UTN);
        organizaciones.add(microsoft);
        organizaciones.add(legislatura);
        return organizaciones;
    }

    public Organizacion buscarOrganizacion(String razonSocial) {
        System.out.print(razonSocial);
        return entityManager()
                .createQuery("from Organizacion O where O.razonSocial =:razonSocial", Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList().get(0);
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
}

