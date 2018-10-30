package pantriapp.russellmcdonald.com.pantirmark3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import pantriapp.russellmcdonald.com.pantirmark3.ProtoAdapter;
import pantriapp.russellmcdonald.com.pantirmark3.ProtoRecipe;
import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.adapter.RecipeAdapter;
import pantriapp.russellmcdonald.com.pantirmark3.model.Recipe;
import pantriapp.russellmcdonald.com.pantirmark3.network.OutService;
import pantriapp.russellmcdonald.com.pantirmark3.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OutService service =  RetrofitInstance.getClient().create(OutService.class);

        Call<ArrayList<Recipe>> call = service.getRcpSuggestions("jmatuRL9NWmshUs6mTAMdHvR0KQIp1XRQyOjsnLs7dwSK1FRJu");

        call.enqueue(new Callback<ArrayList<Recipe>>(){
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                buildRcpSuggestions((ArrayList<Recipe>) response.body());
                Log.d("Full json", new GsonBuilder().setPrettyPrinting().create().toJson(response));

            }
            
            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Not so much Luck", Toast.LENGTH_LONG).show();
                Log.d("Main Activity", "error loading from API" + t);
            }
        });


    }

    private void buildRcpSuggestions(ArrayList<Recipe> recipeList) {
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new RecipeAdapter(this, recipeList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }
}


