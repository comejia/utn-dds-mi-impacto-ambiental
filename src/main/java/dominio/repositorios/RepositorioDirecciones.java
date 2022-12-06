package dominio.repositorios;

import dominio.Notificador.Contacto;
import dominio.trayectos.Direccion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioDirecciones implements WithGlobalEntityManager {
    public static RepositorioDirecciones instance = new RepositorioDirecciones();

    public List<Direccion> listar() {
        return entityManager()
                .createQuery("from Direccion ",Direccion.class)
                .getResultList();
    }

    public Direccion buscarDireccion(String id_direccion) {
        return entityManager().createQuery("from Vinculacion v where v.direccionID = :id_direccion", Direccion.class)
                .setParameter("id_direccion", id_direccion).getResultList().get(0); // buscar vinculacion segun empleado
    }

    public Contacto getById(int id_direccion) {
        return entityManager().find(Contacto.class, id_direccion);
    }

    public void agregar(Direccion direccion) {
        entityManager().persist(direccion);
    }

    public void quitar(Direccion direccion) {
        entityManager().remove(direccion);
    }

}
