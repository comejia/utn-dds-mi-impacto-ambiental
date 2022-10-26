package Dominio.excepciones;

public class SectorNoExistenteException extends RuntimeException {

  public SectorNoExistenteException(String message) {
    super(message);
  }

}
