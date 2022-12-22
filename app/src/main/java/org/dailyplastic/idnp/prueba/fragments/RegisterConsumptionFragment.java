package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Consumption;
import org.dailyplastic.idnp.prueba.model.Origin;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.ArrayList;
import java.util.List;

public class RegisterConsumptionFragment extends Fragment {

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
        View registerConsumptionFragment = inflater.inflate(R.layout.fragment_register_consumption, container, false);

        buttonRegister = registerConsumptionFragment.findViewById(R.id.buttonRegisterComsumption);
        namePlastic = registerConsumptionFragment.findViewById(R.id.editPlasticName);
        units = registerConsumptionFragment.findViewById(R.id.editUnits);
        hour = registerConsumptionFragment.findViewById(R.id.editTime);
        date = registerConsumptionFragment.findViewById(R.id.editDate);
        description = registerConsumptionFragment.findViewById(R.id.editDescription);
        spinnerCategories = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerCategory);
        spinnerPresentation = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerFormPresentation);
        spinnerOrigin = (Spinner) registerConsumptionFragment.findViewById(R.id.spinnerOrigin);

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
                getParentFragmentManager().popBackStack();
            }
        });
        return registerConsumptionFragment;
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