package com.jesusramirez.proyecto.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jesusramirez.proyecto.Modelos.Alumnos;
import com.jesusramirez.proyecto.Modelos.Grupos;
import com.jesusramirez.proyecto.Modelos.Maestros;
import com.jesusramirez.proyecto.Modelos.Materias;
import com.jesusramirez.proyecto.Utilidades.Utilidades;


/**
 * Created by Jesus on 25/10/2017.
 */

public final class CRUD_Tablas {
    private static DBOpenHelper baseDatos;
    private static CRUD_Tablas instancia=new CRUD_Tablas();
    private CRUD_Tablas(){ }

    public static CRUD_Tablas obtenerInstancia(Context contexto){
        if (baseDatos==null){
            baseDatos=new DBOpenHelper(contexto,"App.db",null,1);
        }
        return instancia;
    }

    //[OPERACIONES ALUMNOS]*********************************************************************************************************
    public Cursor obtenerAlumnos(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        String sql=String.format("SELECT * FROM %s", Utilidades.TABLA_ALUMNOS);
        return db.rawQuery(sql,null);
    }

    public void insertarAlumno(Alumnos alumno){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        //Generar PK idAlumno
        String idAlumno = Alumnos.generarIdAlumno();
        valores.put(Utilidades.CAMPO_ID_ALUMNO,idAlumno);
        valores.put(Utilidades.CAMPO_NOMBRES_ALUMNO,alumno.nombreAlumno);
        valores.put(Utilidades.CAMPO_APATERNO_ALUMNO,alumno.aPaternoAlumno);
        valores.put(Utilidades.CAMPO_AMATERNO_ALUMNO,alumno.aMaternoAlumno);
        valores.put(Utilidades.CAMPO_CORREO_ALUMNO,alumno.correoAlumno);
        valores.put(Utilidades.CAMPO_CONTRASEÑA_ALUMNO,alumno.contraseñaAlumno);

        db.insertOrThrow(Utilidades.TABLA_ALUMNOS,null,valores);
    }

    public Cursor obtenerAlumnoPorId(String idAlumno){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                Utilidades.TABLA_ALUMNOS,Utilidades.CAMPO_ID_ALUMNO);
        String[] seleccionArg = {idAlumno};

        return db.rawQuery(sql,seleccionArg);
    }

    public boolean actualizarAlumno(Alumnos actAlumno){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Utilidades.CAMPO_NOMBRES_ALUMNO,actAlumno.nombreAlumno);
        valores.put(Utilidades.CAMPO_APATERNO_ALUMNO,actAlumno.aPaternoAlumno);
        valores.put(Utilidades.CAMPO_AMATERNO_ALUMNO,actAlumno.aMaternoAlumno);
        valores.put(Utilidades.CAMPO_CORREO_ALUMNO,actAlumno.correoAlumno);
        valores.put(Utilidades.CAMPO_CONTRASEÑA_ALUMNO,actAlumno.contraseñaAlumno);

        String whereClausula = String.format("%s=?",Utilidades.CAMPO_ID_ALUMNO);
        String[] whereArg = {actAlumno.idAlumno};
        int resultado = db.update(Utilidades.TABLA_ALUMNOS,valores,whereClausula,whereArg);

        return resultado > 0;
    }

    public boolean eliminarAlumno(Alumnos eliAlumno){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClausula = Utilidades.CAMPO_ID_ALUMNO+"=?";
        String[] whereArg = {eliAlumno.idAlumno};
        int resultado = db.delete(Utilidades.TABLA_ALUMNOS,whereClausula,whereArg);

        return resultado > 0;
    }

    //[OPERACIONES ALUMNOS]******************************************************************************************************

    //[OPERACIONES GRUPOS]*****************************************************************************************************
    public Cursor obtenerGrupos(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        String sql=String.format("SELECT * FROM %s", Utilidades.TABLA_GRUPOS);
        return db.rawQuery(sql,null);
    }

    public void insertarGrupo(Grupos nuevoGrupo){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        //Generar PK idMateria
        //String idMateria = Grupos.generarIdMaestro();
        //valores.put(Utilidades.CAMPO_ID_MAESTRO,idMaestro); Agregar metodo la clase correspondiente
        valores.put(Utilidades.CAMPO_NOMBRE_MATERIA,nuevoGrupo.nombreMateria);
        valores.put(Utilidades.CAMPO_SALON,nuevoGrupo.salon);
        valores.put(Utilidades.CAMPO_HORA,nuevoGrupo.hora);
        valores.put(Utilidades.CAMPO_CAPACIDAD,nuevoGrupo.capacidad);
        valores.put(Utilidades.CAMPO_DIAS_CLASES,nuevoGrupo.diasClase);
        valores.put(Utilidades.CAMPO_FALTAS_PERMITIDAS,nuevoGrupo.faltasPermitidas);

        db.insertOrThrow(Utilidades.TABLA_GRUPOS,null,valores);
    }

    public Cursor obtenerGrupoPorId(String idMateria){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                Utilidades.TABLA_GRUPOS,Utilidades.CAMPO_ID_MATERIA);
        String[] seleccionArg = {idMateria};

        return db.rawQuery(sql,seleccionArg);
    }

    public boolean actualizarGrupo(Grupos actGrupo){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Utilidades.CAMPO_NOMBRE_MATERIA,actGrupo.nombreMateria);
        valores.put(Utilidades.CAMPO_SALON,actGrupo.salon);
        valores.put(Utilidades.CAMPO_HORA,actGrupo.hora);
        valores.put(Utilidades.CAMPO_CAPACIDAD,actGrupo.capacidad);
        valores.put(Utilidades.CAMPO_DIAS_CLASES,actGrupo.diasClase);
        valores.put(Utilidades.CAMPO_FALTAS_PERMITIDAS,actGrupo.faltasPermitidas);

        String whereClausula = String.format("%s=?",Utilidades.CAMPO_ID_MATERIA);
        String[] whereArg = {actGrupo.idMateria};
        int resultado = db.update(Utilidades.TABLA_GRUPOS,valores,whereClausula,whereArg);

        return resultado > 0;
    }

    public boolean eliminarGrupo(Grupos eliGrupo){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClausula = Utilidades.CAMPO_ID_MATERIA+"=?";
        String[] whereArg = {eliGrupo.idMateria};
        int resultado = db.delete(Utilidades.TABLA_GRUPOS,whereClausula,whereArg);

        return resultado > 0;
    }

    //[OPERACIONES GRUPOS]*****************************************************************************************************

    //[OPERACIONES MAESTROS]*******************************************************************************************************

    public Cursor obtenerMaestros(){
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        String sql=String.format("SELECT * FROM %s", Utilidades.TABLA_MAESTROS);
        return db.rawQuery(sql,null);
    }

    public void insertarMaestros(Maestros maestro){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        //Generar PK idMaestro
        String idMaestro = Maestros.generarIdMaestro();
        valores.put(Utilidades.CAMPO_ID_MAESTRO,idMaestro);
        valores.put(Utilidades.CAMPO_NOMBRES_MAESTRO,maestro.nombreMaestro);
        valores.put(Utilidades.CAMPO_APATERNO_MAESTRO,maestro.aPaternoMaestro);
        valores.put(Utilidades.CAMPO_AMATERNO_MAESTRO,maestro.aMaternoMaestro);
        valores.put(Utilidades.CAMPO_CORREO_MAESTRO,maestro.correoMaestro);
        valores.put(Utilidades.CAMPO_CONTRASEÑA_MAESTRO,maestro.contraseñaMaestro);
        valores.put(Utilidades.CAMPO_CONFIRMACION_MAESTRO,maestro.checkConfirmacion);

        db.insertOrThrow(Utilidades.TABLA_MAESTROS,null,valores);
    }

    public Cursor obtenerMaestrosPorId(String idMaestro){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s=?",
                Utilidades.TABLA_MAESTROS,Utilidades.CAMPO_ID_MAESTRO);
        String[] seleccionArg = {idMaestro};

        return db.rawQuery(sql,seleccionArg);
    }

    public boolean actualizarMaestros(Maestros actMaestro){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Utilidades.CAMPO_NOMBRES_MAESTRO,actMaestro.nombreMaestro);
        valores.put(Utilidades.CAMPO_APATERNO_MAESTRO,actMaestro.aPaternoMaestro);
        valores.put(Utilidades.CAMPO_AMATERNO_MAESTRO,actMaestro.aMaternoMaestro);
        valores.put(Utilidades.CAMPO_CORREO_MAESTRO,actMaestro.correoMaestro);
        valores.put(Utilidades.CAMPO_CONTRASEÑA_MAESTRO,actMaestro.contraseñaMaestro);
        valores.put(Utilidades.CAMPO_CONFIRMACION_MAESTRO,actMaestro.checkConfirmacion);

        String whereClausula = String.format("%s=?",Utilidades.CAMPO_ID_MAESTRO);
        String[] whereArg = {actMaestro.idMaestro};
        int resultado = db.update(Utilidades.TABLA_MAESTROS,valores,whereClausula,whereArg);

        return resultado > 0;
    }

    public boolean eliminarMaestros(Maestros eliMaestro){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClausula = Utilidades.CAMPO_ID_MAESTRO+"=?";
        String[] whereArg = {eliMaestro.idMaestro};
        int resultado = db.delete(Utilidades.TABLA_MAESTROS,whereClausula,whereArg);

        return resultado > 0;
    }

    //[OPERACIONES MAESTROS]*******************************************************************************************************

    //[OPERACIONES MATERIAS]*******************************************************************************************************

    //[OPERACIONES MATERIAS]*******************************************************************************************************

    //[OPERACIONES TRABAJOS]*******************************************************************************************************
    //[OPERACIONES TRABAJOS]*******************************************************************************************************

    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

}
