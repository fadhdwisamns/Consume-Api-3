package com.fadh.cataloguemoveapi;
;
import com.fadh.cataloguemoveapi.model.ModelOmdb;
import com.fadh.cataloguemoveapi.model.SearchMdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
//    @GET("movie/top_rated")
//    Call<Model> getTopRatedMovies(@Query("apikey") String apiKey);
//
//    @GET("movie/{id}")
//    Call<Model> getMovieDetails(@Path("i") int id, @Query("apikey") String apiKey);
    @GET(".")
    Call<SearchMdb>getDetails(@Query("s") String tittle , @Query("apikey") String apiKey);
}
