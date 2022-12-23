package org.dailyplastic.idnp.prueba.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.ReporteDto;
import org.dailyplastic.idnp.prueba.dto.UserDto;
import org.dailyplastic.idnp.prueba.interfaces.ReportService;
import org.dailyplastic.idnp.prueba.views.PieChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PieChartCategoriesWeightFragment extends Fragment {

    List<ReporteDto> listCategoryWeight;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartCategoriesWeightFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        linearLayout = pieChartCategoriesWeightFragment.findViewById(R.id.pieChart);

        //Recuperacion del id del usuario logueado
        SharedPreferences mPrefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("userLoggedIn", "");
        UserDto userObj = gson.fromJson(json, UserDto.class);


        getCategoryWeight(userObj.getUser().getId());
        return pieChartCategoriesWeightFragment;
    }

    private void getCategoryWeight(Integer userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);
        Call<List<ReporteDto>> call = reportService.getCategoryWeight(userId);
        call.enqueue(new Callback<List<ReporteDto>>() {
            @Override
            public void onResponse(Call<List<ReporteDto>> call, Response<List<ReporteDto>> response) {
                //si falla el response
                if (!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listCategoryWeight = response.body();
                String[] categories = new String[listCategoryWeight.size()];
                int[] values = new int[listCategoryWeight.size()];
                for (int i = 0; i < listCategoryWeight.size(); i++) {
                    categories[i] = listCategoryWeight.get(i).getLabel();
                    values[i] = listCategoryWeight.get(i).getValue();
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