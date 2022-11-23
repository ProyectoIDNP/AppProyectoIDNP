package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dailyplastic.idnp.R;


public class RegisterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment_register, container, false);
        return fragview;
    }
}