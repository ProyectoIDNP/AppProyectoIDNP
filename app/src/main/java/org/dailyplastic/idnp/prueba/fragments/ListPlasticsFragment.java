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
        callCreate();



        //plasticList = new ArrayList<>();
        //plasticList.add(new Plastic(1, new Category(1, "Categoria 1", "", ""), new Presentation(1, "Presetntation 1", "", ""), "Plastico 1", 10, 3, "20/10/2022", "20/10/2022"));
        /*plasticList.add(new Plastic(2, "Categoria 2", "Presentacion 2", "Plastico 2", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(3, "Categoria 3", "Presentacion 3", "Plastico 3", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(4, "Categoria 4", "Presentacion 4", "Plastico 4", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(5, "Categoria 5", "Presentacion 5", "Plastico 5", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(6, "Categoria 6", "Presentacion 6", "Plastico 6", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(7, "Categoria 7", "Presentacion 7", "Plastico 7", 10, 3, "20/10/2022", "20/10/2022"));
        plasticList.add(new Plastic(8, "Categoria 8", "Presentacion 8", "Plastico 8", 10, 3, "20/10/2022", "20/10/2022"));*/

        searchViewPlastic = listPlasticsFragment.findViewById(R.id.searchViewPlastic);

        recyclerViewPlastics = listPlasticsFragment.findViewById(R.id.recyclerViewPlastics);
        recyclerViewPlastics.setLayoutManager(new LinearLayoutManager(getActivity()));

        //plasticRecyclerViewAdapter = new PlasticRecyclerViewAdapter(plasticList);
        //recyclerViewPlastics.setAdapter(plasticRecyclerViewAdapter);

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
                //Colocacion de los datos en el gridView
                //CategoryGridViewAdapter adapter = new CategoryGridViewAdapter(getActivity(), categories);
                //categoriesGridView.setAdapter(adapter);
                System.out.println("AQUIIIIIIIIIIIIIIIII");
                plasticRecyclerViewAdapter = new PlasticRecyclerViewAdapter(plasticList);
                recyclerViewPlastics.setAdapter(plasticRecyclerViewAdapter);



                plasticList.forEach(System.out::println);

                //categories.forEach(c -> Log.i("Categorias: ", c.toString()));
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