package org.idnp.pruebas_lista_navegation.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import org.idnp.pruebas_lista_navegation.R;

public class CategoriasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view =inflater.inflate(R.layout.fragment_categorias, container, false);

        Spinner spinnerA;

        String[] plastic;
        spinnerA = (Spinner)view.findViewById(R.id.spinner);


        plastic = getResources().getStringArray(R.array.plasticos);



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,plastic);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerA.setAdapter(arrayAdapter);

        return view;
    }

}