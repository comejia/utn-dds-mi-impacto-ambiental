package trayectos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Trayecto {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "egreso_id")
  private final List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public Long getId() {
    return id;
  }

  public void agregarTramo(Tramo tramo) {
    this.tramos.add(tramo);
  }

  public double distanciaTotal() {
    return tramos.stream().mapToDouble(tramo -> tramo.distancia()).sum();
  }

  public boolean puedoCompartir() {
    return tramos.stream().allMatch(tramo -> tramo.esCompartido());
  }

  public double getHC(String unidad) {
    return this.tramos.stream().mapToDouble(tramo -> tramo.getHC(unidad)).sum();
  }
}
