package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.dto.ConsumptionDto;
import org.dailyplastic.idnp.prueba.model.Consumption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsumptionService {
    @GET("users/plastics/")
    Call<List<Consumption>> getAllConsumptionByUserId(@Query("user") int id);

    @GET("users/plastics/{id}")
    Call<Consumption> getOne(@Path("id") Integer id);

    @POST("users/plastics/")
    Call<ConsumptionDto> create(@Body ConsumptionDto dto);
}