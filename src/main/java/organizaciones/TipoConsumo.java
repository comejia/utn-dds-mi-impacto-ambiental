package organizaciones;

public class TipoConsumo {

  private String tipo;
  private String unidad;
  private String actividad;
  private int alcance;

  public TipoConsumo(String tipo, String unidad, String actividad, int alcance) {
    this.tipo = tipo;
    this.unidad = unidad;
    this.actividad = actividad;
    this.alcance = alcance;
  }

  public String getUnidad() {
    return this.unidad;
  }

  public String getTipo() {
    return this.tipo;
  }

  public boolean esMismoTipo(String tipo) {
    return this.tipo.equals(tipo);
  }
}
