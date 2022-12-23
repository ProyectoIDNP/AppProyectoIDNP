package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.dto.ConsumptionDto;
import org.dailyplastic.idnp.prueba.dto.ReporteDto;
import org.dailyplastic.idnp.prueba.model.Consumption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReportService {
    @GET("report/categories/unit/")
    Call<List<ReporteDto>> getCategoryUnits(@Query("user") int id);

    @GET("report/categories/weight/")
    Call<List<ReporteDto>> getCategoryWeight(@Query("user") int id);

    @GET("report/presentations/unit/")
    Call<List<ReporteDto>> getPresentationUnits(@Query("user") int id);

    @GET("report/presentations/weight/")
    Call<List<ReporteDto>> getPresentationWeight(@Query("user") int id);
}
