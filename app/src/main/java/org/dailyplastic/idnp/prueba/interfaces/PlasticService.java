package org.dailyplastic.idnp.prueba.interfaces;
import org.dailyplastic.idnp.prueba.model.Category;
import org.dailyplastic.idnp.prueba.model.Plastic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlasticService {
    @GET("plastics/")
    Call<List<Plastic>> getAll();

    @POST("plastics/")
    @FormUrlEncoded
    Call<Plastic> create(@Field("category") int categoryId,
                         @Field("presentation") int presentationId,
                         @Field("name") String name,
                            @Field("decomposition_time") int decompositionTime,
                                 @Field("unit_weight") int unitWeight);

}
