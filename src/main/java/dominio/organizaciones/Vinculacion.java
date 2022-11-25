package dominio.organizaciones;

import dominio.miembros.Miembro;
import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Vinculacion extends EntidadPersistente {

    @ManyToOne(cascade = CascadeType.ALL)
    private Organizacion organizacion;

    @ManyToOne(cascade = CascadeType.ALL)
    private Miembro miembro;

    public Vinculacion(Organizacion organizacion, Miembro miembro) {
        this.organizacion = organizacion;
        this.miembro = miembro;
    }
}
