package dominio.organizaciones;

import dominio.miembros.Miembro;
import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;
import dominio.usuarios.Usuario;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
public class Vinculacion extends EntidadPersistente {

    @OneToOne
    @JoinColumn(name="id")
    private Organizacion organizacion;

    @OneToOne
    @JoinColumn(name="id")//Tirar join column a ver que pasa
    private Usuario empleado;

    public Vinculacion() {
    }

    public Vinculacion(Organizacion organizacion, Usuario empleado) {
        this.organizacion = organizacion;
        this.empleado = empleado;
    }

}
