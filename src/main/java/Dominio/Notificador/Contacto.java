package Dominio.Notificador;

import Dominio.usuarios.EntidadPersistente;

import javax.persistence.Entity;

@Entity
public class Contacto extends EntidadPersistente {
  private String mail;
  private String telefono;

  public Contacto(String mail, String telefono) {
    this.mail = mail;
    this.telefono = telefono;
  }

  public String getMail() {
    return mail;
  }

  public String getTelefono() {
    return telefono;
  }
}
