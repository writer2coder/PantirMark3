package pantriapp.russellmcdonald.com.pantirmark3.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class PantriModel {

    public String item;
    public int itemCount;
    public String amount;
    public String userID;

    public PantriModel(String item, int itemCount, String amount, String userID) {
        this.item = item;
        this.itemCount = itemCount;
        this.amount = amount;
        this.userID= userID;
    }

    public PantriModel() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}


