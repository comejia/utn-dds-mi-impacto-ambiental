package organizaciones;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import excepciones.TipoConsumoInexistente;
import trayectos.Direccion;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {

  private String razonSocial;
  private TipoOrganizacion tipoOrganizacion;
  private Direccion ubicacion;
  private final List<Sector> sectores = new ArrayList<>();
  private Clasificacion clasificacion;
  private final List<Medicion> mediciones = new ArrayList<>();

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Direccion ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public void agregarSector(Sector sector) {
    this.sectores.add(sector);
  }

  public void cargarMediciones(String path, List<TipoConsumo> tiposExistentes) throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(path));
    List<String[]> filas = reader.readAll();
    filas.forEach(fila -> {
      TipoConsumo tipoConsumo = tiposExistentes.stream().filter(tipo -> tipo.esMismoTipo(fila[0])).findFirst().orElseThrow(()->new TipoConsumoInexistente("El Tipo de Consumo leido debe existir en el sistema"));
      agregarMedicion(fila,tipoConsumo);
    });
    reader.close();
  }

  private void agregarMedicion(String[] fila,TipoConsumo tipoConsumo) {
    this.mediciones.add(new Medicion(tipoConsumo, new BigDecimal(fila[1]), fila[2], fila[3]));
  }
  
  private int getCalculoHCTotal(TipoConsumo tipo) {
    return this.mediciones.stream().mapToInt(medicion -> medicion.getHuellaCarbono(tipo)).sum();
  }
}
