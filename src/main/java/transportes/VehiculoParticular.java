package transportes;

public class VehiculoParticular extends Transporte {

    private TipoVehiculo tipoVehiculo;
    private TipoCombustible tipoCombustible;

    public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible, String direccionInicio, String direccionLlegada) {
        this.tipoVehiculo = tipoVehiculo;
        this.tipoCombustible = tipoCombustible;
        this.direccionInicio = direccionInicio;
        this.direccionLlegada = direccionLlegada;
    }
}
