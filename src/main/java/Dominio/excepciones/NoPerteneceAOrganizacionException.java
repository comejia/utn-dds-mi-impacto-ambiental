package Dominio.excepciones;

public class NoPerteneceAOrganizacionException extends RuntimeException {

  public NoPerteneceAOrganizacionException(String message) {
    super(message);
  }

}
