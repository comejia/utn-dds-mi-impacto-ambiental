package dominio.excepciones;

public class SectorNoExistenteException extends RuntimeException {

  public SectorNoExistenteException(String message) {
    super(message);
  }

}
