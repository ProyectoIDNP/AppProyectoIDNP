package org.dailyplastic.idnp.prueba.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.dailyplastic.idnp.R;

public class PlasticsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View plasticsFragment =  inflater.inflate(R.layout.fragment_plastics, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        PlasticsCategoriesFragment plasticsCategoriesFragment = new PlasticsCategoriesFragment();

        Button buttonPlasticsCategories = plasticsFragment.findViewById(R.id.buttonPlasticsCategories);
        Button buttonPlaticsPresentations = plasticsFragment.findViewById(R.id.buttonPlasticsPresentation);

        transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsCategoriesFragment).commit();

        buttonPlasticsCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
        buttonPlaticsPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        buttonPlasticsCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPlasticsCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
                buttonPlaticsPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                PlasticsCategoriesFragment plasticsCategoriesFragment1 = new PlasticsCategoriesFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsCategoriesFragment1);
                transaction.commit();
            }
        });

        buttonPlaticsPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPlaticsPresentations.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
                buttonPlasticsCategories.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                PlasticsPresentationsFragment plasticsPresentationsFragment = new PlasticsPresentationsFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.cardsCategoriesAndPresentations, plasticsPresentationsFragment);
                transaction.commit();
            }
        });

        return plasticsFragment;
    }
}