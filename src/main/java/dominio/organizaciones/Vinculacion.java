package dominio.organizaciones;

import dominio.miembros.Miembro;
import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;
import dominio.usuarios.Usuario;
import lombok.Getter;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
public class Vinculacion extends EntidadPersistente{

    @OneToOne
    private Organizacion organizacion;

    @OneToOne
    private Usuario empleado;

    boolean pendiente = true;

    public Vinculacion() {
    }

    public Vinculacion(Organizacion organizacion, Usuario empleado) {
        this.organizacion = organizacion;
        this.empleado = empleado;

    }

    public boolean estaAprobada() {
        return !pendiente;
    }

    public void setEstado() {
        pendiente = false;
    }

}
