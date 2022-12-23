package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.model.Plastic;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPlasticEditDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPlasticEditDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListPlasticEditDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPlasticEditDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPlasticEditDeleteFragment newInstance(String param1, String param2) {
        ListPlasticEditDeleteFragment fragment = new ListPlasticEditDeleteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

        /*TextView c,pr,a,pe;

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