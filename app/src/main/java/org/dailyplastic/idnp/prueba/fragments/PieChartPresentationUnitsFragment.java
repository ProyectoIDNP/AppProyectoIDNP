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

public class PieChartPresentationUnitsFragment extends Fragment {

    List<ReporteDto> listPresentationUnits;
    LinearLayout linearLayout;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartPresentationUnitsFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        linearLayout = pieChartPresentationUnitsFragment.findViewById(R.id.pieChart);
        getPresentationUnits();
        return pieChartPresentationUnitsFragment;
    }

    private void getPresentationUnits() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);
        Call<List<ReporteDto>> call = reportService.getPresentationUnits(19);
        call.enqueue(new Callback<List<ReporteDto>>() {
            @Override
            public void onResponse(Call<List<ReporteDto>> call, Response<List<ReporteDto>> response) {
                //si falla el response
                if (!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listPresentationUnits = response.body();
                String[] categories = new String[listPresentationUnits.size()];
                int[] values = new int[listPresentationUnits.size()];
                for (int i = 0; i < listPresentationUnits.size(); i++) {
                    categories[i] = listPresentationUnits.get(i).getLabel();
                    values[i] = listPresentationUnits.get(i).getValue();
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