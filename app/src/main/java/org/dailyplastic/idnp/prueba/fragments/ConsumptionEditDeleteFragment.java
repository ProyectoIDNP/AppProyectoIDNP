package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.ConsumptionService;
import org.dailyplastic.idnp.prueba.model.Consumption;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsumptionEditDeleteFragment extends Fragment{

    TextView consumptionName;
    TextView consumptionCategory;
    TextView consumptionPresentation;
    TextView consumptionOrigin;
    TextView consumptionDescription;
    TextView consumptionUnits;
    TextView consumptionTotalWeight;
    Button consumptionEdit, consumptionDelete;
    ImageView consumptionImage;

    ConsumptionService consumptionService;

    MyPlasticConsumptionFragment myPlasticConsumptionFragment = new MyPlasticConsumptionFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View consumptionDetailFragment = inflater.inflate(R.layout.fragment_consumption_edit_delete, container, false);
        //Recuperando los datos
        Bundle dataRecovery = getArguments();
        if(dataRecovery == null) {

            return consumptionDetailFragment;
        }

        consumptionName = consumptionDetailFragment.findViewById(R.id.textViewConsumptionDetailName);
        consumptionCategory = consumptionDetailFragment.findViewById(R.id.textViewConsumptionCategory);
        consumptionPresentation = consumptionDetailFragment.findViewById(R.id.textViewConsumptionPresentation);
        consumptionOrigin = consumptionDetailFragment.findViewById(R.id.textViewConsumptionOrigin);
        consumptionDescription = consumptionDetailFragment.findViewById(R.id.textViewConsumptionDescription);
        consumptionUnits = consumptionDetailFragment.findViewById(R.id.textViewConsumptionUnits);
        consumptionTotalWeight = consumptionDetailFragment.findViewById(R.id.textViewConsumptionWeight);
        consumptionImage = consumptionDetailFragment.findViewById(R.id.imageViewConsumptionImage);

        consumptionEdit = consumptionDetailFragment.findViewById(R.id.ButtonEdit);
        consumptionDelete = consumptionDetailFragment.findViewById(R.id.ButtonDelete);


        consumptionImage.setImageResource(R.drawable.ic_image_not_available);

        consumptionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, myPlasticConsumptionFragment).commit();
            }
        });

        consumptionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, myPlasticConsumptionFragment).commit();
            }
        });

        Integer idConsumption = dataRecovery.getInt("idConsumption");

        getOne(idConsumption);
        return consumptionDetailFragment;
    }

    public void getOne(Integer idConsumption) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        consumptionService = retrofit.create(ConsumptionService.class);
        Call<Consumption> call = consumptionService.getOne(idConsumption);
        call.enqueue(new Callback<Consumption>() {
            @Override
            public void onResponse(Call<Consumption> call, Response<Consumption> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                Consumption consumption = response.body();
                System.out.println(consumption);
                consumptionName.setText(consumption.getPlastic().getName().toString());
                consumptionCategory.setText(consumption.getPlastic().getCategory().getName().toString());
                consumptionPresentation.setText(consumption.getPlastic().getPresentation().getName().toString());
                consumptionOrigin.setText(consumption.getOrigin().getName().toString());
                consumptionDescription.setText(consumption.getDescription());
                consumptionUnits.setText(consumption.getUnits().toString());
                consumptionTotalWeight.setText(consumption.getTotalUnitsWeight().toString() + " gr.");

            }

            @Override
            public void onFailure(Call<Consumption> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}