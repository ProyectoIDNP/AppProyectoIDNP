package org.dailyplastic.idnp.prueba.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.fragments.MyPlasticConsumptionFragment;
import org.dailyplastic.idnp.prueba.fragments.RegisterConsumptionFragment;
import org.dailyplastic.idnp.prueba.model.Consumption;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumptionRecyclerViewAdapter extends RecyclerView.Adapter<ConsumptionRecyclerViewAdapter.ConsumptionViewHolder> {
    List<Consumption> consumptionList;
    List<Consumption> originalListConsumption;
    private final OnItemClickListener listener;

    public ConsumptionRecyclerViewAdapter(List<Consumption> consumptionList, OnItemClickListener listener) {
        this.consumptionList = consumptionList;
        this.listener = listener;
        originalListConsumption = new ArrayList<>();
        originalListConsumption.addAll(consumptionList);
    }

    @NonNull
    @Override
    public ConsumptionRecyclerViewAdapter.ConsumptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item, null, false);
        return new ConsumptionRecyclerViewAdapter.ConsumptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionRecyclerViewAdapter.ConsumptionViewHolder holder, int position) {
        holder.titleConsumption.setText(consumptionList.get(position).getPlastic().getName());
        holder.originConsumption.setText(consumptionList.get(position).getOrigin().getName());
        holder.descriptionConsumption.setText(consumptionList.get(position).getDescription());
        //cambiar por la fecha y hora en la que se agrego el plastico
        String strDatewithTime = consumptionList.get(position).getUpdated();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDatewithTime);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        holder.hourConsumption.setText(zonedDateTime.format(FORMATTER));
        //cambiar la imagen utilizando picasso
        holder.imageConsumption.setImageResource(R.drawable.ic_image_not_available);
        holder.bind(consumptionList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return consumptionList.size();
    }

    public void plasticFilter(final String searchText) {
        int textLenght = searchText.length();
        if (textLenght == 0) {
            consumptionList.clear();
            consumptionList.addAll(originalListConsumption);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Consumption> consumptionCollection = consumptionList.stream()
                        .filter(i -> i.getPlastic().getName().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                consumptionList.clear();
                consumptionList.addAll(consumptionCollection);
            } else {
                for (Consumption c : originalListConsumption) {
                    if (c.getPlastic().getName().toLowerCase().contains(searchText.toLowerCase())) {
                        consumptionList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Consumption item);
    }

    public static class ConsumptionViewHolder extends RecyclerView.ViewHolder {

        TextView titleConsumption;
        TextView originConsumption;
        TextView descriptionConsumption;
        TextView hourConsumption;
        ImageView imageConsumption;

        public ConsumptionViewHolder(@NonNull View itemView) {
            super(itemView);

            titleConsumption = itemView.findViewById(R.id.recyclerItemTitle);
            originConsumption = itemView.findViewById(R.id.recyclerItemSubtitle1);
            descriptionConsumption = itemView.findViewById(R.id.recyclerItemSubtitle2);
            imageConsumption = itemView.findViewById(R.id.recyclerItemImageView);
            hourConsumption = itemView.findViewById(R.id.recyclerItemHour);
        }

        public void bind(final Consumption item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }


}
