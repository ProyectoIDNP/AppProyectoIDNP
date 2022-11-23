package org.dailyplastic.idnp.prueba.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.fragments.LoginFragment;
import org.dailyplastic.idnp.prueba.fragments.RegisterFragment;

public class MainMenuActivity extends AppCompatActivity {

    LoginFragment fragmentIniciarSesion = new LoginFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,fragmentIniciarSesion).commit();

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
        buttonRegister.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonLogin.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonRegister.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                LoginFragment fragment1 = new LoginFragment();
                transaction.replace(R.id.mainFrame, fragment1);
                transaction.commit();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonRegister.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonLogin.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                RegisterFragment fragment2 = new RegisterFragment();
                transaction.replace(R.id.mainFrame, fragment2);
                transaction.commit();
            }
        });
    }
}