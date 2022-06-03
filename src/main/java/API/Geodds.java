package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import trayectos.Distancia;
import trayectos.Direccion;
import javax.ws.rs.core.MediaType;

public class Geodds {

  private Client client;
  private static final String API_DISTANCIA = "https://app.swaggerhub.com/apis-docs/ezequieloscarescobar/geodds/1.0.0#/default/get_api_distancia";
  private static final String RESOURCE_DISTANCIA = "distancia";

  public Geodds() {
    this.client = Client.create();
  }

  public double getDistancia(Direccion direccionInicio, Direccion direccionFin) throws JsonProcessingException {
    ClientResponse recurso = this.client.resource(API_DISTANCIA)

        .queryParam("localidadOrigenId", String.valueOf(direccionInicio.getLocalidad()))
        .queryParam("calleOrigen", direccionInicio.getCalle()).queryParam("alturaOrigen", direccionInicio.getAltura())
        .queryParam("localidadDestinoId", String.valueOf(direccionFin.getLocalidad()))
        .queryParam("calleDestino", direccionFin.getCalle()).queryParam("alturaDestino", direccionFin.getAltura())
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer WXt8IlZJB6tEj/mJgSQpqVNq45VR919wOxFqCGKR7yk=").get(ClientResponse.class);

    String jsonResultApi = recurso.getEntity(String.class);
    JsonNode JsonNodeApi = new ObjectMapper().readTree(jsonResultApi);
    Double unidad = JsonNodeApi.get("unidad").asDouble();
    String valor = JsonNodeApi.get("valor").textValue();

    return unidad;
    // return new Distancia(unidad, valor);
  }

}
