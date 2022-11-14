package org.idnp.pruebas_lista_navegation.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.idnp.pruebas_lista_navegation.R;

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