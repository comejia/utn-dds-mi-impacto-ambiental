package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import trayectos.Distancia;
import trayectos.Punto;

import javax.ws.rs.core.MediaType;

public class Geodds {

  private Client client;
  private static final String API_DISTANCIA = "https://app.swaggerhub.com/apis-docs/ezequieloscarescobar/geodds/1.0.0#/default/get_api_distancia";
  private static final String RESOURCE_DISTANCIA = "distancia";

  public Geodds() {
    this.client = Client.create();
  }

  public Distancia getDistancia(Punto puntoInicio, Punto puntoFin) throws JsonProcessingException {

    ClientResponse recurso = this.client.resource(API_DISTANCIA)
        .path(RESOURCE_DISTANCIA)
        .queryParam("localidadOrigenId", String.valueOf(puntoInicio.getLocalidad()))
        .queryParam("calleOrigen", puntoInicio.getCalle())
        .queryParam("alturaOrigen", puntoInicio.getAltura())
        .queryParam("localidadDestinoId",String.valueOf(puntoFin.getLocalidad()))
        .queryParam("calleDestino",puntoFin.getCalle())
        .queryParam("alturaDestino",puntoFin.getAltura())
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer WXt8IlZJB6tEj/mJgSQpqVNq45VR919wOxFqCGKR7yk=")
        .get(ClientResponse.class);

    String jsonResultApi = recurso.getEntity(String.class);
    JsonNode JsonNodeApi = new ObjectMapper().readTree(jsonResultApi);
    Double unidad = JsonNodeApi.get("unidad").asDouble();
    String valor = JsonNodeApi.get("valor").textValue();

    return new Distancia(unidad,valor);
  }
}
