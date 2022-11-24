import org.junit.jupiter.api.Test;
import dominio.excepciones.ContraseniaDebilException;
import dominio.usuarios.Administrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdministradorTest {

  @Test
  public void administradorConContraseniaFacil() {
    ContraseniaDebilException usuarioInvalido = assertThrows(ContraseniaDebilException.class,
        () -> new Administrador("LaPulga10", "123456"));
    assertEquals("La contraseña ingresada es muy fácil", usuarioInvalido.getMessage());
  }

  @Test
  public void administradorConContraseniaMuyCorta() {
    ContraseniaDebilException usuarioInvalido = assertThrows(ContraseniaDebilException.class,
        () -> new Administrador("LaPulga10", "a1"));
    assertEquals("La contraseña debe tener al menos 8 caracteres", usuarioInvalido.getMessage());
  }

  @Test
  public void administradorConContraseniaMuyLarga() {
    ContraseniaDebilException usuarioInvalido = assertThrows(ContraseniaDebilException.class, () ->
        new Administrador("LaPulga10", "Esta clave es muy larga por el hecho de que " +
            "tengo que llegar a mas de 64 caracteres, no se a que loco se le ocurriria poner " +
            "una contraseña tan larga, la verdad que le llegas a errar a una letra y te la " +
            "regalo"));
    assertEquals("La contraseña puede tener 64 caracteres como máximo", usuarioInvalido.getMessage());
  }

  @Test
  public void administradorConNombreNullEsInvalido() {
    RuntimeException usuarioInvalido = assertThrows(RuntimeException.class,
        () -> new Administrador(null, "prueba"));
    assertEquals("Debe ingresar un usuario", usuarioInvalido.getMessage());
  }

  @Test
  public void administradorConContraseniaNullEsInvalido() {
    RuntimeException usuarioInvalido = assertThrows(RuntimeException.class,
        () -> new Administrador("nombre", null));
    assertEquals("Password cannot be null", usuarioInvalido.getMessage());
  }

  @Test
  public void administradorConContraseniaVaciaArrojaExcepcion() {
    RuntimeException usuarioInvalido = assertThrows(RuntimeException.class,
        () -> new Administrador("nombre", ""));
    assertEquals("La contraseña debe tener al menos 8 caracteres", usuarioInvalido.getMessage());
  }

}

