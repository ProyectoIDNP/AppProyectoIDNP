package org.dailyplastic.idnp.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dailyplastic.idnp.R;

public class fragment_iniciarSesion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
        return fragview;
    }



}
