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


public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View statisticsFragment =  inflater.inflate(R.layout.fragment_statistics, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        PieChartCategoriesFragment pieChartCategoriesFragment = new PieChartCategoriesFragment();

        Button buttonCategories = statisticsFragment.findViewById(R.id.buttonCategories);
        Button buttonPresentations = statisticsFragment.findViewById(R.id.buttonPresentation);

        transaction.replace(R.id.pieChart, pieChartCategoriesFragment).commit();

        buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
        buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonCategories.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
                buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                PieChartCategoriesFragment pieChartCategoriesFragment = new PieChartCategoriesFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.pieChart, pieChartCategoriesFragment);
                transaction.commit();
            }
        });

        buttonPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPresentations.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_primary)));
                buttonCategories.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                PieChartPresentationFragment pieChartPresentationFragment = new PieChartPresentationFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.pieChart, pieChartPresentationFragment);
                transaction.commit();
            }
        });

        return statisticsFragment;
    }
}