package funciones;

import java.util.Map;

public class UsuarioNotificacion {

  public static void notificar(Map<String, Object> viewModel, String tipo, String titulo, String texto) {
    viewModel.put("mensaje", true);
    viewModel.put("tipoMensaje", tipo);
    viewModel.put("tituloMensaje", titulo);
    viewModel.put("textoMensaje", texto);
  }
}
