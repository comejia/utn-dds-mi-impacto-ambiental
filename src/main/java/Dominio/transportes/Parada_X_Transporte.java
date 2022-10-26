package Dominio.transportes;

import Dominio.trayectos.Punto;
import Dominio.organizaciones.FactorEmision;
import Dominio.trayectos.Parada;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("PT")
public class Parada_X_Transporte extends Transporte {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Parada> paradas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TransportePublico> transportesPublicos;
    public Parada_X_Transporte(List<Parada> paradas,List<TransportePublico> transportesPublicos ) {
        this.paradas = paradas;
        this.transportesPublicos = transportesPublicos;
    }
    public Parada_X_Transporte(){}

    @Override
    public double getDistancia(Punto puntoInicio, Punto puntoFin) {
        return 0;
    }

    @Override
    public FactorEmision getFactorEmision() {
        return null;
    }

    @Override
    public boolean seComparte() {
        return false;
    }
}
