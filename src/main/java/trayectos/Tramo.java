package trayectos;
import organizaciones.FactorEmision;
import organizaciones.TipoConsumo;
import repositorios.RepositorioFactorEmision;
import transportes.ServicioContratado;
import transportes.Transporte;
import transportes.VehiculoParticular;

public class Tramo {
  private Transporte transporte;
  private Punto puntoInicio;
  private Punto puntoFinal;

  public Tramo(Transporte transporte, Punto puntoInicio, Punto puntoFinal) {
    this.transporte = transporte;
    this.puntoInicio = puntoInicio;
    this.puntoFinal = puntoFinal;
  }

  public double distancia() {
    return transporte.getDistancia(puntoInicio, puntoFinal);
  }

  public boolean esVehiculoParticularOServicioContratado() {
    return (transporte instanceof VehiculoParticular) || (transporte instanceof ServicioContratado);
   }
   
  public double getHC(String unidad) {
    // TODO Auto-generated method stub
    FactorEmision fe = this.transporte.getFactorEmision();
    return this.distancia() * fe.getValor();
  }
}
