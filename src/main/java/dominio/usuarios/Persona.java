package dominio.usuarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("PE")
public class Persona extends Usuario {

  @Enumerated(EnumType.STRING)
  private Role role;

  public Persona() {
  }

  public Persona(String usuario, String contrasenia) {
    role = Role.USUARIO;
    if (usuario == null) {
      throw new RuntimeException("Debe ingresar un usuario");
    }
    validarContrasenia(usuario, contrasenia);
    this.usuario = usuario;
    this.contrasenia = hashearContrasenia(contrasenia);
  }

  @Override
  public Role getRole() {
    return this.role;
  }
}
