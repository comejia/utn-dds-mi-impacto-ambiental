package dominio.organizaciones;

import dominio.miembros.Miembro;
import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;
import dominio.usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Vinculacion extends EntidadPersistente {

    @ManyToOne(cascade = CascadeType.ALL)
    private Organizacion organizacion;

    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario empleado;

    public Vinculacion() {
    }

    public Vinculacion(Organizacion organizacion, Usuario empleado) {
        this.organizacion = organizacion;
        this.empleado = empleado;
    }

}
