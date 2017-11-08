package com.jesusramirez.proyecto.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jesusramirez.proyecto.R;

import java.util.Map;
import java.util.Set;

public class PantallaInicial extends AppCompatActivity {

    private final int DURACION_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarToken();
    }

    private void cargarToken(){
        SharedPreferences tokenSesion = getSharedPreferences("TokenSesion", Context.MODE_PRIVATE);
        String sesion = tokenSesion.getString("SesionIniciada","false");
        if (sesion.equals("false")){
            cargarSplash();
        }else if (sesion.equals("trueMaestro")){
            Intent intent = new Intent(this,HomeMaestro.class);
            startActivity(intent);
        }else if (sesion.equals("trueAlumno")){
            Intent intent = new Intent(this, HomeAlumno.class);
            startActivity(intent);
        }
    }

    private void cargarSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(getApplicationContext(),Login.class);
                startActivity(intent1);
                finish();
            }
        },DURACION_SPLASH);
    }

}
