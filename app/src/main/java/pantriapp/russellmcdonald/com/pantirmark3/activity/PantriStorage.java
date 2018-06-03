package pantriapp.russellmcdonald.com.pantirmark3.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
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

        CollectionReference pantriPath = fireDB.collection("users").document(currentID).collection("PANTRI_ITEMS");


        PantriModel protoTest = new PantriModel("Bacon", 2, "packs ", currentID);
        PantriModel test2 = new PantriModel("Lettuce", 1, "Head", currentID);

        addItem(protoTest, currentID);
        addItem(test2, currentID);


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














