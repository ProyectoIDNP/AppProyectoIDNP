package org.dailyplastic.idnp.prueba.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
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

        transaction.replace(R.id.spaceGraphics, pieChartCategoriesFragment).commit();

        ColorStateList green = ColorStateList.valueOf(getResources().getColor(R.color.green_primary, this.getContext().getTheme()));
        Drawable buttonShape = ContextCompat.getDrawable(this.getContext(), R.drawable.button_drawable_standard);

        buttonCategories.setBackgroundTintList(green);
        buttonCategories.setBackground(buttonShape);
        buttonPresentations.setBackgroundResource(0);

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonCategories.setBackground(buttonShape);
                buttonCategories.setBackgroundTintList(green);
                buttonPresentations.setBackgroundResource(0);
                PieChartCategoriesFragment pieChartCategoriesFragment = new PieChartCategoriesFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.spaceGraphics, pieChartCategoriesFragment);
                transaction.commit();
            }
        });

        buttonPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buttonPresentations.setBackground(buttonShape);
                buttonPresentations.setBackgroundTintList(green);
                buttonCategories.setBackgroundResource(0);
                PieChartPresentationFragment pieChartPresentationFragment = new PieChartPresentationFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.spaceGraphics, pieChartPresentationFragment);
                transaction.commit();
            }
        });

        return statisticsFragment;
    }
}