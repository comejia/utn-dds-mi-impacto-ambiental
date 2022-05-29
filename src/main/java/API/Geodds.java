package API;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;

public class Geodds {

  private Client client;
  private static final String API_DISTANCIA = "https://app.swaggerhub.com/apis-docs/ezequieloscarescobar/geodds/1.0.0#/default/get_api_distancia";
  private static final String RESOURCE_DISTANCIA = "distancia";

  public Geodds() {
    this.client = Client.create();
  }

  //falta mas parametros
  public ClientResponse getDistancia(String puntoInicio, String puntoFin) {
    ClientResponse recurso = this.client.resource(API_DISTANCIA)
        .path(RESOURCE_DISTANCIA)
        .queryParam("localidadOrigenId","1")
        .queryParam("calleOrigen",puntoFin)
        .queryParam("alturaOrigen","1")
        .queryParam("localidadDestinoId","1")
        .queryParam("calleDestino",puntoInicio)
        .queryParam("alturaDestino","1")
        .accept(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);
    return recurso;
  }
}
