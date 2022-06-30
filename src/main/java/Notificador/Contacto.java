package Notificador;

public class Contacto {
  private String mail;
  private int telefono;

  public Contacto(String mail, int telefono) {
    this.mail = mail;
    this.telefono = telefono;
  }

  public String getMail() {
    return mail;
  }

  public int getTelefono() {
    return telefono;
  }
}
