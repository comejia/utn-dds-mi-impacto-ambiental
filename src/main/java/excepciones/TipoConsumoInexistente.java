package exepciones;

public class TipoConsumoInexistente extends RuntimeException {

  public TipoConsumoInexistente(String mensaje) {
    super(mensaje);
  }
}
