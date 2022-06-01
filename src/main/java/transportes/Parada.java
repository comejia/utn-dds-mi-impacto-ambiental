package transportes;

public class Parada {

  //private String direccion;
  private double distanciaProximaParada;

  public Parada(/*String direccion,*/ double distanciaProxParada) {
   //this.direccion = direccion;
    this.distanciaProximaParada = distanciaProxParada;
  }

 /* public String getDireccion() {
    return direccion;
  }*/

  public double getDistanciaProximaParada() {
    return distanciaProximaParada;
  }

}
