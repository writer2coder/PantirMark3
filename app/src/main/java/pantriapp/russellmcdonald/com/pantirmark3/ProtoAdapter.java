package pantriapp.russellmcdonald.com.pantirmark3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProtoAdapter extends RecyclerView.Adapter<ProtoAdapter.ProtoViewHolder> {

    private ArrayList<ProtoRecipe> pRecipes;

    public ProtoAdapter(ArrayList<ProtoRecipe> pRecipes) {
        this.pRecipes = pRecipes;
    }

    @NonNull
    @Override
    public ProtoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cv_recipe, parent, false);
        return new ProtoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProtoAdapter.ProtoViewHolder holder, int position) {

        holder.txtRcpTitle.setText(pRecipes.get(position).getProtoTitle());
        holder.getProtoImg().setImageResource(pRecipes.get(position).getProtoImagID());
    }

    @Override
    public int getItemCount() {
        return pRecipes.size();
    }


    class ProtoViewHolder extends RecyclerView.ViewHolder {

        TextView txtRcpTitle;
        ImageView protoImg;

        public ProtoViewHolder(View itemView) {
            super(itemView);
            txtRcpTitle = itemView.findViewById(R.id.recipe_title);
            protoImg = itemView.findViewById(R.id.recipe_image);
        }

        public TextView getTxtRcpTitle() {
            return txtRcpTitle;
        }

        public void setTxtRcpTitle(TextView txtRcpTitle) {
            this.txtRcpTitle = txtRcpTitle;
        }

        public ImageView getProtoImg() {
            return protoImg;
        }

        public void setProtoImg(ImageView protoImg) {
            this.protoImg = protoImg;
        }
    }
}
