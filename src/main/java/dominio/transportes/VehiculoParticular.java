package dominio.transportes;

import javax.persistence.*;

@Entity
@DiscriminatorValue("VH")
public class VehiculoParticular extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipoVehiculo;

  @Enumerated(EnumType.STRING)
  @Embedded
  private TipoCombustible tipoCombustible;

  public VehiculoParticular(){}

  public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) {
    super();
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
  }

  @Override
  public boolean seComparte() {
    return true;
  }
}
