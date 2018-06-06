package pantriapp.russellmcdonald.com.pantirmark3.activity;

import android.app.Activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.adapter.FireStoreRecyclerAdapter;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriList;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriModel;

public class PantriStorage extends Activity {
    private FirebaseFirestore fireDB;
    private FirebaseAuth fireAuth;
    private RecyclerView recyclerView;
    private FireStoreRecyclerAdapter fireAdapter;
    private ArrayList<PantriList> pantriLists = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantri_storage);

        recyclerView = findViewById(R.id.recycler_view_pantri);
        Button toMainRecipes = findViewById(R.id.to_recipes_button);
        Button addItemBtn = findViewById(R.id.add_item_button);
        final EditText addItem = findViewById(R.id.add_item_box);
        final EditText addCount= findViewById(R.id.add_count_box);
        final EditText addType= findViewById(R.id.add_type_box);

        fireDB = FirebaseFirestore.getInstance();

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        assert fireUser != null;
        final String currentID = fireUser.getUid();

        toMainRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PantriStorage.this, MainActivity.class));
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = addItem.getText().toString();
                int count= Integer.parseInt(addCount.getText().toString());
                String type = addType.getText().toString();

                if (TextUtils.isEmpty(item)) {
                    Toast.makeText(getApplicationContext(), "Enter item type", Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(addCount.getText())) {
                    Toast.makeText(getApplicationContext(), "Enter an amount for the items", Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(type)) {
                    Toast.makeText(getApplicationContext(), "Enter the type of the item", Toast.LENGTH_LONG).show();
                    return;
                }

                PantriModel addThingy = new PantriModel(item, count, type, currentID);
                addItem(addThingy, currentID);


            }
        });



        CollectionReference pantriQuery = fireDB.collection("users").document(currentID).collection("PANTRI_ITEMS");
        pantriQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    System.out.println("QUERY RESULTS" + task.toString());
                    ArrayList<PantriModel> pantriList = new ArrayList<>();
                    for (DocumentSnapshot doc : task.getResult()) {
                        PantriModel pm = doc.toObject(PantriModel.class);
                        Log.e("translate RESULTS", pm.toString());
                        pm.setID(doc.getId());
                        pantriList.add(pm);
                        buildPantriStorage(pantriList);
                        Log.e("Pantri built", pantriList.toString());


                    }
                } else {
                    Log.e("Pantri FAILED", task.toString());
                }
            }
        });
    }













    private void addItem(PantriModel model, String currentID) {
        fireDB.collection("users")
                .document(currentID)
                .collection("PANTRI_ITEMS")
                .add(model)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("New ITEM ADDED ", documentReference.getId());

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("NO DICE", e.toString());

            }
        });
    }

    private void buildPantriStorage(ArrayList<PantriModel> pantriList){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_pantri);

        FireStoreRecyclerAdapter adapter = new FireStoreRecyclerAdapter(pantriList, this );
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(PantriStorage.this);
        recyclerView.setLayoutManager(layManager);
        recyclerView.setAdapter(adapter);

    }










}







/*if(task.isSuccessful()){
                    QuerySnapshot doc = task.getResult();
                    if(doc != null){
                        buildPantriStorage(doc);
                        Log.d( "DocuSnapshot data: ", String.valueOf(task.getResult().getQuery()));
                    }else{
                        Log.d("No Luck", "No document here");
                    }
                }else{
                    Log.d("Get has failed", String.valueOf(task.getException()));
                }*/







