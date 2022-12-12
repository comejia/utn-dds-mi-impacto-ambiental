package dominio.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import dominio.trayectos.Direccion;
import dominio.trayectos.Distancia;

import javax.ws.rs.core.MediaType;

public class Geodds implements Geolocalizacion {

  private Client client;
  private static final String API_DISTANCIA = "https://ddstpa.com.ar/api/distancia";

  public Geodds() {
    this.client = Client.create();
  }

  public double getDistancia(Direccion direccionInicio, Direccion direccionFin) {
    ClientResponse recurso = this.client.resource(API_DISTANCIA)
        .queryParam("localidadOrigenId", String.valueOf(direccionInicio.getLocalidadId()))
        .queryParam("calleOrigen", direccionInicio.getCalle())
        .queryParam("alturaOrigen", direccionInicio.getAltura())
        .queryParam("localidadDestinoId", String.valueOf(direccionFin.getLocalidadId()))
        .queryParam("calleDestino", direccionFin.getCalle())
        .queryParam("alturaDestino", direccionFin.getAltura())
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer vS4yrD83YZNnJGcvm8eRw2S8ZaN4dB220Zhb85Xfu3k=")
        .get(ClientResponse.class);

    String jsonResultApi = recurso.getEntity(String.class);
    JsonNode jsonNodeApi;
    try {
      jsonNodeApi = new ObjectMapper().readTree(jsonResultApi);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("No se pudo obtener la distancia desde la API");
    }
    //String unidad = jsonNodeApi.get("unidad").textValue();

    return jsonNodeApi.get("valor").asDouble();
  }

}
