package pantriapp.russellmcdonald.com.pantirmark3.network;

import java.util.ArrayList;


import pantriapp.russellmcdonald.com.pantirmark3.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface OutService {
    @GET("findByIngredients?fillIngredients=")
    Call<ArrayList<Recipe>> getRcpSuggestions(@Query("ingredients") String userInput,
                                              @Header("X-Mashape-Key") String api_key);

}
