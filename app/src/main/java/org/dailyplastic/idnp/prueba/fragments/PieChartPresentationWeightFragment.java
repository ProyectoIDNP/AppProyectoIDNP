package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.views.PieChart;

public class PieChartPresentationWeightFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartPresentationWeightFragment = inflater.inflate(R.layout.fragment_pie_chart_presentation, container, false);
        LinearLayout linearLayout = pieChartPresentationWeightFragment.findViewById(R.id.pieChart);
        int[] values = {400, 320, 140, 30, 20, 101};
        String[] data = {"Bolsas", "Botellas", "Envases de plastico", "Juguetes", "Tarjetas de credito", "Guantes"};
        linearLayout.addView(new PieChart(getActivity(), values, data));

        return pieChartPresentationWeightFragment;
    }
}