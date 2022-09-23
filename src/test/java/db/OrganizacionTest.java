package db;

import miembros.AgenteSectorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import organizaciones.*;
import transportes.*;
import trayectos.Direccion;
import trayectos.Parada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrganizacionTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    SectorTerritorial KISS;
    Organizacion organizacion;
    AgenteSectorial pum;
    List<String> unidades;

    @BeforeEach
    public void init(){
        KISS = new SectorTerritorial(TipoSectorTerritorial.DEPARTAMENTO);
        organizacion = new Organizacion("DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
        pum = new AgenteSectorial(KISS);
        unidades.add("kgCO2eq/kWh");
        unidades.add("gCO2eq/m3");
    }
    @Test
    public void sePuedePersistirUnSectorTerritorial() {
        entityManager().persist(KISS);
    }
    @Test
    public void sePuedePersistirUnaOrganizacion() {
        entityManager().persist(organizacion);
    }
    @Test
    public void calculoHCPorOrganizacion() {
        assertEquals(0, organizacion.getHCTotal("kgCO2eq/kWh"));
    }
    @Test
    public void composicionHcOrganizacion() {
        assertEquals(0, organizacion.getHCPorPorcentaje(unidades));
    }
    @Test
    public void composicionHcSectorTerritorial() {
        assertEquals(0, KISS.getHcSector(unidades));
    }
    @Test
    public void evolucionHcOrganizacion(){
        assertNotEquals(20, organizacion.getHc().get(organizacion.getHc().size()-1));
    }
    @Test
    public void evolucionHcSectorTerritorial(){
        assertNotEquals(10, KISS.getHcEvolucion().get(KISS.getHcEvolucion().size()-1).get(KISS.getHcEvolucion().size()-1));
    }
}

