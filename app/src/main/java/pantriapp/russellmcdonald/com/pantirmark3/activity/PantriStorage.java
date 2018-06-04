package pantriapp.russellmcdonald.com.pantirmark3.activity;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriModel;

public class PantriStorage extends Activity {
    private FirebaseFirestore fireDB;
    private FirebaseAuth fireAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantri_storage);
        fireDB = FirebaseFirestore.getInstance();
        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        assert fireUser != null;
        String currentID = fireUser.getUid();



        PantriModel protoTest = new PantriModel("Bacon", 2, "packs ", currentID);
        PantriModel test2 = new PantriModel("Lettuce", 1, "Head", currentID);

        addItem(protoTest, currentID);
        addItem(test2, currentID);

        queryDB(currentID);






    }

    public void queryDB(String currentID){
        CollectionReference pantriQuery = fireDB.collection("users").document(currentID).collection("PANTRI_ITEMS");
        pantriQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot doc = task.getResult();
                    if(doc != null){
                        Log.d( "DocuSnapshot data: ", String.valueOf(task.getResult().getQuery()));
                    }else{
                        Log.d("No Luck", "No document here");
                    }
                }else{
                    Log.d("Get has failed", String.valueOf(task.getException()));
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
}














