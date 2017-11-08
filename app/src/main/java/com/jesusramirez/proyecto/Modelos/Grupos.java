package com.jesusramirez.proyecto.Modelos;


import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Jesus on 23/10/2017.
 */

public class Grupos implements Serializable{

    public String idMateria;
    public String idMaestro;
    public String nombreMateria;
    public String salon;
    public String hora;
    public int capacidad;
    public String diasClase;
    public int faltasPermitidas;

    public Grupos(String idMateria, String idMaestro, String nombreMateria, String salon, String hora, int capacidad, String diasClase, int faltasPermitidas) {
        this.idMateria = idMateria;
        this.idMaestro = idMaestro;
        this.nombreMateria = nombreMateria;
        this.salon = salon;
        this.hora = hora;
        this.capacidad = capacidad;
        this.diasClase = diasClase;
        this.faltasPermitidas = faltasPermitidas;
    }

    public static String generarIdGrupo(){
        return "GRU-"+ UUID.randomUUID().toString();
    }

}
