package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.model.Consumption;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConsumptionService {
    @GET("users/plastics/")
    Call<List<Consumption>> getAll();
}
