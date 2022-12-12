package dominio.transportes;

import dominio.organizaciones.FactorEmision;
import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("VH")
@Getter
public class VehiculoParticular extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipo;

  @Enumerated(EnumType.STRING)
  private TipoCombustible tipoCombustible;

  public VehiculoParticular() {}

  public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) {
    super();
    this.tipo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
  }

  @Override
  public boolean seComparte() {
    return true;
  }

  @Override
  public String toString() {
    return String.valueOf(tipo);
  }
}
