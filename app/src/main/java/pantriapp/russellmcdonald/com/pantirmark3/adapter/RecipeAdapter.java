package pantriapp.russellmcdonald.com.pantirmark3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipeList){
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater;
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cv_recipe, parent, false);
        return new RecipeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.rcpTitleView.setText(recipeList.get(position).getTitle());
        Glide.with(context)
                .load(recipeList.get(position).getImage())
                .placeholder(R.drawable.airship)
                .into(holder.rcpImageView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder{
        TextView rcpTitleView;
        ImageView rcpImageView;

        RecipeViewHolder(View itemView){
            super(itemView);
            rcpTitleView= itemView.findViewById(R.id.recipe_title);
            rcpImageView = itemView.findViewById((R.id.recipe_image));
        }
    }






}
