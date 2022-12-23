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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterConsumptionFragment extends Fragment {

    List<Category> listCategories;
    List<Presentation> listPresentations;
    List<Origin> listOrigins;

    ConsumptionService consumptionService;
    CategoryService categoryService;
    OriginService originService;
    PresentationService presentationService;

    Button buttonRegister;
    EditText namePlastic;
    EditText units;
    EditText hour;
    EditText date;
    EditText description;
    Spinner spinnerCategories;
    Spinner spinnerPresentation;
    Spinner spinnerOrigin;

    MyPlasticConsumptionFragment myPlasticConsumptionFragment = new MyPlasticConsumptionFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerConsumptionFragment = inflater.inflate(R.layout.fragment_register_consumption, container, false);

        buttonRegister = registerConsumptionFragment.findViewById(R.id.buttonRegisterComsumption);
        namePlastic = registerConsumptionFragment.findViewById(R.id.editTextConsumptionPlasticName);
        units = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumption);
        hour = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumptionHour);
        date = registerConsumptionFragment.findViewById(R.id.editTextConsumptionDate);
        description = registerConsumptionFragment.findViewById(R.id.editTextUnitsConsumptionDescription);
        spinnerCategories = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerConsumptionCategoryRegister);
        spinnerPresentation = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerConsumptionPresentationRegister);
        spinnerOrigin = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerConsumptionOriginRegister);
        // Se crean los objetos de prueba
        /*Category category = new Category(1, "Categoria", "21/12/2022", "21/12/2022");
        Category category2 = new Category(1, "Categoria2", "21/12/2022", "21/12/2022");
        Origin origin = new Origin(1, "Origen");
        Presentation presentation = new Presentation(1, "Presentacion", "21/12/2022", "21/12/2022");

        listCategories.add(category);
        listCategories.add(category2);
        listOrigins.add(origin);
        listPresentations.add(presentation);*/

        //Aqui reemplazar por las consultas
        getAllOrigins();
        getAllCategories();
        getAllPresentations();


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presentation valuePresentation = (Presentation) spinnerPresentation.getSelectedItem();
                Category valueCategory = (Category) spinnerCategories.getSelectedItem();
                Origin valueOrigin = (Origin) spinnerOrigin.getSelectedItem();

                //Recuperacion del id del usuario logueado
                SharedPreferences mPrefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("userLoggedIn", "");
                UserDto userObj = gson.fromJson(json, UserDto.class);


                ConsumptionDto consumptionDto = new ConsumptionDto();

                //Cambiar el id de plastico por la busqueda
                consumptionDto.setPlastic(2);
                consumptionDto.setUser(userObj.getUser().getId());
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

    private void getAllCategories() {
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
                listCategories = response.body();

                ArrayAdapter<Category> categoriesAdapter = new ArrayAdapter<Category>(getActivity().getApplicationContext(), R.layout.style_spinner, listCategories);
                spinnerCategories.setAdapter(categoriesAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }

    private void getAllPresentations() {
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
                listPresentations = response.body();

                ArrayAdapter<Presentation> presentationsAdapter = new ArrayAdapter<Presentation>(getActivity().getApplicationContext(), R.layout.style_spinner, listPresentations);
                spinnerPresentation.setAdapter(presentationsAdapter);
            }

            @Override
            public void onFailure(Call<List<Presentation>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}