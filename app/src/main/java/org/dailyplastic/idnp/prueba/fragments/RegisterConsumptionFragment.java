package org.dailyplastic.idnp.prueba.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.admin.SystemUpdatePolicy;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.CategoryGridViewAdapter;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.adapters.PresentationGridViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.ConsumptionDto;
import org.dailyplastic.idnp.prueba.dto.UserDto;
import org.dailyplastic.idnp.prueba.interfaces.CategoryService;
import org.dailyplastic.idnp.prueba.interfaces.ConsumptionService;
import org.dailyplastic.idnp.prueba.interfaces.OriginService;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.interfaces.PresentationService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Origin;
import org.dailyplastic.idnp.prueba.model.Plastic;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterConsumptionFragment extends Fragment {
    List<Origin> listOrigins;
    List<Plastic> listPlastics;

    ConsumptionService consumptionService;
    OriginService originService;
    PlasticService plasticService;

    Button buttonRegister;

    EditText units;
    EditText hour;
    EditText date;
    EditText description;
    EditText presentation;
    EditText category;
    Spinner spinnerPlastic;
    Spinner spinnerOrigin;

    MyPlasticConsumptionFragment myPlasticConsumptionFragment = new MyPlasticConsumptionFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerConsumptionFragment = inflater.inflate(R.layout.fragment_register_consumption, container, false);

        buttonRegister = registerConsumptionFragment.findViewById(R.id.buttonRegisterComsumption);
        units = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumption);
        hour = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumptionHour);
        date = registerConsumptionFragment.findViewById(R.id.editTextConsumptionDate);
        description = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumptionDescription);

        presentation = registerConsumptionFragment.findViewById(R.id.editTextConsumptionPlasticPresentation);
        category = registerConsumptionFragment.findViewById(R.id.editTextConsumptionPlasticCategory);
        presentation.setKeyListener(null);
        category.setKeyListener(null);

        spinnerPlastic = registerConsumptionFragment.findViewById(R.id.spinnerConsumptionPlasticRegister);
        spinnerOrigin = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerConsumptionOriginRegister);

        spinnerPlastic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            Plastic valuePlasticTemp = (Plastic) spinnerPlastic.getSelectedItem();
            presentation.setText(valuePlasticTemp.getPresentation().getName());
            category.setText(valuePlasticTemp.getCategory().getName());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
        }

        });


        //Aqui reemplazar por las consultas
        getAllPlastics();
        getAllOrigins();


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plastic valuePlastic = (Plastic) spinnerPlastic.getSelectedItem();
                Origin valueOrigin = (Origin) spinnerOrigin.getSelectedItem();

                //Recuperacion del id del usuario logueado
                SharedPreferences mPrefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("userLoggedIn", "");
                UserDto userObj = gson.fromJson(json, UserDto.class);


                ConsumptionDto consumptionDto = new ConsumptionDto();

                //Cambiar el id de plastico por la busqueda
                consumptionDto.setUser(userObj.getUser().getId());
                consumptionDto.setPlastic(valuePlastic.getId());
                consumptionDto.setOrigin(valueOrigin.getId());
                consumptionDto.setImage(null);
                consumptionDto.setDescription(description.getText().toString());
                consumptionDto.setUnit(Integer.valueOf(units.getText().toString()));
                create(consumptionDto);

            }
        });
        return registerConsumptionFragment;
    }

    public void create(ConsumptionDto consumptionDto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        consumptionService = retrofit.create(ConsumptionService.class);
        Call<ConsumptionDto> call = consumptionService.create(consumptionDto);
        call.enqueue(new Callback<ConsumptionDto>() {
            @Override
            public void onResponse(Call<ConsumptionDto> call, Response<ConsumptionDto> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                ConsumptionDto consumptionDtoReceived = response.body();
                Toast.makeText(getActivity(), "Se guardaron los datos de con éxito", Toast.LENGTH_LONG).show();
                getParentFragmentManager().popBackStack();
                //Nos regresamos a la lista de consumos
            }

            @Override
            public void onFailure(Call<ConsumptionDto> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }


    public void returnToConsumptionListFragment() {
        getParentFragmentManager().beginTransaction().replace(R.id.container, myPlasticConsumptionFragment).commit();
    }

    private void getAllOrigins() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        originService = retrofit.create(OriginService.class);
        Call<List<Origin>> call = originService.getAll();
        call.enqueue(new Callback<List<Origin>>() {
            @Override
            public void onResponse(Call<List<Origin>> call, Response<List<Origin>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listOrigins = response.body();
                ArrayAdapter<Origin> originsAdapter = new ArrayAdapter<Origin>(getActivity().getApplicationContext(), R.layout.style_spinner, listOrigins);
                spinnerOrigin.setAdapter(originsAdapter);
            }

            @Override
            public void onFailure(Call<List<Origin>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void getAllPlastics() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        plasticService = retrofit.create(PlasticService.class);
        Call<List<Plastic>> call = plasticService.getAll();
        call.enqueue(new Callback<List<Plastic>>() {
            @Override
            public void onResponse(Call<List<Plastic>> call, Response<List<Plastic>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listPlastics = response.body()
                        .stream()
                        .sorted(Comparator.comparing(Plastic::getName))
                        .collect(Collectors.toList());
                //Se ordena la lista

                ArrayAdapter<Plastic> originsAdapter = new ArrayAdapter<Plastic>(getActivity().getApplicationContext(), R.layout.style_spinner, listPlastics);
                spinnerPlastic.setAdapter(originsAdapter);
            }

            @Override
            public void onFailure(Call<List<Plastic>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}