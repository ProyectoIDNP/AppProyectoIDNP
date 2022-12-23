package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.views.PieChart;

public class PieChartPresentationUnitsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartPresentationUnitsFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        LinearLayout linearLayout = pieChartPresentationUnitsFragment.findViewById(R.id.pieChart);
        int[] values = {300, 20, 40, 100, 20, 10};
        String[] data = {"Bolsas", "Botellas", "Envases de plastico", "Juguetes", "Tarjetas de credito", "Guantes"};
        linearLayout.addView(new PieChart(getActivity(), values, data));
        return pieChartPresentationUnitsFragment;
    }
}