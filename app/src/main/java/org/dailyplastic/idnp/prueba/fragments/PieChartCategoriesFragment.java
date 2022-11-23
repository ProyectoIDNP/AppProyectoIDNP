package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.views.PieChart;

public class PieChartCategoriesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartCategoriesFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        LinearLayout linearLayout = (LinearLayout) pieChartCategoriesFragment.findViewById(R.id.pieChartCategories);
        int[] values = {20, 20, 40, 100, 20, 10};
        String[] data = {"PET o PETE (tereftalato de polietileno)", "HDPE (polietileno de alta densidad)", "PVC (policloruro de vinilo)", "LDPE (Polietileno de baja densidad)", "PP (Polipropileno)", "PS (Poliestireno)"};
        linearLayout.addView(new PieChart(getActivity(), values, data));

        return pieChartCategoriesFragment;
    }
}