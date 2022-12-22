package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.ConsumptionRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.ConsumptionService;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Consumption;
import org.dailyplastic.idnp.prueba.model.Origin;
import org.dailyplastic.idnp.prueba.model.Plastic;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Ref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyPlasticConsumptionFragment extends Fragment implements SearchView.OnQueryTextListener{

    ConsumptionService consumptionService;
    SearchView searchViewConsumption;
    List<Consumption> consumptionList;
    RecyclerView recyclerViewConsumptions;
    ConsumptionRecyclerViewAdapter consumptionRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listConsumptionsFragment = inflater.inflate(R.layout.fragment_my_plastic_consumption, container, false);

        //Traer los plasticos
        getAll();

        Button buttonRegister = listConsumptionsFragment.findViewById(R.id.buttonRegisterComsumption);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                RegisterConsumptionFragment registerConsumptionFragment = new RegisterConsumptionFragment();
                transaction.replace(R.id.container, registerConsumptionFragment).addToBackStack(null).commit();
            }
        });

        consumptionList = new ArrayList<>();
        
        searchViewConsumption = listConsumptionsFragment.findViewById(R.id.searchViewConsumption);

        recyclerViewConsumptions = listConsumptionsFragment.findViewById(R.id.recyclerViewConsumptions);
        recyclerViewConsumptions.setLayoutManager(new LinearLayoutManager(getActivity()));

        consumptionRecyclerViewAdapter = new ConsumptionRecyclerViewAdapter(consumptionList, new ConsumptionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Consumption item) {
                onItemConsumptionClick(item);
            }
        });
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

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consumptionService = retrofit.create(ConsumptionService.class);
        Call<List<Consumption>> call = consumptionService.getAll();
        call.enqueue(new Callback<List<Consumption>>() {
            @Override
            public void onResponse(Call<List<Consumption>> call, Response<List<Consumption>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                consumptionList = response.body();
                consumptionRecyclerViewAdapter = new ConsumptionRecyclerViewAdapter(consumptionList, new ConsumptionRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Consumption item) {
                        onItemConsumptionClick(item);
                    }
                });
                recyclerViewConsumptions.setAdapter(consumptionRecyclerViewAdapter);

                consumptionList.forEach(System.out::println);
            }
            @Override
            public void onFailure(Call<List<Consumption>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    public void onItemConsumptionClick(Consumption item){
        Bundle bundle = new Bundle();
        bundle.putParcelable("Consumption", (Parcelable) item);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        EditConsumptionFragment editConsumptionFragment = new EditConsumptionFragment();
        editConsumptionFragment.setArguments(bundle);
        transaction.replace(R.id.container, editConsumptionFragment).addToBackStack(null).commit();
    }
}