package pantriapp.russellmcdonald.com.pantirmark3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import pantriapp.russellmcdonald.com.pantirmark3.*;
import pantriapp.russellmcdonald.com.pantirmark3.adapter.RecipeAdapter;
import pantriapp.russellmcdonald.com.pantirmark3.model.Recipe;
import pantriapp.russellmcdonald.com.pantirmark3.network.OutService;
import pantriapp.russellmcdonald.com.pantirmark3.network.RetrofitInstance;
import pantriapp.russellmcdonald.com.pantirmark3.network.SignIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    String ingredients = "";
    OutService service =  RetrofitInstance.getClient().create(OutService.class);
    Button toPantri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getRecipes = findViewById(R.id.get_recipe);
        toPantri = findViewById(R.id.to_pantri_button);
        final EditText userInput = findViewById(R.id.user_input);

        toPantri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PantriStorage.class));
            }
        });

        getRecipes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ingredients = userInput.getText().toString();

                Call<ArrayList<Recipe>> call = service.getRcpSuggestions(ingredients,"jmatuRL9NWmshUs6mTAMdHvR0KQIp1XRQyOjsnLs7dwSK1FRJu");


                call.enqueue(new Callback<ArrayList<Recipe>>(){
                    @Override
                    public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                        buildRcpSuggestions(response.body());
                        Log.d("Full json", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                    }

                    @Override
                    public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Not so much Luck", Toast.LENGTH_LONG).show();
                        Log.d("Main Activity", "error loading from API" + t);
                    }
                });

            }
        });




    }






    private void buildRcpSuggestions(ArrayList<Recipe> recipeList) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }
}




