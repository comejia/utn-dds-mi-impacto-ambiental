package API;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import trayectos.Direccion;
import trayectos.Punto;

import javax.ws.rs.core.MediaType;

public class Geodds {

  private Client client;
  private static final String API_DISTANCIA = "https://app.swaggerhub.com/apis-docs/ezequieloscarescobar/geodds/1.0.0#/default/get_api_distancia";
  private static final String RESOURCE_DISTANCIA = "distancia";

  public Geodds() {
    this.client = Client.create();
  }

  //falta mas parametros
  public ClientResponse getDistancia(Direccion puntoInicio, Direccion puntoFin) {
    ClientResponse recurso = this.client.resource(API_DISTANCIA)
        .path(RESOURCE_DISTANCIA)
        .queryParam("localidadOrigenId",puntoInicio.getLocalidad())
        .queryParam("calleOrigen",puntoInicio.getCalle())
        .queryParam("alturaOrigen",puntoInicio.getAltura())
        .queryParam("localidadDestinoId",puntoFin.getLocalidad())
        .queryParam("calleDestino",puntoFin.getCalle())
        .queryParam("alturaDestino",puntoFin.getAltura())
        .accept(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);
    return recurso;
  }


}
