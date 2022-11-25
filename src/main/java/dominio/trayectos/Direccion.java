package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Direccion extends EntidadPersistente {
  private String localidad;
  private String calle;
  private String altura;

  public Direccion() {}

  public Direccion(String localidad, String calle, String altura) {
      this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }

  public String getLocalidad() {
    return localidad;
  }

  public String getCalle() {
    return calle;
  }

  public String getAltura() {
    return altura;
  }

}
