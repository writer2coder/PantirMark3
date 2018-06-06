package pantriapp.russellmcdonald.com.pantirmark3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;

import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.activity.PantriStorage;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriModel;

public class FireStoreRecyclerAdapter extends RecyclerView.Adapter<FireStoreRecyclerAdapter.FireStoreViewHolder> {
    private ArrayList<PantriModel> pantriList;
    private Context context;


    public FireStoreRecyclerAdapter(ArrayList<PantriModel> pantriList, Context context) {
        this.pantriList = pantriList;
        this.context = context;
    }

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
        holder.itemName.setText(pantriList.get(position).getItem());
        holder.count.setText(String.valueOf(pantriList.get(position)));
        holder.amountType.setText(pantriList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return pantriList.size();
        }


    public class FireStoreViewHolder extends RecyclerView.ViewHolder{
        EditText itemName;
        EditText amountType;
        EditText count;

        FireStoreViewHolder(View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            amountType= itemView.findViewById(R.id.amount_view);
            count= itemView.findViewById(R.id.count);
        }

    }

}



