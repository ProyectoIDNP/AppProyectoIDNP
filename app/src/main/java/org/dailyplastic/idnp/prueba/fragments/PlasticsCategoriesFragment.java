package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.CategoryGridViewAdapter;
import org.dailyplastic.idnp.prueba.model.Category;

import java.util.ArrayList;


public class PlasticsCategoriesFragment extends Fragment {

    GridView categoriesGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View plasticCategoriesFragment = inflater.inflate(R.layout.fragment_plastics_categories, container, false);

        categoriesGridView = plasticCategoriesFragment.findViewById(R.id.gridViewCategories);
        ArrayList<Category> categoriesModelArrayList = new ArrayList<Category>();

        categoriesModelArrayList.add(new Category(1, "Categoria 1", "10/12/2022", "34/35/2009"));
        categoriesModelArrayList.add(new Category(2, "Categoria 2", "10/12/2022", "34/35/2009"));
        categoriesModelArrayList.add(new Category(3, "Categoria 3", "10/12/2022", "34/35/2009"));
        categoriesModelArrayList.add(new Category(4, "Categoria 4", "10/12/2022", "34/35/2009"));
        categoriesModelArrayList.add(new Category(5, "Categoria 5", "10/12/2022", "34/35/2009"));
        categoriesModelArrayList.add(new Category(6, "Categoria 6", "10/12/2022", "34/35/2009"));

        CategoryGridViewAdapter adapter = new CategoryGridViewAdapter(getActivity(), categoriesModelArrayList);
        categoriesGridView.setAdapter(adapter);

        return plasticCategoriesFragment;
    }
}