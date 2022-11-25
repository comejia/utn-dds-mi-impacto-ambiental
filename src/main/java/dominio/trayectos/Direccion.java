package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;

import javax.persistence.Entity;

@Entity
public class Direccion extends EntidadPersistente {
  private String localidad;
  private String calle;
  private String altura;

  public Direccion(String localidad, String calle, String altura) {
    this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }
  public Direccion() {
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
