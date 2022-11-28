package org.dailyplastic.idnp.prueba.adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import org.dailyplastic.idnp.prueba.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dailyplastic.idnp.R;

public class CategoryGridViewAdapter extends ArrayAdapter<Category>{

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
        TextView categoryTextView = listItemView.findViewById(R.id.textViewCategory);
        ImageView categoryImageView = listItemView.findViewById(R.id.imageViewCategory);
        CardView categoryCardView = listItemView.findViewById(R.id.cardViewCategory);

        categoryTextView.setText(categoryModel.getName());


        String categoryAcronym = splitName(categoryModel.getName());

        //Colocacion de las imagenes
        switch (categoryAcronym) {
            case "PET":
                categoryImageView.setImageResource(R.drawable.ic_image_pet);
                categoryCardView.setCardBackgroundColor(0XFF7A16FC);
                break;
            case "PEAD":
                categoryImageView.setImageResource(R.drawable.ic_image_pead);
                categoryCardView.setCardBackgroundColor(0XFF6E8166);
                break;
            case "PVC":
                categoryImageView.setImageResource(R.drawable.ic_image_pvc);
                categoryCardView.setCardBackgroundColor(0XFFC2150A);
                break;
            case "PEBD":
                categoryImageView.setImageResource(R.drawable.ic_image_pebd);
                categoryCardView.setCardBackgroundColor(0XFFFE561D);
                break;
            case "PP":
                categoryImageView.setImageResource(R.drawable.ic_image_pp);
                categoryCardView.setCardBackgroundColor(0XFF36D78B);
                break;
            case "PS":
                categoryImageView.setImageResource(R.drawable.ic_image_ps);
                categoryCardView.setCardBackgroundColor(0XFF8EB6AB);
                break;
            case "O":
                categoryImageView.setImageResource(R.drawable.ic_image_o);
                categoryCardView.setCardBackgroundColor(0XFF33A3EB);
                break;
            default:
                categoryImageView.setImageResource(R.drawable.ic_image_not_available);
                categoryCardView.setCardBackgroundColor(0XFF000000);
                break;
        }

        return listItemView;
    }

    public String splitName(String foo) {
        String bar = foo. split(" ")[0];
        return bar;
    }


    

}
