package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoryService {

    @GET("plastics/categories/")
    Call<List<Category>> getAll();

    @POST("plastics/categories/")
    Call<Category> create(@Body Category category);
    //@FormUrlEncoded
    //Call<Category> create(@Field("name") String name);

}
