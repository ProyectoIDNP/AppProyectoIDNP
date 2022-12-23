package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.views.PieChart;

public class PieChartCategoriesUnitsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartCategoriesUnitsFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        LinearLayout linearLayout = pieChartCategoriesUnitsFragment.findViewById(R.id.pieChart);
        int[] values = {100, 2, 4, 1, 2, 1};
        String[] data = {"PET o PETE (tereftalato de polietileno)", "HDPE (polietileno de alta densidad)", "PVC (policloruro de vinilo)", "LDPE (Polietileno de baja densidad)", "PP (Polipropileno)", "PS (Poliestireno)"};
        linearLayout.addView(new PieChart(getActivity(), values, data));

        return pieChartCategoriesUnitsFragment;
    }

}