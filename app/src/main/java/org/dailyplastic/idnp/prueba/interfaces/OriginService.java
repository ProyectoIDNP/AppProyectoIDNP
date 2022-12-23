package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.model.Origin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OriginService {
    @GET("users/plastics/origins")
    Call<List<Origin>> getAll();
}
