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

import org.dailyplastic.idnp.prueba.model.Category;

import java.util.List;

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

        categoryTextView.setText(categoryModel.getName());
        categoryImageView.setImageResource(R.drawable.ic_launcher_background);
        return listItemView;
    }

}
