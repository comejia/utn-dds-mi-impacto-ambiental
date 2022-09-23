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

public class OrganizacionTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    SectorTerritorial KISS;
    Organizacion organizacion;
    AgenteSectorial pum;
    List<Double> hcPorUnidad;

    @BeforeEach
    public void init(){
        KISS = new SectorTerritorial(TipoSectorTerritorial.DEPARTAMENTO);
        organizacion = new Organizacion("DDS", TipoOrganizacion.INSTITUCION, new Direccion("Lugano", "Mozart", "2300"), Clasificacion.UNIVERSIDAD);
        pum = new AgenteSectorial(KISS);
    }
    @Test
    public void sePuedePersistirUnSectorTerritorial() {
        entityManager().persist(KISS);
    }
    @Test
    public void calculoHCPorOrganizacion() {
        assertEquals(0, organizacion.getHCTotal("kgCO2eq/kWh"));
    }
    @Test
    public void calculoHCPorSectorTerritorial() {
        assertEquals(0, KISS.getCalculoHCTotal("gCO2eq/m3"));
    }
}

