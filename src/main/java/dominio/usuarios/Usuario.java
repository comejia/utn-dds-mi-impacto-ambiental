package dominio.usuarios;

import dominio.excepciones.ContraseniaDebilException;
import dominio.organizaciones.Organizacion;
import lombok.Getter;
import org.passay.*;
import org.passay.dictionary.Dictionary;
import org.passay.dictionary.DictionaryBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", length = 2)
@Getter
public abstract class Usuario extends EntidadPersistente {

  protected String usuario;
  protected String contrasenia;

  @OneToOne(cascade = CascadeType.ALL)
  private Organizacion organizacion;


  public String getUsuario() {
    return usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  abstract public Role getRole();

  public void validarContrasenia(String usuario, String contrasenia) {
    PasswordData passwordData = new PasswordData();
    passwordData.setUsername(usuario);
    passwordData.setPassword(contrasenia);

    PasswordValidator validador = getValidador();

    RuleResult validate = validador.validate(passwordData);
    if (!validate.isValid()) {
      throw new ContraseniaDebilException(getMensajeDeError(validate));
    }
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public PasswordValidator getValidador() {
    return new PasswordValidator(
        reglaConClavesBaneadas(),
        new LengthRule(8, 64),
        new UsernameRule()
    );
  }

  public String getMensajeDeError(RuleResult validate) {
    RuleResultDetail ruleResultDetail = validate.getDetails().get(0);
    return String.valueOf(getDiccionarioDeErrores().get(ruleResultDetail.getErrorCode()));
  }

  public HashMap<String, String> getDiccionarioDeErrores() {
    HashMap<String, String> diccionarioDeErrores = new HashMap<>();
    diccionarioDeErrores.put("ILLEGAL_WORD", "La contraseña ingresada es muy fácil");
    diccionarioDeErrores.put("TOO_SHORT", "La contraseña debe tener al menos 8 caracteres");
    diccionarioDeErrores.put("TOO_LONG", "La contraseña puede tener 64 caracteres como máximo");
    return diccionarioDeErrores;
  }

  public DictionaryRule reglaConClavesBaneadas() {
    ClassLoader classLoader = Administrador.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("documentación/10-million-password-list-top-10000.txt");
    DictionaryBuilder dictionaryBuilder = new DictionaryBuilder();
    assert inputStream != null;
    Dictionary diccionarioContraseniasFaciles = dictionaryBuilder.addReader(new InputStreamReader(inputStream)).build();
    return new DictionaryRule(diccionarioContraseniasFaciles);
  }

  public String hashearContrasenia(String contraseniaPlana) {
    return BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt());
  }

}
