package com.example.android.golocalfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    DatabaseReference mRef;
    Button Login,SignUp;
    FirebaseUser mUser;
    static final public String EMAIL_ID="EMAIL_ID",ACTIVITY_NAME="MAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();
        mUser = mAuth.getCurrentUser();
        Login = (Button) findViewById(R.id.buttonLogin);
        SignUp = (Button) findViewById(R.id.buttonSignUp);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    /*protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            Login();
        }
    }*/

    private void userLogin() {
        String Email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim()  ;
        if(Email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please Enter a valid Email");
            editTextEmail.requestFocus();
            return;

        }
        if(Password.isEmpty()){
            editTextPassword.setError("Email is required");
            editTextPassword.requestFocus();
            return;
        }
        if(Password.length()<6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Login();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Login(){
        mRef = FirebaseDatabase.getInstance().getReference("TYPE");
        final FirebaseUser mUser = mAuth.getCurrentUser();
        String email2 = mUser.getEmail().replace('.', ',');

        mRef = FirebaseDatabase.getInstance().getReference("TYPE").child(email2);
        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = (String) snapshot.getValue();

                if(val.equals("BUYER")){
                    Intent intent = new Intent(MainActivity.this, AfterLoginBuyer.class);
                    startActivity(intent);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                else{
                    DatabaseReference mRef2 = FirebaseDatabase.getInstance().getReference();
                    Intent intent = new Intent(getApplicationContext(), AfterLoginSeller.class);
                    intent.putExtra(EMAIL_ID,mUser.getEmail());
                    intent.putExtra(ACTIVITY_NAME,"MAIN");
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}