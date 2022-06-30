package trayectos;

import java.util.List;

public class Trayecto {

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
    return tramos.stream().allMatch(tramo -> tramo.esVehiculoParticularOServicioContratado());
  }
  public double calcularHcTotal() {
    return this.tramos.stream().mapToDouble(tramo->tramo.calcularHC()).sum();
  }
}
