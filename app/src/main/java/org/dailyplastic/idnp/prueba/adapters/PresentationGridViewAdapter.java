package org.dailyplastic.idnp.prueba.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.List;

public class PresentationGridViewAdapter extends ArrayAdapter<Presentation>{

    public PresentationGridViewAdapter(@NonNull Context context, List<Presentation> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        Presentation presentationModel = getItem(position);
        TextView presentationTextView = listItemView.findViewById(R.id.cardItemTextView);
        ImageView presentationImageView = listItemView.findViewById(R.id.cardItemImageView);
        ConstraintLayout presentationContainerTextView = listItemView.findViewById(R.id.cardItemContainerTextView); //falta darle color

        if ((position % 2 != 0)) {
            presentationContainerTextView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.presentation_color_1));
        } else {
            presentationContainerTextView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.presentation_color_2));
        }

        presentationTextView.setText(presentationModel.getName());
        presentationImageView.setImageResource(R.drawable.ic_image_not_available); //se coloca como imagen no disponible


        return listItemView;
    }
}
