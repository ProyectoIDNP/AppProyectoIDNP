package org.dailyplastic.idnp.prueba.fragments;

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

public class PresentationsFragment extends Fragment {

    Button mostrarCategorias;
    Button mostrarPresentaciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view =inflater.inflate(R.layout.fragment_presentations, container, false);

        String[] plastic;
        plastic = getResources().getStringArray(R.array.plasticos);

        mostrarCategorias = view.findViewById(R.id.buttonCat);
        mostrarPresentaciones = view.findViewById(R.id.buttonPres);

        mostrarCategorias.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                CategoriesFragment categoriasFragment = new CategoriesFragment();

                transaction.replace(R.id.categoriasLayout,categoriasFragment);
                transaction.commit();
            }
        });

        mostrarPresentaciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                PresentationsFragment fragmentPresentaciones = new PresentationsFragment();

                transaction.replace(R.id.presentacionesLayout,fragmentPresentaciones);
                transaction.commit();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,plastic);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        return view;
    }

}