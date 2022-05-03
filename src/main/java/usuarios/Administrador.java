package usuarios;

import Exepciones.ContraseniaDebilException;
import org.passay.*;
import org.passay.dictionary.Dictionary;
import org.passay.dictionary.DictionaryBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Administrador {

  private String usuario;
  private String contrasenia;

  public Administrador(String usuario, String contrasenia) throws ClassNotFoundException {
    if (usuario == null) {
      throw new RuntimeException("Debe ingresar un usuario");
    }
    validarContrasenia(usuario,contrasenia);
    this.usuario = usuario;
    this.contrasenia = hashearContrasenia(contrasenia); //guardo la contraseña de forma hasheada
  }

  private void validarContrasenia(String usuario, String contrasenia) throws ClassNotFoundException {
    PasswordData passwordData = new PasswordData();
    passwordData.setUsername(usuario);
    passwordData.setPassword(contrasenia);

    PasswordValidator validador = getValidador();

    //valido la contraseña contra las validaciones definidas
    RuleResult validate = validador.validate(passwordData);
    if (!validate.isValid()) {
      throw new ContraseniaDebilException(definirMensajeDelError(validate));
    }
  }

  //En este metodo armo un validador personalizado con las validaciones que quiero.
  private PasswordValidator getValidador() throws ClassNotFoundException {
    return new PasswordValidator(
        reglaConClavesBaneadas(),
        new LengthRule(8,64),
        new UsernameRule()
    );
  }

  //Un get del Error de la validacion
  private String definirMensajeDelError(RuleResult validate) {
    RuleResultDetail ruleResultDetail = validate.getDetails().get(0);
    return String.valueOf(getDiccionarioDeErrores().get(ruleResultDetail.getErrorCode()));
  }

  //Metodo para traduccir el codigo de un error para el usuario
  private HashMap<String, String> getDiccionarioDeErrores() {
    HashMap<String, String> DiccionarioDeErrores = new HashMap<>();
    DiccionarioDeErrores.put("ILLEGAL_WORD", "La contraseña ingresada es muy fácil");
    DiccionarioDeErrores.put("TOO_SHORT", "La contraseña debe tener al menos 8 caracteres");
    DiccionarioDeErrores.put("TOO_LONG", "La contraseña puede tener 64 caracteres como máximo");
    return DiccionarioDeErrores;
  }

  //Genero un diccionario de reglas en bases al archivo de las 10000 contraseñas faciles
  private DictionaryRule reglaConClavesBaneadas() throws ClassNotFoundException {
    Class cls = Class.forName("usuarios.Administrador");
    ClassLoader cLoader = cls.getClassLoader();
    InputStream inputStream = cLoader.getResourceAsStream("10-million-password-list-top-10000.txt");
    DictionaryBuilder dictionaryBuilder = new DictionaryBuilder();
    assert inputStream != null;
    Dictionary diccionarioContraseniasFaciles = dictionaryBuilder.addReader(new InputStreamReader(inputStream)).build();
    return new DictionaryRule(diccionarioContraseniasFaciles);
  }

  private String hashearContrasenia(String contraseniaPlana) {
    return BCrypt.hashpw(contraseniaPlana, BCrypt.gensalt());
  }


}
