package org.dailyplastic.idnp.prueba.interfaces;

import org.dailyplastic.idnp.prueba.dto.UserDto;
import org.dailyplastic.idnp.prueba.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/")
    Call<User> signIn(@Body User user);

    @POST("auth/login/")
    Call<UserDto> logIn(@Body User user);
}
