package pantriapp.russellmcdonald.com.pantirmark3.network;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import pantriapp.russellmcdonald.com.pantirmark3.R;
import pantriapp.russellmcdonald.com.pantirmark3.activity.MainActivity;
import pantriapp.russellmcdonald.com.pantirmark3.activity.PantriStorage;
import pantriapp.russellmcdonald.com.pantirmark3.model.NewUser;
import pantriapp.russellmcdonald.com.pantirmark3.model.PantriModel;

public class SignIn extends AppCompatActivity {

    private EditText emailInput;
    private EditText passInput;
    private Button signInAuth;
    private Button logInAuth;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fireDB;
    private String userID;



    //alternate startup screen for logging in.
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.auth_for_sign_in);

        emailInput= findViewById(R.id.email_box);
        passInput= findViewById(R.id.password_box);
        signInAuth= findViewById(R.id.sign_up_button);
        logInAuth= findViewById(R.id.log_in_button);

        firebaseAuth = FirebaseAuth.getInstance();
        fireDB = FirebaseFirestore.getInstance();






        signInAuth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailInput.getText().toString().toLowerCase().trim();
                String password = passInput.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "enterPassword", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<4){
                    Toast.makeText(getApplicationContext(), "at least 5 charaters", Toast.LENGTH_LONG).show();
                    return;
                }

                registerUser(email,password);

                FirebaseUser fireUser =FirebaseAuth.getInstance().getCurrentUser();
                assert fireUser != null;
                final String currentID = fireUser.getUid();

                final NewUser newUser = new NewUser("RussTest", "PushTest", email);
                fireDB.collection("users")
                        .document(currentID)
                        .set(newUser)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("NOPE TRY AGAIN", e.toString());
                            }
                        });


                startActivity(new Intent(SignIn.this, PantriStorage.class));
                finish();

                PantriModel dummyData = new PantriModel("null", 1, "null", currentID);

                fireDB.collection("users").document(currentID).collection("PANTRI_ITEMS").document("list").set(dummyData);



            }
        });



        logInAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().toLowerCase().trim();
                String password = passInput.getText().toString().trim();

                logUserIn(email, password);

                startActivity(new Intent(SignIn.this, PantriStorage.class));
                finish();

            }
        });




    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser= firebaseAuth.getCurrentUser();



    }



    private void registerUser(final String email, final String password){
        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            logUserIn(email, password);
                            }else{
                            Toast.makeText(getApplicationContext(),"ATTEMPT TO AUTH: no auth for you." + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



    private void logUserIn(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "found you!", Toast.LENGTH_LONG).show();
                            FirebaseAuth.getInstance().getCurrentUser();

                        }else{
                            Toast.makeText(getApplicationContext(),"Not in records" + task.getException(), Toast.LENGTH_LONG).show();


                        }
                    }
                });
    }




}