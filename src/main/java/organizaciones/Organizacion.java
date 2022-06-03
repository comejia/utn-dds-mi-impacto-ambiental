package organizaciones;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exepciones.TipoConsumoInexistente;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {

  private String razonSocial;
  private TipoOrganizacion tipoOrganizacion;
  private String ubicacion;
  private final List<Sector> sectores = new ArrayList<>();
  private Clasificacion clasificacion;
  private final List<Medicion> mediciones = new ArrayList<>();

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, String ubicacion, Clasificacion clasificacion) {
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
    filas.forEach(f -> {
      if (tiposExistentes.stream().noneMatch(tipo -> tipo.esMismoTipo(f[0]))) {
        throw new TipoConsumoInexistente("El Tipo de Consumo leido debe existir en el sistema");
      }
      agregarMedicion(f);
    });

    reader.close();
  }

  private void agregarMedicion(String[] fila) {
    this.mediciones.add(new Medicion(fila[0], Integer.parseInt(fila[1]), fila[2], fila[3]));
  }
}
