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
import android.widget.RadioButton;

import com.google.gson.Gson;

import org.dailyplastic.idnp.R;
import org.dailyplastic.idnp.prueba.adapters.PlasticRecyclerViewAdapter;
import org.dailyplastic.idnp.prueba.constants.Constants;
import org.dailyplastic.idnp.prueba.dto.ReporteDto;
import org.dailyplastic.idnp.prueba.dto.UserDto;
import org.dailyplastic.idnp.prueba.interfaces.PlasticService;
import org.dailyplastic.idnp.prueba.interfaces.ReportService;
import org.dailyplastic.idnp.prueba.model.Plastic;
import org.dailyplastic.idnp.prueba.views.PieChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PieChartCategoriesUnitsFragment extends Fragment {

    List<ReporteDto> listCategoryUnits;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pieChartCategoriesUnitsFragment = inflater.inflate(R.layout.fragment_pie_chart_categories, container, false);
        linearLayout = pieChartCategoriesUnitsFragment.findViewById(R.id.pieChart);

        //Recuperacion del id del usuario logueado
        SharedPreferences mPrefs = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("userLoggedIn", "");
        UserDto userObj = gson.fromJson(json, UserDto.class);

        getCategoryUnit(userObj.getUser().getId());
        return pieChartCategoriesUnitsFragment;
    }

    private void getCategoryUnit(Integer idUser) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportService reportService = retrofit.create(ReportService.class);
        Call<List<ReporteDto>> call = reportService.getCategoryUnits(idUser);
        call.enqueue(new Callback<List<ReporteDto>>() {
            @Override
            public void onResponse(Call<List<ReporteDto>> call, Response<List<ReporteDto>> response) {
                //si falla el response
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                listCategoryUnits = response.body();
                String[] categories =  new String[listCategoryUnits.size()];
                int[] values =  new int[listCategoryUnits.size()];
                for (int i = 0; i < listCategoryUnits.size(); i++ ) {
                    categories[i] = listCategoryUnits.get(i).getLabel();
                    values[i] = listCategoryUnits.get(i).getValue();
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