package org.dailyplastic.idnp.prueba.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.CategoryService;
import org.dailyplastic.idnp.prueba.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesFragment extends Fragment {

    List<Category> categories;
    CategoryService categoryService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_categories, container, false);

        String[] plastic;
        plastic = getResources().getStringArray(R.array.plasticos);

        Button buttonCategories = view.findViewById(R.id.buttonCat);
        Button buttonPresentations = view.findViewById(R.id.buttonPres);

        buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
        buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        //Obtener los datos del api rest
        getAll();

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_secondary)));
                buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                CategoriesFragment categoriasFragment = new CategoriesFragment();
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
                PresentationsFragment fragmentPresentaciones = new PresentationsFragment();
                transaction.replace(R.id.presentacionesLayout,fragmentPresentaciones);
                transaction.commit();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, plastic);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        return view;
    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        categoryService = retrofit.create(CategoryService.class);
        Call<List<Category>> call = categoryService.getAll();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                categories = response.body();
                categories.forEach(c -> Log.i("Categorias: ", c.toString()));
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}