package dominio.excepciones;

public class UnidadIncompatibleException extends RuntimeException {
  public UnidadIncompatibleException(String mensaje) {
    super(mensaje);
  }
}
