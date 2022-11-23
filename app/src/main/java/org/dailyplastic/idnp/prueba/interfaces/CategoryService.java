package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("plastics/categories/")
    Call<List<Category>> getAll();
}
