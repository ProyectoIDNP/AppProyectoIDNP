package org.dailyplastic.idnp.prueba.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.fragments.PlasticDetailFragment;
import org.dailyplastic.idnp.prueba.fragments.PlasticsCategoriesFragment;
import org.dailyplastic.idnp.prueba.fragments.PlasticsPresentationsFragment;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlasticRecyclerViewAdapter extends RecyclerView.Adapter<PlasticRecyclerViewAdapter.PlasticViewHolder> {

    List<Plastic> plasticList;
    List<Plastic> originalListPlastic;

    FragmentManager fragmentManager;

    public PlasticRecyclerViewAdapter(List<Plastic> plasticList, FragmentManager fragmentManager) {
        this.plasticList = plasticList;
        originalListPlastic = new ArrayList<>();
        originalListPlastic.addAll(plasticList);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public PlasticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item, null, false);
        return new PlasticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlasticViewHolder holder, int position) {
        holder.titlePlastic.setText(plasticList.get(position).getName());
        holder.categoryPlastic.setText(plasticList.get(position).getCategory().getName());
        holder.presentationPlastic.setText(plasticList.get(position).getPresentation().getName());
        //cambiar por la fecha y hora en la que se agrego el plastico
        holder.hourPlastic.setText("");
        holder.imagePlastic.setImageResource(R.drawable.ic_image_not_available);
    }

    @Override
    public int getItemCount() {
        return plasticList.size();
    }

    public void plasticFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            plasticList.clear();
            plasticList.addAll(originalListPlastic);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Plastic> plasticCollection = plasticList.stream()
                        .filter(i -> i.getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                plasticList.clear();
                plasticList.addAll(plasticCollection);
            } else {
                for (Plastic c : originalListPlastic) {
                    if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
                        plasticList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class PlasticViewHolder extends RecyclerView.ViewHolder {

        TextView titlePlastic;
        TextView categoryPlastic;
        TextView presentationPlastic;
        TextView hourPlastic;
        ImageView imagePlastic;

        public PlasticViewHolder(@NonNull View itemView) {
            super(itemView);

            titlePlastic = itemView.findViewById(R.id.recyclerItemTitle);
            categoryPlastic = itemView.findViewById(R.id.recyclerItemSubtitle1);
            presentationPlastic = itemView.findViewById(R.id.recyclerItemSubtitle2);
            imagePlastic = itemView.findViewById(R.id.recyclerItemImageView);
            hourPlastic = itemView.findViewById(R.id.recyclerItemHour);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle dataSend = new Bundle();
                    dataSend.putInt("idPlastic", plasticList.get(getAdapterPosition()).getId());

                    PlasticDetailFragment fragmentPlasticDetail = new PlasticDetailFragment();
                    fragmentPlasticDetail.setArguments(dataSend);
                    fragmentManager.beginTransaction().replace(R.id.container, fragmentPlasticDetail).commit();

                    System.out.println("*************** PLASTIC DETAIL***************");
                }
            });
        }
    }
}
