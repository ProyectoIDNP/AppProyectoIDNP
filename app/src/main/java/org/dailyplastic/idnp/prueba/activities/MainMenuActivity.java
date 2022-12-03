package org.dailyplastic.idnp.prueba.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

        ColorStateList green = ColorStateList.valueOf(getResources().getColor(R.color.green_secondary, this.getTheme()));
        Drawable buttonShape = ContextCompat.getDrawable(this, R.drawable.button_drawable_standard);

        buttonLogin.setBackgroundTintList(green);
        buttonLogin.setBackground(buttonShape);
        buttonRegister.setBackgroundResource(0);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonLogin.setBackground(buttonShape);
                buttonLogin.setBackgroundTintList(green);
                buttonRegister.setBackgroundResource(0);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                LoginFragment fragment1 = new LoginFragment();
                transaction.replace(R.id.mainFrame, fragment1);
                transaction.commit();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonRegister.setBackground(buttonShape);
                buttonRegister.setBackgroundTintList(green);
                buttonLogin.setBackgroundResource(0);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                RegisterFragment fragment2 = new RegisterFragment();
                transaction.replace(R.id.mainFrame, fragment2);
                transaction.commit();
            }
        });
    }
}