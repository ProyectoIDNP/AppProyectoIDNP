package org.dailyplastic.idnp.prueba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;


public class EstadisticasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View estadisticasFragment =  inflater.inflate(R.layout.fragment_estadisticas, container, false);
        LinearLayout linearLayout = (LinearLayout) estadisticasFragment.findViewById(R.id.pieChart);
        int[] values = {20, 20, 40, 100, 20, 1};
        String[] data = {"PET o PETE (tereftalato de polietileno).", "HDPE (polietileno de alta densidad)", "PVC (policloruro de vinilo).", "LDPE (Polietileno de baja densidad).", "PP (Polipropileno).", "PS (Poliestireno)."};
        linearLayout.addView(new PieChart(getActivity(), values, data));
        return estadisticasFragment;
    }
}