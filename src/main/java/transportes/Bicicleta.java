package transportes;

public class Bicicleta extends TransportePrivado {

  public Bicicleta() {
    super();
  }

  @Override
  public boolean seComparte() {
    return false;
  }
}
