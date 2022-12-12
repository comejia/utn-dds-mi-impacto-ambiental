package main;

import dominio.Notificador.Contacto;
import dominio.Notificador.Notificador;
import dominio.Notificador.NotificarPorMail;
import dominio.Notificador.NotificarPorWhatsApp;

public class HuellaCarbonoApp {



    public static void main(String[] args) {

        Contacto contacto = new Contacto("saramambiche@frba.utn.edu.ar","00541115253245");
        Notificador notificarPorMail = new NotificarPorMail(contacto.getMail(), contacto.getTelefono());
        Notificador notificarPorTelefono= new NotificarPorWhatsApp();
        notificarPorMail.notificar(contacto,"Esto es una prueba","Soy el mail calendarizado\n");
        notificarPorTelefono.notificar(contacto,"Esto es una prueba","Soy el telefono calendarizado\n");
    }
}
