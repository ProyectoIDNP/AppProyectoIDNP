package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.model.Plastic;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlasticDetailFragment extends Fragment {

    TextView plasticName;
    TextView plasticCategory;
    TextView plasticPresentation;
    TextView plasticTimeDecomposition;
    TextView plasticWeight;
    Button plasticDetailBack;

    PlasticService plasticService;

    ListPlasticsFragment listPlasticsFragment = new ListPlasticsFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View plasticDetailFragment = inflater.inflate(R.layout.fragment_plastic_detail, container, false);
        //Recuperando los datos
        Bundle dataRecovery = getArguments();
        if(dataRecovery == null) {

            return plasticDetailFragment;
        }

        plasticName = plasticDetailFragment.findViewById(R.id.textViewPlasticDetailName);
        plasticCategory = plasticDetailFragment.findViewById(R.id.textViewPlasticDetailCategory);
        plasticPresentation = plasticDetailFragment.findViewById(R.id.textViewPlasticDetailPresentation);
        plasticTimeDecomposition = plasticDetailFragment.findViewById(R.id.textViewPlasticDetailTimeDesc);
        plasticWeight = plasticDetailFragment.findViewById(R.id.textViewPlasticDetailWeight);
        plasticDetailBack = plasticDetailFragment.findViewById(R.id.buttonPlasticDetailBack);

        plasticDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, listPlasticsFragment).commit();
            }
        });

        Integer idPlastic = dataRecovery.getInt("idPlastic");

        getOne(idPlastic);
        return plasticDetailFragment;
    }

    public void getOne(Integer idPlastic) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        plasticService = retrofit.create(PlasticService.class);
        Call<Plastic> call = plasticService.getOne(idPlastic);
        call.enqueue(new Callback<Plastic>() {
            @Override
            public void onResponse(Call<Plastic> call, Response<Plastic> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                Plastic plastic = response.body();
                System.out.println(plastic);
                plasticName.setText(plastic.getName());
                plasticCategory.setText(plastic.getCategory().getName());
                plasticPresentation.setText(plastic.getPresentation().getName());
                plasticTimeDecomposition.setText(plastic.getDecompositionTime().toString());
                plasticWeight.setText(plastic.getUnitWeight().toString());

            }

            @Override
            public void onFailure(Call<Plastic> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}