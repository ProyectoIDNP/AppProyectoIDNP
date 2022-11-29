package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.CategoryGridViewAdapter;
import org.dailyplastic.idnp.prueba.adapters.PresentationGridViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.CategoryService;
import org.dailyplastic.idnp.prueba.interfaces.PresentationService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlasticsPresentationsFragment extends Fragment {

    GridView presentationsGridView;
    List<Presentation> presentations;
    PresentationService presentationService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View plasticPresentationFragment = inflater.inflate(R.layout.fragment_plastics_presentations, container, false);

        //Obtener los datos del api rest
        getAll();
        presentationsGridView = plasticPresentationFragment.findViewById(R.id.gridViewPresentations);

        return plasticPresentationFragment;
    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        presentationService = retrofit.create(PresentationService.class);
        Call<List<Presentation>> call = presentationService.getAll();
        call.enqueue(new Callback<List<Presentation>>() {
            @Override
            public void onResponse(Call<List<Presentation>> call, Response<List<Presentation>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                presentations = response.body();
                //Colocacion de los datos en el gridView
                PresentationGridViewAdapter adapter = new PresentationGridViewAdapter(getActivity(), presentations);
                presentationsGridView.setAdapter(adapter);

                presentations.forEach(c -> Log.i("Categorias: ", c.toString()));
            }

            @Override
            public void onFailure(Call<List<Presentation>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}