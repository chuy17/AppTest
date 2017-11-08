package com.jesusramirez.proyecto.views;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jesusramirez.proyecto.BaseDeDatos.CRUD_Tablas;
import com.jesusramirez.proyecto.BaseDeDatos.DBOpenHelper;
import com.jesusramirez.proyecto.Modelos.Alumnos;
import com.jesusramirez.proyecto.Modelos.Maestros;
import com.jesusramirez.proyecto.R;
import com.jesusramirez.proyecto.Utilidades.Utilidades;

import java.sql.Array;

public class CreateAccount extends AppCompatActivity {
    CRUD_Tablas datos;

    //Controladores necesario
    private EditText etNombresUsuarios,etAPaternoUsuario,etAMaternoUsuario,etCorreoUsuario,etContraseñaUsuario,etConfirmarContraseñaUsuario;
    private Spinner tipocuenta;
    private String Cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Creacion de la base de datos einstancia
        datos = CRUD_Tablas.obtenerInstancia(getApplicationContext());

        //Enlazar controles
        etNombresUsuarios = (EditText)findViewById(R.id.etnombres_createaccount);
        etAPaternoUsuario = (EditText)findViewById(R.id.etapellidoP_createaccount);
        etAMaternoUsuario = (EditText)findViewById(R.id.etapellidoM_createaccount);
        etCorreoUsuario = (EditText)findViewById(R.id.etcorreo_createaccount);
        etContraseñaUsuario = (EditText)findViewById(R.id.etcontraseña_createaccount);
        etConfirmarContraseñaUsuario = (EditText)findViewById(R.id.etconfirmarcontraseña_createaccount);
        tipocuenta = (Spinner)findViewById(R.id.spinner_createaccount);

        //Toolbar con el nombre de la actividad
        Toolbar barra=(Toolbar)findViewById(R.id.toolbar);
        barra.setTitle("Crear cuenta");
        setSupportActionBar(barra);

        //Cargar spinner para el tipo de cuenta
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.spinner_createaccount,android.R.layout.simple_spinner_item);
        tipocuenta.setAdapter(adapter);
        tipocuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cuenta = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Cuenta = "";
            }
        });

    }

    public void nuevoGrupo(){
        new tareaAsincrona().execute();
    }

    public class tareaAsincrona extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                datos.getDb().beginTransaction();
                if (Cuenta.equals("MAESTRO")){
                datos.insertarMaestros(new Maestros(null,
                        etNombresUsuarios.getText().toString(),etAPaternoUsuario.getText().toString(),
                        etAMaternoUsuario.getText().toString(),etCorreoUsuario.getText().toString(),
                        etContraseñaUsuario.getText().toString(),null,null));
                    /*Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeMaestro.class);
                    startActivity(intent);*/
                }else if (Cuenta.equals("ALUMNO")){
                datos.insertarAlumno(new Alumnos(null,
                        etNombresUsuarios.getText().toString(),etAPaternoUsuario.getText().toString(),
                        etAMaternoUsuario.getText().toString(),etCorreoUsuario.getText().toString(),
                        etContraseñaUsuario.getText().toString(),null));
                    /*Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeAlumno.class);
                    startActivity(intent);*/
                }
                datos.getDb().setTransactionSuccessful();
            }finally {
                datos.getDb().endTransaction();
            }
            return null;
        }
    }

    public void registrarse(View view){
        if (etContraseñaUsuario.getText().toString().equals(etConfirmarContraseñaUsuario.getText().toString())){
            crearToken();
            new tareaAsincrona().execute();
        }else{
            etContraseñaUsuario.setText("");
            etConfirmarContraseñaUsuario.setText("");
            etContraseñaUsuario.setFocusable(true);
            Toast.makeText(this,"Contraseñas distintas",Toast.LENGTH_SHORT).show();
        }
    }

    private void crearToken(){
        SharedPreferences tokenSesion = getSharedPreferences("TokenSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenSesion.edit();
        if (Cuenta.equals("MAESTRO")){
            editor.putString("SesionIniciada","trueMaestro");
            Intent intent = new Intent(getApplicationContext(),HomeMaestro.class);
            startActivity(intent);
        }else if (Cuenta.equals("ALUMNO")){
            editor.putString("SesionIniciada","trueAlumno");
            Intent intent = new Intent(getApplicationContext(),HomeAlumno.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Ocurrio algo",Toast.LENGTH_SHORT).show();
        }
        editor.commit();
    }
}
