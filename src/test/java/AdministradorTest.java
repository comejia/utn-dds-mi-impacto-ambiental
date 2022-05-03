import Exepciones.ContraseniaDebilException;
import org.junit.jupiter.api.Test;
import usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdministradorTest {
  @Test
  public void usuarioConPasswordInvalida() {

    ContraseniaDebilException UsuarioInvalido = assertThrows(ContraseniaDebilException.class, () -> {
      new Administrador("LaPulga10", "a1");
    });

    assertEquals("La contraseña debe tener al menos 8 caracteres", UsuarioInvalido.getMessage());
  }
}
