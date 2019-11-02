package com.autonoma.proyecto3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.autonoma.proyecto3.models.Pokemon;
import com.autonoma.proyecto3.models.Pokemons;
import com.autonoma.proyecto3.restapi.PokemonAPI;
import com.autonoma.proyecto3.restapi.PokemonAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    PokemonAPI pokemonAPI;
    ListView lvPokemons;
    List<String> stringsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        lvPokemons = findViewById(R.id.lvListado);
        // retroit
        retrofit = new PokemonAdapter().getAdapter();
        //instanciamos restClient
        pokemonAPI=retrofit.create(PokemonAPI.class);

        //llamamos a servicio de pokemon
        Call<Pokemons> pokemonsCall = pokemonAPI.getDataPokemons();
        pokemonsCall.enqueue(new Callback<Pokemons>() {
            @Override
            public void onResponse(Call<Pokemons> call, Response<Pokemons> response) {
                //Log.d("Main",response.body().getResults().toString());
                List<Pokemon> pokemons = response.body().getResults();
                stringsList = new ArrayList<>((pokemons.size()));
                //
                for(Pokemon pokemon:pokemons){
                    stringsList.add(pokemon.getName());
                }
                //
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,stringsList);
                lvPokemons.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Pokemons> call, Throwable t) {
            }
        });

        lvPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("Main",stringsList.get(position));
                Intent intent = new Intent(MainActivity.this, Main2DetallePokemon.class);
                intent.putExtra("nombre", stringsList.get(position) );
                startActivity(intent);
            }
        });
    }
}
