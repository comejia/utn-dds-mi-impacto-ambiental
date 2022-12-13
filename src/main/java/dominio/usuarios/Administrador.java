package dominio.usuarios;

import dominio.organizaciones.Organizacion;
import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("AD")
@Getter
public class Administrador extends Usuario {

  @Enumerated(EnumType.STRING)
  private Role role;

  private Administrador() {
  }

  public Administrador(String usuario, String contrasenia) {
    role = Role.ADMIN;
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
