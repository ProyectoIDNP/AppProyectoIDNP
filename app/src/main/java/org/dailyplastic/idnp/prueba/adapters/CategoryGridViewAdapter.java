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

import org.dailyplastic.idnp.prueba.model.Category;

import java.util.List;

import org.dailyplastic.idnp.R;

public class CategoryGridViewAdapter extends ArrayAdapter<Category> {

    public CategoryGridViewAdapter(@NonNull Context context, List<Category> courseModelArrayList) {
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

        Category categoryModel = getItem(position);
        TextView categoryTextView = listItemView.findViewById(R.id.cardItemTextView);
        ImageView categoryImageView = listItemView.findViewById(R.id.cardItemImageView);
        CardView categoryCardView = listItemView.findViewById(R.id.cardViewDescription);
        ConstraintLayout categoryContainerTextView = listItemView.findViewById(R.id.cardItemContainerTextView);

        categoryTextView.setText(categoryModel.getName());

        String categoryAcronym = splitName(categoryModel.getName());

        //Colocacion de las imagenes
        switch (categoryAcronym) {
            case "PET":
                categoryImageView.setImageResource(R.drawable.ic_image_pet);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PET));
                break;
            case "PEAD":
                categoryImageView.setImageResource(R.drawable.ic_image_pead);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PEAD));
                break;
            case "PVC":
                categoryImageView.setImageResource(R.drawable.ic_image_pvc);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PVC));
                break;
            case "PEBD":
                categoryImageView.setImageResource(R.drawable.ic_image_pebd);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PEBD));
                break;
            case "PP":
                categoryImageView.setImageResource(R.drawable.ic_image_pp);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PP));
                break;
            case "PS":
                categoryImageView.setImageResource(R.drawable.ic_image_ps);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PS));
                break;
            case "O":
                categoryImageView.setImageResource(R.drawable.ic_image_o);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.O));
                break;
            default:
                categoryImageView.setImageResource(R.drawable.ic_image_not_available);
                categoryContainerTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Default));
                break;
        }

        return listItemView;
    }

    public String splitName(String foo) {
        String bar = foo. split(" ")[0];
        return bar;
    }
}
