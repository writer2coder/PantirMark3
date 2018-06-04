package pantriapp.russellmcdonald.com.pantirmark3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriModel;

public class FireStoreRecyclerAdapter extends RecyclerView.Adapter<FireStoreRecyclerAdapter.FireStoreViewHolder> {
    private ArrayList<PantriModel> pantriStorage;
    private Context context;
    private FirebaseFirestore fireDB;



    @NonNull
    @Override
    public FireStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater;
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cv_pantri_storage, parent, false);
        return new FireStoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FireStoreRecyclerAdapter.FireStoreViewHolder holder, int position) {
        holder.itemName.setText(pantriStorage.get(position).getItem());
        holder.count.setText(pantriStorage.get(position).getItemCount());
        holder.amountType.setText(pantriStorage.get(position).getItemCount());
    }

    @Override
    public int getItemCount() {
        return pantriStorage.size();
    }

    public FireStoreRecyclerAdapter(ArrayList<PantriModel> pantriStorage, Context context, FirebaseFirestore fireDB) {
        this.pantriStorage = pantriStorage;
        this.context = context;
        this.fireDB = fireDB;
    }

    public FireStoreRecyclerAdapter() {
    }

    //FireStoreRecyclerAdapter<PantriModel> storageOptions = new FireStoreRecyclerOptions.builder<PantriModel>()
    class FireStoreViewHolder extends RecyclerView.ViewHolder{
        EditText itemName;
        EditText amountType;
        Button count;

        FireStoreViewHolder(View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            amountType= itemView.findViewById(R.id.amount_view);
            count= itemView.findViewById(R.id.count);
        }

    }

}



