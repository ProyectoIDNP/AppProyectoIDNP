package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.util.ArrayList;
import java.util.List;


public class ListPlasticsFragment extends Fragment implements SearchView.OnQueryTextListener{

    SearchView searchViewPlastic;
    List<Plastic> plasticList;
    RecyclerView recyclerViewPlastics;
    PlasticRecyclerViewAdapter plasticRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listPlasticsFragment = inflater.inflate(R.layout.fragment_list_plastics, container, false);

        plasticList = new ArrayList<>();
        plasticList.add(new Plastic(1, "Categoria 1", "Presentacion 1", "Plastico 1", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(2, "Categoria 2", "Presentacion 2", "Plastico 2", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(3, "Categoria 3", "Presentacion 3", "Plastico 3", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(4, "Categoria 4", "Presentacion 4", "Plastico 4", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(5, "Categoria 5", "Presentacion 5", "Plastico 5", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(6, "Categoria 6", "Presentacion 6", "Plastico 6", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(7, "Categoria 7", "Presentacion 7", "Plastico 7", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(8, "Categoria 8", "Presentacion 8", "Plastico 8", 10, 3, "20/10/2022", "20/10/2022"));

        searchViewPlastic = listPlasticsFragment.findViewById(R.id.searchViewPlastic);

        recyclerViewPlastics = listPlasticsFragment.findViewById(R.id.recyclerViewPlastics);
        recyclerViewPlastics.setLayoutManager(new LinearLayoutManager(getActivity()));

        plasticRecyclerViewAdapter = new PlasticRecyclerViewAdapter(plasticList);
        recyclerViewPlastics.setAdapter(plasticRecyclerViewAdapter);

        searchViewPlastic.setOnQueryTextListener(this);


        return listPlasticsFragment;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        plasticRecyclerViewAdapter.plasticFilter(s);
        return false;
    }
}