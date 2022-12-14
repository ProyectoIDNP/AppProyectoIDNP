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
import org.dailyplastic.idnp.prueba.adapters.ConsumptionRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.model.Consumption;

import java.util.ArrayList;
import java.util.List;


public class MyPlasticConsumptionFragment extends Fragment implements SearchView.OnQueryTextListener{

    SearchView searchViewConsumption;
    List<Consumption> consumptionList;
    RecyclerView recyclerViewConsumptions;
    ConsumptionRecyclerViewAdapter consumptionRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listConsumptionsFragment = inflater.inflate(R.layout.fragment_my_plastic_consumption, container, false);

        consumptionList = new ArrayList<>();

        consumptionList.add(new Consumption(1, "Plastic 1", "User 1", "Origin 1", "UrlImage1", "Description 1", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(2, "Plastic 2", "User 2", "Origin 2", "UrlImage2", "Description 2", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(3, "Plastic 3", "User 3", "Origin 3", "UrlImage3", "Description 3", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(4, "Plastic 4", "User 4", "Origin 4", "UrlImage4", "Description 4", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(5, "Plastic 5", "User 5", "Origin 5", "UrlImage5", "Description 5", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(6, "Plastic 6", "User 6", "Origin 6", "UrlImage6", "Description 6", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(7, "Plastic 7", "User 7", "Origin 7", "UrlImage7", "Description 7", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(8, "Plastic 8", "User 8", "Origin 8", "UrlImage8", "Description 8", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(9, "Plastic 9", "User 9", "Origin 9", "UrlImage9", "Description 9", 10, "05/12/2022", "05/12/2022"));
        consumptionList.add(new Consumption(10, "Plastic 10", "User 10", "Origin 10", "UrlImage10", "Description 10", 10, "05/12/2022", "05/12/2022"));

        searchViewConsumption = listConsumptionsFragment.findViewById(R.id.searchViewConsumption);

        recyclerViewConsumptions = listConsumptionsFragment.findViewById(R.id.recyclerViewConsumptions);
        recyclerViewConsumptions.setLayoutManager(new LinearLayoutManager(getActivity()));

        consumptionRecyclerViewAdapter = new ConsumptionRecyclerViewAdapter(consumptionList);
        recyclerViewConsumptions.setAdapter(consumptionRecyclerViewAdapter);

        searchViewConsumption.setOnQueryTextListener(this);

        return listConsumptionsFragment;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        consumptionRecyclerViewAdapter.plasticFilter(s);
        return false;
    }
}