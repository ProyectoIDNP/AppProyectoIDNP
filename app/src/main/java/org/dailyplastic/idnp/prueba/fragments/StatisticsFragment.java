package org.dailyplastic.idnp.prueba.fragments;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.dailyplastic.idnp.R;


public class StatisticsFragment extends Fragment {


    RadioGroup radioGroupStatisticsBy;
    Boolean units = false;
    Boolean weight = true;
    Boolean categories = true;
    Boolean presentation = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View statisticsFragment =  inflater.inflate(R.layout.fragment_statistics, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        PieChartCategoriesWeightFragment pieChartCategoriesWeightFragment = new PieChartCategoriesWeightFragment();

        Button buttonCategories = statisticsFragment.findViewById(R.id.buttonCategories);
        Button buttonPresentations = statisticsFragment.findViewById(R.id.buttonPresentation);
        radioGroupStatisticsBy = statisticsFragment.findViewById(R.id.radioGroupStatistics);

        transaction.replace(R.id.spaceGraphics, pieChartCategoriesWeightFragment).commit();

        ColorStateList green = ColorStateList.valueOf(getResources().getColor(R.color.green_primary, this.getContext().getTheme()));
        Drawable buttonShape = ContextCompat.getDrawable(this.getContext(), R.drawable.button_drawable_standard);

        buttonCategories.setBackgroundTintList(green);
        buttonCategories.setBackground(buttonShape);
        buttonPresentations.setBackgroundResource(0);
        radioGroupStatisticsBy.check(R.id.radioButtonWeight);

        radioGroupStatisticsBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButtonOption = statisticsFragment.findViewById(i);
                if (radioButtonOption.getText().toString().equals("Unidades")) {
                    units = true;
                    weight = false;
                    if (presentation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttonPresentations.performClick();
                            }
                        }, 10);
                    } if (categories) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttonCategories.performClick();
                            }
                        }, 10);
                    }
                } else {
                    units = false;
                    weight = true;
                    if (presentation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttonPresentations.performClick();
                            }
                        }, 10);
                    } if (categories) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttonCategories.performClick();
                            }
                        }, 10);
                    }
                }
            }
        });

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                categories = true;
                presentation = false;
                buttonCategories.setBackground(buttonShape);
                buttonCategories.setBackgroundTintList(green);
                buttonPresentations.setBackgroundResource(0);
                if (units) {
                    PieChartCategoriesUnitsFragment pieChartCategoriesUnitsFragment = new PieChartCategoriesUnitsFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.spaceGraphics, pieChartCategoriesUnitsFragment);
                    transaction.commit();
                } if (weight) {
                    PieChartCategoriesWeightFragment pieChartCategoriesWeightFragment = new PieChartCategoriesWeightFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.spaceGraphics, pieChartCategoriesWeightFragment);
                    transaction.commit();
                }
            }
        });

        buttonPresentations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presentation = true;
                categories = false;
                buttonPresentations.setBackground(buttonShape);
                buttonPresentations.setBackgroundTintList(green);
                buttonCategories.setBackgroundResource(0);
                if (units) {
                    PieChartPresentationUnitsFragment pieChartPresentationUnitsFragment = new PieChartPresentationUnitsFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.spaceGraphics, pieChartPresentationUnitsFragment);
                    transaction.commit();
                } if (weight) {
                    PieChartPresentationWeightFragment pieChartPresentationWeightFragment = new PieChartPresentationWeightFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.spaceGraphics, pieChartPresentationWeightFragment);
                    transaction.commit();
                }
            }
        });
        return statisticsFragment;
    }
}