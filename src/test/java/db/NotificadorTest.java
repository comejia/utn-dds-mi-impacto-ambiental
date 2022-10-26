package db;

import Dominio.Notificador.Contacto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import Dominio.organizaciones.*;

public class NotificadorTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    Contacto contacto;

    @BeforeEach
    public void init(){
        contacto = new Contacto("mail@gmail.com","45032699");
    }
    @Test
    public void sePuedePersistirUnContacto() {
        entityManager().persist(contacto);
    }
}
