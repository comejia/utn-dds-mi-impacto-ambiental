package transportes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("VH")
public class VehiculoParticular extends TransportePrivado {

  @Enumerated(EnumType.STRING)
  private TipoVehiculo tipoVehiculo;

  @Enumerated(EnumType.STRING)
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
