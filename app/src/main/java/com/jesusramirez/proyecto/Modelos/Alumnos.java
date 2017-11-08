package com.jesusramirez.proyecto.Modelos;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Jesus on 23/10/2017.
 */

public class Alumnos implements Serializable{

    public String idAlumno;
    public String nombreAlumno;
    public String aPaternoAlumno;
    public String aMaternoAlumno;
    public String correoAlumno;
    public String contraseñaAlumno;
    public File imgAlumno;

    public Alumnos(String idAlumno, String nombreAlumno, String aPaternoAlumno, String aMaternoAlumno, String correoAlumno, String contraseñaAlumno, File imgAlumno) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.aPaternoAlumno = aPaternoAlumno;
        this.aMaternoAlumno = aMaternoAlumno;
        this.correoAlumno = correoAlumno;
        this.contraseñaAlumno = contraseñaAlumno;
        this.imgAlumno = imgAlumno;
    }

    public static String generarIdAlumno(){
        return "ALU-"+ UUID.randomUUID().toString();
    }

}
