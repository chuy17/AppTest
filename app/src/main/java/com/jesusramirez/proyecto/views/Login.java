package com.jesusramirez.proyecto.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.jesusramirez.proyecto.BaseDeDatos.CRUD_Tablas;
import com.jesusramirez.proyecto.R;
import com.jesusramirez.proyecto.Utilidades.Utilidades;

public class Login extends AppCompatActivity {
    CRUD_Tablas datos;
    private String Cuenta;

    //Controles necesarios
    EditText correoUsuario,contraseñaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Creacion de la base de datos e instancia
        datos = CRUD_Tablas.obtenerInstancia(getApplicationContext());

        //Instanciar controles
        correoUsuario = (EditText)findViewById(R.id.etcorreo_login);
        contraseñaUsuario = (EditText)findViewById(R.id.etcontraseña_login);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public class tareaAsin extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                datos.getDb().beginTransaction();
                Cursor cursor = datos.getDb().query(Utilidades.TABLA_ALUMNOS,new String[]{Utilidades.CAMPO_CORREO_ALUMNO,Utilidades.CAMPO_CONTRASEÑA_ALUMNO},
                        Utilidades.CAMPO_CORREO_ALUMNO+" LIKE '"+correoUsuario.getText().toString()+"' AND "+Utilidades.CAMPO_CONTRASEÑA_ALUMNO+" LIKE '"+contraseñaUsuario.getText().toString()+"'",
                        null,null,null,null);
                Cursor cursor1 = datos.getDb().query(Utilidades.TABLA_MAESTROS,new String[]{Utilidades.CAMPO_CORREO_MAESTRO,Utilidades.CAMPO_CONTRASEÑA_MAESTRO},
                        Utilidades.CAMPO_CORREO_MAESTRO+" LIKE '"+correoUsuario.getText().toString()+"' AND "+Utilidades.CAMPO_CONTRASEÑA_MAESTRO+" LIKE '"+contraseñaUsuario.getText().toString()+"'",
                        null,null,null,null);
                if (cursor.getCount()>0){
                    Cuenta = "ALUMNO";
                    Intent intent = new Intent(getApplicationContext(),HomeAlumno.class);
                    startActivity(intent);
                }else if (cursor1.getCount()>0){
                    Cuenta = "MAESTRO";
                    Intent intent = new Intent(getApplicationContext(),HomeMaestro.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Contraseña y/o correo incorrectos",Toast.LENGTH_SHORT).show();
                    correoUsuario.setText("");
                    contraseñaUsuario.setText("");
                    correoUsuario.setFocusable(true);
                }
                datos.getDb().setTransactionSuccessful();
            } finally {
                datos.getDb().endTransaction();
            }
            return null;
        }
    }

    public void login(View view ){
        new tareaAsin().execute();
        crearToken();
    }

    public void goCreateAccount(View view ){
        Intent intent=new Intent(this,CreateAccount.class);
        startActivity(intent);
    }

    private void crearToken(){
        SharedPreferences tokenSesion = getSharedPreferences("TokenSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenSesion.edit();
        if (Cuenta.equals("MAESTRO")){
            editor.putString("SesionIniciada","trueMaestro");
        }else if (Cuenta.equals("ALUMNO")){
            editor.putString("SesionIniciada","trueAlumno");
        }
        editor.commit();
    }

}
