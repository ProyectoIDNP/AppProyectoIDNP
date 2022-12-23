package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.dailyplastic.idnp.R;

public class Card_Item_OnClick extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cardItem = inflater.inflate(R.layout.card_item, container, false);
        CardView c = cardItem.findViewById(R.id.cardViewDescription);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDescription(cardItem);
            }
        });

        return cardItem;
    }

    public void showDescription(View view) {
        FragmentManager f = getActivity().getSupportFragmentManager();
        PlasticsDescriptionFragment d = new PlasticsDescriptionFragment();
        d.show(f,"Description");
    }
}
