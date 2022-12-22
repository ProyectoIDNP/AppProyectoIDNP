package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.ConsumptionRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.ConsumptionService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Consumption;
import org.dailyplastic.idnp.prueba.model.Origin;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditConsumptionFragment extends Fragment {

    List<Category> listCategories = new ArrayList<>();
    List<Presentation> listPresentations = new ArrayList<>();
    List<Origin> listOrigins = new ArrayList<>();
    Consumption consumption;
    Button buttonRegister;
    EditText namePlastic;
    EditText units;
    EditText hour;
    EditText date;
    EditText description;
    Spinner spinnerCategories;
    Spinner spinnerPresentation;
    Spinner spinnerOrigin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            consumption = bundle.getParcelable("Consumption");
            Log.i("Comsumption", consumption.toString());
        }

        View editConsumptionFragment = inflater.inflate(R.layout.fragment_edit_consumption, container, false);

        buttonRegister = editConsumptionFragment.findViewById(R.id.buttonRegisterComsumption);
        namePlastic = editConsumptionFragment.findViewById(R.id.editPlasticName);
        units = editConsumptionFragment.findViewById(R.id.editUnits);
        hour = editConsumptionFragment.findViewById(R.id.editTime);
        date = editConsumptionFragment.findViewById(R.id.editDate);
        description = editConsumptionFragment.findViewById(R.id.editDescription);
        spinnerCategories = (Spinner) editConsumptionFragment.findViewById(R.id.spinnerCategory);
        spinnerPresentation = (Spinner) editConsumptionFragment.findViewById(R.id.spinnerFormPresentation);
        spinnerOrigin = (Spinner) editConsumptionFragment.findViewById(R.id.spinnerOrigin);

        setValues();
        setValuesSpinners();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueNamePlastic = namePlastic.getText().toString();
                String valueUnits = units.getText().toString();
                String valueHour = units.getText().toString();
                String valueDate = date.getText().toString();
                String valueDescription = description.getText().toString();
                Presentation valuePresentation = (Presentation) spinnerPresentation.getSelectedItem();
                Category valueCategory = (Category) spinnerCategories.getSelectedItem();
                Origin valueOrigin = (Origin) spinnerOrigin.getSelectedItem();
                Log.i("DATOS", valueCategory + valueDate + valueDescription + valueCategory + valueOrigin);
                getParentFragmentManager().popBackStack();
            }
        });
        return editConsumptionFragment;
    }

    public void setValues() {
        String strDatewithTime = consumption.getUpdated();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDatewithTime);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        namePlastic.setText(consumption.getPlastic().getName());
        units.setText(String.valueOf(consumption.getUnits()));
        hour.setText(zonedDateTime.format(timeFormat));
        date.setText(zonedDateTime.format(dateFormat));
        description.setText(consumption.getDescription());
    }

    public void setValuesSpinners() {
        // Se crean los objetos de prueba
        Category category = new Category(1, "Categoria", "21/12/2022", "21/12/2022");
        Category category2 = new Category(1, "Categoria2", "21/12/2022", "21/12/2022");
        Origin origin = new Origin(1, "Origen");
        Presentation presentation = new Presentation(1, "Presentacion", "21/12/2022", "21/12/2022");

        listCategories.add(category);
        listCategories.add(category2);
        listOrigins.add(origin);
        listPresentations.add(presentation);
        List<Category> categories = new ArrayList<>(listCategories);
        List<Origin> origins = new ArrayList<>(listOrigins);
        List<Presentation> presentations = new ArrayList<>(listPresentations);
        ArrayAdapter<Category> categoriesAdapter = new ArrayAdapter<Category>(getActivity().getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(categoriesAdapter);
        ArrayAdapter<Presentation> presentationsAdapter = new ArrayAdapter<Presentation>(getActivity().getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, presentations);
        presentationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPresentation.setAdapter(presentationsAdapter);
        ArrayAdapter<Origin> originsAdapter = new ArrayAdapter<Origin>(getActivity().getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, origins);
        presentationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(originsAdapter);
    }
}