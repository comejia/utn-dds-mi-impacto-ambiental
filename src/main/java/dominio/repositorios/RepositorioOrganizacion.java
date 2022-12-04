package dominio.repositorios;
import dominio.organizaciones.*;
import dominio.trayectos.Direccion;
import dominio.usuarios.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;


public class RepositorioOrganizacion implements WithGlobalEntityManager {

    public static RepositorioOrganizacion instance = new RepositorioOrganizacion();


    public void agregar(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }

    public List<Organizacion> listar() {
        return entityManager()
                .createQuery("from Organizacion", Organizacion.class)
                .getResultList();
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> ca4a5943c6045fe55f13b84fa214be06a7ebdd03
    public Organizacion buscarOrganizacion(String razonSocial) {
        System.out.print(razonSocial);
        return entityManager()
                .createQuery("from Organizacion O where O.razonSocial =:razonSocial", Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList().get(0);
<<<<<<< HEAD
=======
    public Organizacion buscarOrganizacion(int id) {
            return entityManager().find(Organizacion.class, id);
        }
    public Organizacion buscarOrganizacion(String razonSocial) {
        return entityManager().createQuery("from Organizaion O where O.razonSocial = :razonSocial", Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultList()
                .get(0);
>>>>>>> 19e44dc07da3b816bf61d814e170910c463743a9
    }
    }
=======
    }}

>>>>>>> ca4a5943c6045fe55f13b84fa214be06a7ebdd03

