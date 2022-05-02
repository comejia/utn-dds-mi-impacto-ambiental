package transportes;

public class VehiculoParticular extends Transporte {

    private TipoVehiculo tipoVehiculo;
    private TipoCombustible tipoCombustible;

    public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) {
        this.tipoVehiculo = tipoVehiculo;
        this.tipoCombustible = tipoCombustible;
    }
}
