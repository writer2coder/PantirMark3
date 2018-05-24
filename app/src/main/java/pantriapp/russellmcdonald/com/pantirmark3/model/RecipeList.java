package pantriapp.russellmcdonald.com.pantirmark3.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeList {

    @SerializedName("recipe_list")
    @Expose
    private ArrayList<Recipe> recipeList = null;

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }


    public void setRecipeList(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
