package pantriapp.russellmcdonald.com.pantirmark3.network;

import java.util.ArrayList;


import pantriapp.russellmcdonald.com.pantirmark3.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface OutService {
    @GET("findByIngredients?fillIngredients=false&ingredients=bacon%2Clettuce%2Ctomato&limitLicense=false&number=5&ranking=")
    Call<ArrayList<Recipe>> getRcpSuggestions(@Header("X-Mashape-Key") String api_key);

}
