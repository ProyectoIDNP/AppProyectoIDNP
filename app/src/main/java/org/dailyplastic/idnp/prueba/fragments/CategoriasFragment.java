package org.dailyplastic.idnp.prueba.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.dailyplastic.idnp.R;

public class CategoriasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_categorias, container, false);

        String[] plastic;
        plastic = getResources().getStringArray(R.array.plasticos);

        Button buttonCategories = view.findViewById(R.id.buttonCat);
        Button buttonPresentations = view.findViewById(R.id.buttonPres);

        buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
        buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                CategoriasFragment categoriasFragment = new CategoriasFragment();
                transaction.replace(R.id.categoriasLayout,categoriasFragment);
                transaction.commit();
            }
        });

        buttonPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonCategories.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                fragment_presentaciones fragmentPresentaciones = new fragment_presentaciones();
                transaction.replace(R.id.presentacionesLayout,fragmentPresentaciones);
                transaction.commit();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, plastic);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        return view;
    }
}