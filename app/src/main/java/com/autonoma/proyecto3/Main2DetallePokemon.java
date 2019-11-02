package com.autonoma.proyecto3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.autonoma.proyecto3.models.PokemonDetalle;
import com.autonoma.proyecto3.models.Pokemons;
import com.autonoma.proyecto3.restapi.PokemonAPI;
import com.autonoma.proyecto3.restapi.PokemonAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2DetallePokemon extends AppCompatActivity {
    Retrofit retrofit;
    PokemonAPI pokemonAPI;
    TextView tvNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_detalle_pokemon);
        //
        tvNombre = findViewById(R.id.tvNombre);
        // retroit
        retrofit = new PokemonAdapter().getAdapter();
        //instanciamos restClient
        pokemonAPI=retrofit.create(PokemonAPI.class);
        //
        String name = getIntent().getExtras().getString ("nombre");
        Call<PokemonDetalle> pokemonCall = pokemonAPI.getPokemonDetalle(name);
        //
        pokemonCall.enqueue(new Callback<PokemonDetalle>() {
            @Override
            public void onResponse(Call<PokemonDetalle> call, Response<PokemonDetalle> response) {
                Log.d("PD",response.body().getName());
                tvNombre.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<PokemonDetalle> call, Throwable t) {

            }
        });


    }
}
