package org.dailyplastic.idnp.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.dailyplastic.idnp.R;


public class EstadisticasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View estadisticasFragment =  inflater.inflate(R.layout.fragment_estadisticas, container, false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        PieChartCategoriesFragment pieChartCategoriesFragment = new PieChartCategoriesFragment();

        Button buttonCategories = estadisticasFragment.findViewById(R.id.buttonCategorias);
        Button buttonPresentation = estadisticasFragment.findViewById(R.id.buttonPresentacion);

        transaction.replace(R.id.pieChart, pieChartCategoriesFragment).commit();

        buttonCategories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PieChartCategoriesFragment pieChartCategoriesFragment = new PieChartCategoriesFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.pieChart, pieChartCategoriesFragment);
                transaction.commit();
            }
        });

        buttonPresentation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PieChartPresentationFragment pieChartPresentationFragment = new PieChartPresentationFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.pieChart, pieChartPresentationFragment);
                transaction.commit();
            }
        });

        return estadisticasFragment;
    }
}