package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.model.Plastic;

public class PlasticsDescriptionFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PlasticsDescriptionFragment() {
        // Required empty public constructor
    }

    public static PlasticsDescriptionFragment newInstance(String param1, String param2) {
        PlasticsDescriptionFragment fragment = new PlasticsDescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Plastic p = new Plastic(1, "a", "a", "a", 1, 1, "a", "a");
        View v = inflater.inflate(R.layout.fragment_plastics_description, container);

        /* TextView c,pr,a,pe;

        c = v.findViewById(R.id.categoria);
        pr = v.findViewById(R.id.presentacion);
        a = v.findViewById(R.id.years);
        pe = v.findViewById(R.id.peso);

        c.setText(p.getCategory());
        pr.setText(p.getPresentation());
        a.setText(p.getDecompositionTime().toString());
        pe.setText(p.getUnitWeight().toString());*/

        return v;
    }
}