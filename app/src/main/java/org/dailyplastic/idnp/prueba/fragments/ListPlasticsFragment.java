package org.dailyplastic.idnp.prueba.fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.CategoryService;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Plastic;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListPlasticsFragment extends Fragment implements SearchView.OnQueryTextListener{

    PlasticService plasticsService;
    SearchView searchViewPlastic;
    List<Plastic> plasticList;
    RecyclerView recyclerViewPlastics;
    PlasticRecyclerViewAdapter plasticRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View listPlasticsFragment = inflater.inflate(R.layout.fragment_list_plastics, container, false);

        //Traer los plasticos
        getAll();

        //Test
        //callCreate();

        searchViewPlastic = listPlasticsFragment.findViewById(R.id.searchViewPlastic);
        recyclerViewPlastics = listPlasticsFragment.findViewById(R.id.recyclerViewPlastics);
        recyclerViewPlastics.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        plasticsService = retrofit.create(PlasticService.class);
        Call<List<Plastic>> call = plasticsService.getAll();
        call.enqueue(new Callback<List<Plastic>>() {
            @Override
            public void onResponse(Call<List<Plastic>> call, Response<List<Plastic>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                plasticList = response.body();
                plasticRecyclerViewAdapter = new PlasticRecyclerViewAdapter(plasticList, getParentFragmentManager());
                recyclerViewPlastics.setAdapter(plasticRecyclerViewAdapter);

                plasticList.forEach(System.out::println);

            }

            @Override
            public void onFailure(Call<List<Plastic>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }


    public void callCreate() {
        System.out.println("***********************");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        plasticsService = retrofit.create(PlasticService.class);

        plasticsService.create(2, 1, "TESt222222", 5000, 40).enqueue(new Callback<Plastic>() {
            @Override
            public void onResponse(Call<Plastic> call, Response<Plastic> response) {
                if(response.isSuccessful()) {
                    System.out.println("EXITO");
                }
            }
            @Override
            public void onFailure(Call<Plastic> call, Throwable t) {

                System.out.println("*************" + t.getMessage());
            }
        });
    }
}