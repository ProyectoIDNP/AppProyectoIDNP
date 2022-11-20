package org.dailyplastic.idnp.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.dailyplastic.idnp.R;

public class fragment_iniciarSesion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
        Button buttonLogin = fragview.findViewById(R.id.buttonIniciarSesion);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(intentMainActivity);
            }
        });

        return fragview;
    }
}
