package ar.edu.unsl.backend.model.repositories;

import java.util.List;

import ar.edu.unsl.backend.model.entities.User;
import ar.edu.unsl.backend.model.persistence.UserOperator;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserRepository
{
    @GET(UserOperator.RESOURCE)
    Call<List<User>> findAll();

    @GET(UserOperator.SINGLE_RESOURCE)
    Call<User> find(@Path(UserOperator.ID) Integer id);

    @POST(UserOperator.RESOURCE)
    Call<User> postUser(@Body User user);
}