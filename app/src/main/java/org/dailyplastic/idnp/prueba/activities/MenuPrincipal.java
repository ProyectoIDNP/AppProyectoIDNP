package org.dailyplastic.idnp.prueba.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
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

        Button buttonLogin = findViewById(R.id.login);
        Button buttonRegister = findViewById(R.id.register);

        buttonLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
        buttonRegister.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonRegister.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                fragment_iniciarSesion fragment1 = new fragment_iniciarSesion();
                transaction.replace(R.id.frame1, fragment1);
                transaction.commit();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonRegister.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonLogin.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                fragment_registrarse fragment2 = new fragment_registrarse();
                transaction.replace(R.id.frame1, fragment2);
                transaction.commit();
            }
        });
    }
}