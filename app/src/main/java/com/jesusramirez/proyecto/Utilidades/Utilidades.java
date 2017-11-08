package com.jesusramirez.proyecto.Utilidades;

/**
 * Created by Jesus on 24/10/2017.
 */

public class Utilidades {

    //Constantes campos tabla alumnos
    public static final String TABLA_ALUMNOS="alumnos";
    public static final String CAMPO_ID_ALUMNO="id_alumno";
    public static final String CAMPO_NOMBRES_ALUMNO="nombres_alumno";
    public static final String CAMPO_APATERNO_ALUMNO="a_paterno_alumno";
    public static final String CAMPO_AMATERNO_ALUMNO="a_materno_alumno";
    public static final String CAMPO_CORREO_ALUMNO="correo_alumno";
    public static final String CAMPO_CONTRASEÑA_ALUMNO="contraseña_alumno";
    public static final String CAMPO_IMG_ALUMNO="img_alumno";

    public static final String CREAR_TABLA_ALUMNOS="CREATE TABLE "+TABLA_ALUMNOS+"("+
            CAMPO_ID_ALUMNO+" TEXT NOT NULL PRIMARY KEY,"+
            CAMPO_NOMBRES_ALUMNO+" TEXT NOT NULL,"+
            CAMPO_APATERNO_ALUMNO+" TEXT NOT NULL,"+
            CAMPO_AMATERNO_ALUMNO+" TEXT NOT NULL,"+
            CAMPO_CORREO_ALUMNO+" TEXT NOT NULL,"+
            CAMPO_CONTRASEÑA_ALUMNO+" TEXT NOT NULL,"+
            CAMPO_IMG_ALUMNO+" BLOB NULL)";

    //Constantes campos tabla maestros
    public static final String TABLA_MAESTROS="maestros";
    public static final String CAMPO_ID_MAESTRO="id_maestro";
    public static final String CAMPO_NOMBRES_MAESTRO="nombres_maestro";
    public static final String CAMPO_APATERNO_MAESTRO="a_paterno_maestro";
    public static final String CAMPO_AMATERNO_MAESTRO="a_materno_maestro";
    public static final String CAMPO_CORREO_MAESTRO="correo_maestro";
    public static final String CAMPO_CONTRASEÑA_MAESTRO="contraseña_maestro";
    public static final String CAMPO_CONFIRMACION_MAESTRO = "confirmacion_maestro";
    public static final String CAMPO_IMG_MAESTRO="img_maestro";

    public static final String CREAR_TABLA_MAESTROS="CREATE TABLE "+TABLA_MAESTROS+"("+
            CAMPO_ID_MAESTRO+" TEXT NOT NULL PRIMARY KEY,"+
            CAMPO_NOMBRES_MAESTRO+" TEXT NOT NULL,"+
            CAMPO_APATERNO_MAESTRO+" TEXT NOT NULL,"+
            CAMPO_AMATERNO_MAESTRO+" TEXT NOT NULL,"+
            CAMPO_CORREO_MAESTRO+" TEXT NOT NULL,"+
            CAMPO_CONTRASEÑA_MAESTRO+" TEXT NOT NULL,"+
            CAMPO_CONFIRMACION_MAESTRO+" NULL,"+
            CAMPO_IMG_MAESTRO+" BLOB NULL)";

    //Constantes campos tabla grupos
    public static final String TABLA_GRUPOS="grupos";
    public static final String CAMPO_ID_GRUPO="id_grupo";
    public static final String CAMPO_FK_ID_ALUMNO_GRUPO="fk_id_alumno";
    public static final String CAMPO_FK_ID_MAESTRO_GRUPO="fk_id_maestro";
    public static final String CAMPO_NOMBRE_MATERIA="nombre_materia";
    public static final String CAMPO_SALON="salon";
    public static final String CAMPO_HORA="hora";
    public static final String CAMPO_CAPACIDAD="capacidad";
    public static final String CAMPO_DIAS_CLASES="dias_clases";
    public static final String CAMPO_FALTAS_PERMITIDAS="faltas_permitidas";

    public static final String CREAR_TABLA_GRUPOS="CREATE TABLE "+TABLA_GRUPOS+"("+
            CAMPO_ID_GRUPO+" TEXT NOT NULL PRIMARY KEY,"+
            CAMPO_FK_ID_ALUMNO_GRUPO+" REFERENCES "+TABLA_ALUMNOS+"("+CAMPO_ID_ALUMNO+"),"+
            CAMPO_FK_ID_MAESTRO_GRUPO+" REFERENCES "+TABLA_MAESTROS+"("+CAMPO_ID_MAESTRO+"),"+
            CAMPO_NOMBRE_MATERIA+" TEXT NOT NULL,"+
            CAMPO_SALON+" TEXT NOT NULL,"+
            CAMPO_HORA+" TEXT NOT NULL,"+
            CAMPO_CAPACIDAD+" INTEGER NOT NULL,"+
            CAMPO_DIAS_CLASES+" TEXT NOT NULL,"+
            CAMPO_FALTAS_PERMITIDAS+" INTEGER NOT NULL)";

    //Constantes campos tabla materias
    public static final String TABLA_MATERIAS="materias";
    public static final String CAMPO_ID_MATERIA="id_materia";
    public static final String CAMPO_FK_ID_ALUMNOS_MATERIAS="fk_id_alumnos";
    public static final String CAMPO_CALIFICACION="calificacion";
    public static final String CAMPO_FALTAS="faltas";
    public static final String CAMPO_PARCIAL="parcial";

    public static final String CREAR_TABLA_MATERIAS="CREATE TABLE "+TABLA_MATERIAS+"("+
            CAMPO_ID_MATERIA+" TEXT NOT NULL PRIMARY KEY,"+
            CAMPO_FK_ID_ALUMNOS_MATERIAS+" REFERENCES "+TABLA_ALUMNOS+"("+CAMPO_ID_ALUMNO+"),"+
            CAMPO_CALIFICACION+" INTEGER NOT NULL,"+
            CAMPO_FALTAS+" INTEGER NOT NULL,"+
            CAMPO_PARCIAL+" INTEGER NOT NULL)";

    //Constantes campos tabla trabajos
    public static final String TABLA_TRABAJOS="trabajos";
    public static final String CAMPO_ID_TRABAJOS="id_trabajo";
    public static final String CAMPO_FK_ID_ALUMNO_TRABAJOS="fk_id_alumnos";
    public static final String CAMPO_FK_ID_MAESTRO_TRABAJOS="fk_id_maestros";
    public static final String CAMPO_NOMBRE_TRABAJO="nombre_trabajo";
    public static final String CAMPO_FECHA_ENTREGA="fecha_entrega";
    public static final String CAMPO_TIPO_TRABAJO="tipo_trabajo";
    public static final String CAMPO_ARCHIVO="archivo";

    public static final String CREAR_TABLA_TRABAJOS="CREATE TABLE "+TABLA_TRABAJOS+"("+
            CAMPO_ID_TRABAJOS+" TEXT  NOT NULL PRIMARY KEY,"+
            CAMPO_FK_ID_ALUMNO_TRABAJOS+" REFERENCES "+TABLA_ALUMNOS+"("+CAMPO_ID_ALUMNO+"),"+
            CAMPO_FK_ID_MAESTRO_TRABAJOS+" REFERENCES "+TABLA_MAESTROS+"("+CAMPO_ID_MAESTRO+"),"+
            CAMPO_NOMBRE_TRABAJO+" TEXT NOT NULL,"+
            CAMPO_FECHA_ENTREGA+" TEXT NOT NULL,"+
            CAMPO_TIPO_TRABAJO+" TEXT NOT NULL,"+
            CAMPO_ARCHIVO+" FILE NULL)";


}
