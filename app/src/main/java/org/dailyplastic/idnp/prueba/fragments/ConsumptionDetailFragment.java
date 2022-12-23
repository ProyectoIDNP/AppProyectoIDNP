package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dailyplastic.idnp.R;


public class ConsumptionDetailFragment extends Fragment {


    public ConsumptionDetailFragment() {

    }


    public static ConsumptionDetailFragment newInstance(String param1, String param2) {
        ConsumptionDetailFragment fragment = new ConsumptionDetailFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View consumptionDetailFragment = inflater.inflate(R.layout.fragment_consumption_detail, container, false);
        // Inflate the layout for this fragment




        return consumptionDetailFragment;
    }
}