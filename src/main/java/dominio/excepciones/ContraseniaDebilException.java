package dominio.excepciones;

public class ContraseniaDebilException extends RuntimeException {

  public ContraseniaDebilException(String message) {
    super(message);
  }
}