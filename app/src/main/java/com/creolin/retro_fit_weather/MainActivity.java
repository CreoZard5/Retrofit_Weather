package com.creolin.retro_fit_weather;
//Name
//date time
//desc

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    //Sets UI components to variables
    EditText edtEmail, edtPassword;
    ImageButton btnLogin, btnSignUp;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //Creates and instance to link program with firebase database.
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sets UI components to variables
        edtEmail = findViewById(R.id.txvemail_input);
        edtPassword = findViewById(R.id.txvpassword_input);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        //Button method for signing in user
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variables to assign user's input to variables
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                //Firebase authentication to validate user log in details
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "Sign Up Successful.");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Sign Up Suceeded.", Toast.LENGTH_SHORT).show();
                                    //CurrentUser.currentUserKey=user.getUid();
//                                    Intent switchToSignUp = new Intent(MainActivity.this, SignUpActivity.class);
//                                    switchToSignUp.putExtra("LoginEmail", email);
//                                    startActivity(switchToSignUp);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Sign Up Failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            ;
        });
    }
}