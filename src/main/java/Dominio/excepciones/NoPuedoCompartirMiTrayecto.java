package Dominio.excepciones;

public class NoPuedoCompartirMiTrayecto extends RuntimeException {

  public NoPuedoCompartirMiTrayecto(String message) {
    super(message);
  }
}
