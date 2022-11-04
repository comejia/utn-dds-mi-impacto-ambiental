package dominio.usuarios;

import org.passay.*;
import org.passay.dictionary.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import dominio.excepciones.ContraseniaDebilException;

import javax.persistence.Entity;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Entity
public class Administrador extends EntidadPersistente{

  private String usuario;
  private String contrasenia;

  public Administrador(String usuario, String contrasenia) {
    if (usuario == null) {
      throw new RuntimeException("Debe ingresar un usuario");
    }
    validarContrasenia(usuario, contrasenia);
    this.usuario = usuario;
    this.contrasenia = hashearContrasenia(contrasenia);
  }

  public String getUsuario() {
    return usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  private void validarContrasenia(String usuario, String contrasenia) {
    PasswordData passwordData = new PasswordData();
    passwordData.setUsername(usuario);
    passwordData.setPassword(contrasenia);

    PasswordValidator validador = getValidador();

    RuleResult validate = validador.validate(passwordData);
    if (!validate.isValid()) {
      throw new ContraseniaDebilException(getMensajeDeError(validate));
    }
  }

  private PasswordValidator getValidador() {
    return new PasswordValidator(
        reglaConClavesBaneadas(),
        new LengthRule(8, 64),
        new UsernameRule()
    );
  }

  private String getMensajeDeError(RuleResult validate) {
    RuleResultDetail ruleResultDetail = validate.getDetails().get(0);
    return String.valueOf(getDiccionarioDeErrores().get(ruleResultDetail.getErrorCode()));
  }

  private HashMap<String, String> getDiccionarioDeErrores() {
    HashMap<String, String> diccionarioDeErrores = new HashMap<>();
    diccionarioDeErrores.put("ILLEGAL_WORD", "La contraseña ingresada es muy fácil");
    diccionarioDeErrores.put("TOO_SHORT", "La contraseña debe tener al menos 8 caracteres");
    diccionarioDeErrores.put("TOO_LONG", "La contraseña puede tener 64 caracteres como máximo");
    return diccionarioDeErrores;
  }

  private DictionaryRule reglaConClavesBaneadas() {
    ClassLoader classLoader = Administrador.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("documentación/10-million-password-list-top-10000.txt");
    DictionaryBuilder dictionaryBuilder = new DictionaryBuilder();
    assert inputStream != null;
    Dictionary diccionarioContraseniasFaciles = dictionaryBuilder.addReader(new InputStreamReader(inputStream)).build();
    return new DictionaryRule(diccionarioContraseniasFaciles);
  }

  private String hashearContrasenia(String contraseniaPlana) {
    return BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt());
  }

}
