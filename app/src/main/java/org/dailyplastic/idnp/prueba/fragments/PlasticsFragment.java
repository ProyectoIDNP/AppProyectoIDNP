package org.dailyplastic.idnp.prueba.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.CategoryGridViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.interfaces.CategoryService;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlasticsFragment extends Fragment {
    ListPlasticsFragment listPlasticsFragment = new ListPlasticsFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View plasticsFragment =  inflater.inflate(R.layout.fragment_plastics, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        PlasticsCategoriesFragment plasticsCategoriesFragment = new PlasticsCategoriesFragment();

        Button buttonPlasticsCategories = plasticsFragment.findViewById(R.id.buttonPlasticsCategories);
        Button buttonPlaticsPresentations = plasticsFragment.findViewById(R.id.buttonPlasticsPresentation);
        //Flotaing button
        FloatingActionButton floatingButtonListPlastics = plasticsFragment.findViewById(R.id.floatingActionButtonListPlastics);

        transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsCategoriesFragment).commit();

        ColorStateList green = ColorStateList.valueOf(getResources().getColor(R.color.green_primary, this.getContext().getTheme()));
        Drawable buttonShape = ContextCompat.getDrawable(this.getContext(), R.drawable.button_drawable_standard);

        buttonPlasticsCategories.setBackgroundTintList(green);
        buttonPlasticsCategories.setBackground(buttonShape);
        buttonPlaticsPresentations.setBackgroundResource(0);

        buttonPlasticsCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPlasticsCategories.setBackground(buttonShape);
                buttonPlasticsCategories.setBackgroundTintList(green);
                buttonPlaticsPresentations.setBackgroundResource(0);
                PlasticsCategoriesFragment plasticsCategoriesFragment1 = new PlasticsCategoriesFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsCategoriesFragment1);
                transaction.commit();
            }
        });

        buttonPlaticsPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPlaticsPresentations.setBackground(buttonShape);
                buttonPlaticsPresentations.setBackgroundTintList(green);
                buttonPlasticsCategories.setBackgroundResource(0);
                PlasticsPresentationsFragment plasticsPresentationsFragment = new PlasticsPresentationsFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsPresentationsFragment);
                transaction.commit();
            }
        });

        floatingButtonListPlastics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, listPlasticsFragment).commit();
            }
        });



        return plasticsFragment;
    }
}