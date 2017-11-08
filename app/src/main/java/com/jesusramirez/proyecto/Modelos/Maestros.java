package com.jesusramirez.proyecto.Modelos;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Jesus on 23/10/2017.
 */

public class Maestros implements Serializable{

    public String idMaestro;
    public String nombreMaestro;
    public String aPaternoMaestro;
    public String aMaternoMaestro;
    public String correoMaestro;
    public String contraseñaMaestro;
    public String checkConfirmacion;
    public File imgMaestro;

    public Maestros(String idMaestro, String nombreMaestro, String aPaternoMaestro,
                    String aMaternoMaestro, String correoMaestro, String contraseñaMaestro, String checkConfirmacion, File imgMaestro) {
        this.idMaestro = idMaestro;
        this.nombreMaestro = nombreMaestro;
        this.aPaternoMaestro = aPaternoMaestro;
        this.aMaternoMaestro = aMaternoMaestro;
        this.correoMaestro = correoMaestro;
        this.contraseñaMaestro = contraseñaMaestro;
        this.checkConfirmacion = checkConfirmacion;
        this.imgMaestro = imgMaestro;
    }

    public static String generarIdMaestro(){
        return "MAE-"+ UUID.randomUUID().toString();
    }
    public static String generarCheckConfirmacion(){
        return "CON-"+ UUID.randomUUID().toString();
    }

}
