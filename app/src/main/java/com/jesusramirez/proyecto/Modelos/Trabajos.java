package com.jesusramirez.proyecto.Modelos;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jesus on 23/10/2017.
 */

public class Trabajos implements Serializable{

    public String idAlumnos;
    public String idMateria;
    public String nombreTrabajo;
    public String fechaEntrega;
    public String tipoTrabajo;
    public File archivo;

    public Trabajos(String idAlumnos, String idMateria, String nombreTrabajo, String fechaEntrega, String tipoTrabajo, File archivo) {
        this.idAlumnos = idAlumnos;
        this.idMateria = idMateria;
        this.nombreTrabajo = nombreTrabajo;
        this.fechaEntrega = fechaEntrega;
        this.tipoTrabajo = tipoTrabajo;
        this.archivo = archivo;
    }

}
