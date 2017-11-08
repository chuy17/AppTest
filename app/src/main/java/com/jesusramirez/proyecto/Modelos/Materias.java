package com.jesusramirez.proyecto.Modelos;

import java.io.Serializable;

/**
 * Created by Jesus on 23/10/2017.
 */

public class Materias implements Serializable{

    public String idAlumnos;
    public float calificacionFinal;
    public int faltas;
    public int noParcial;

    public Materias(String idAlumnos, float calificacionFinal, int faltas, int noParcial) {
        this.idAlumnos = idAlumnos;
        this.calificacionFinal = calificacionFinal;
        this.faltas = faltas;
        this.noParcial = noParcial;
    }

}
