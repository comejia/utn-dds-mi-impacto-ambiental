package Notificador;

public class Contacto {
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
