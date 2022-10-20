package transportes;

import javax.persistence.Embeddable;

@Embeddable
public enum TipoCombustible {
  GNC,
  NAFTA,
  GASOIL,
  ELECTRICO
}
