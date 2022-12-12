package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
public class Direccion extends EntidadPersistente {
  private int localidadId;
  private String calle;
  private String altura;

  public Direccion() {}

  public Direccion(int localidadId, String calle, String altura) {
      this.localidadId = localidadId;
    this.calle = calle;
    this.altura = altura;
  }

  public int getLocalidadId() {
    return localidadId;
  }

  public String getCalle() {
    return calle;
  }

  public String getAltura() {
    return altura;
  }

}
