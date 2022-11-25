package dominio.transportes;

import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("VH")
@Getter
public class VehiculoParticular extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipo;

  @Enumerated(EnumType.STRING)
  @Embedded
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
}
