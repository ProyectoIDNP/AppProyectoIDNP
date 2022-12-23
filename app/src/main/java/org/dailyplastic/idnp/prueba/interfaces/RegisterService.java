package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("users/")
    Call<User> create(@Body User user);
}
