package trayectos;

import java.util.List;

import organizaciones.TipoConsumo;

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
  public double getHC(String unidad) {
    return this.tramos.stream().mapToDouble(tramo -> tramo.getHC(unidad)).sum();
  }
}
