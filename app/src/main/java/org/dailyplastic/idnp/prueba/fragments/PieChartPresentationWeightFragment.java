package org.dailyplastic.idnp.prueba.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.ReporteDto;
import org.dailyplastic.idnp.prueba.interfaces.ReportService;
import org.dailyplastic.idnp.prueba.views.PieChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PieChartPresentationWeightFragment extends Fragment {

    List<ReporteDto> listPresentationWeight;
    LinearLayout linearLayout;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartPresentationWeightFragment = inflater.inflate(R.layout.fragment_pie_chart_presentation, container, false);
        linearLayout = pieChartPresentationWeightFragment.findViewById(R.id.pieChart);
        getPresentationWeight();
        return pieChartPresentationWeightFragment;
    }

    private void getPresentationWeight() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);
        Call<List<ReporteDto>> call = reportService.getPresentationWeight(19);
        call.enqueue(new Callback<List<ReporteDto>>() {
            @Override
            public void onResponse(Call<List<ReporteDto>> call, Response<List<ReporteDto>> response) {
                //si falla el response
                if (!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listPresentationWeight = response.body();
                String[] categories = new String[listPresentationWeight.size()];
                int[] values = new int[listPresentationWeight.size()];
                for (int i = 0; i < listPresentationWeight.size(); i++) {
                    categories[i] = listPresentationWeight.get(i).getLabel();
                    values[i] = listPresentationWeight.get(i).getValue();
                }
                linearLayout.addView(new PieChart(getActivity(), values, categories));
            }

            @Override
            public void onFailure(Call<List<ReporteDto>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}