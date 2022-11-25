package dominio.organizaciones;

import dominio.trayectos.Direccion;
import dominio.usuarios.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Vinculacion extends EntidadPersistente {

    @ManyToOne(cascade = CascadeType.ALL)
    private int organizacionID;

    @ManyToOne(cascade = CascadeType.ALL)
    private int miembroID;

    public Vinculacion(int organizacionID, int miembroID) {
        this.organizacionID = organizacionID;
        this.miembroID = miembroID;
    }
}
