package org.dailyplastic.idnp.prueba.interfaces;
import org.dailyplastic.idnp.prueba.model.Presentation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PresentationService {
    @GET("plastics/presentations/")
    Call<List<Presentation>> getAll();
}
