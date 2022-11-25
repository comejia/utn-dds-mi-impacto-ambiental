package dominio.trayectos;

import dominio.usuarios.EntidadPersistente;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Entity
public class Punto extends EntidadPersistente {

  @OneToOne(cascade = CascadeType.ALL)
  private Direccion direccion;

  @OneToOne(cascade = CascadeType.ALL)
  private Parada parada;

  public Punto(Direccion direccion) {
    this.direccion = direccion;
  }

  public Punto(Parada parada) {
    this.parada = parada;
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public Parada getParada() {
    return parada;
  }

}