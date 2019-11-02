package com.autonoma.proyecto3.restapi;

import com.autonoma.proyecto3.models.PokemonDetalle;
import com.autonoma.proyecto3.models.Pokemons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon")
    Call<Pokemons> getDataPokemons();

    @GET("pokemon/{nombre}")
    Call<PokemonDetalle> getPokemonDetalle(@Path("nombre") String nombre);

}
