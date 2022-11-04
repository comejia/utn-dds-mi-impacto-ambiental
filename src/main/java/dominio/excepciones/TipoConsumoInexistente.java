package dominio.excepciones;

public class TipoConsumoInexistente extends RuntimeException {

  public TipoConsumoInexistente(String mensaje) {
    super(mensaje);
  }
}
