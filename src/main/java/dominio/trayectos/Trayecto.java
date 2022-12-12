package dominio.trayectos;


import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trayecto extends EntidadPersistente {

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "egresoId")
  private final List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void agregarTramo(Tramo tramo) {
    this.tramos.add(tramo);
  }

  public double distanciaTotal() {
    return tramos.stream().mapToDouble(tramo -> tramo.distancia()).sum();
  }

  public boolean puedoCompartir() {
    return tramos.stream().allMatch(tramo -> tramo.esCompartido());
  }

  public double getHC(String unidad) {
    return this.tramos.stream().mapToDouble(tramo -> tramo.getHC(unidad)).sum();
  }
}
