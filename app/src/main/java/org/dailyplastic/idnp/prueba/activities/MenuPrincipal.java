package org.dailyplastic.idnp.prueba.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.fragments.fragment_iniciarSesion;
import org.dailyplastic.idnp.prueba.fragments.fragment_registrarse;

public class MenuPrincipal extends AppCompatActivity {

    fragment_iniciarSesion fragmentIniciarSesion = new fragment_iniciarSesion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame1,fragmentIniciarSesion).commit();

        Button button1 = findViewById(R.id.button5);
        Button button2 = findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                fragment_iniciarSesion fragment1 = new fragment_iniciarSesion();
                transaction.replace(R.id.frame1, fragment1);
                transaction.commit();

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                fragment_registrarse fragment2 = new fragment_registrarse();
                transaction.replace(R.id.frame1, fragment2);
                transaction.commit();
            }
        });
    }
}