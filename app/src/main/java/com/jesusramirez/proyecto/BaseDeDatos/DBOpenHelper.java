package com.jesusramirez.proyecto.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.jesusramirez.proyecto.Utilidades.Utilidades;

/**
 * Created by Jesus on 24/10/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            }else{
                db.execSQL("PRAGMA foreing_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_ALUMNOS);
        db.execSQL(Utilidades.CREAR_TABLA_GRUPOS);
        db.execSQL(Utilidades.CREAR_TABLA_MAESTROS);
        db.execSQL(Utilidades.CREAR_TABLA_MATERIAS);
        db.execSQL(Utilidades.CREAR_TABLA_TRABAJOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ALUMNOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MAESTROS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_GRUPOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MATERIAS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_TRABAJOS);

        onCreate(db);
    }
}
