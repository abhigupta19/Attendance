package com.sar.user.attandance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

import static com.sar.user.attandance.MainActivity.MY_GLOBAL;

public class Teacher extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail,password;
    SharedPreferences.Editor sharedPreference;
    public static final String  MY_GLOBAL = "my_global";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        firebaseAuth=FirebaseAuth.getInstance();
        gmail=findViewById(R.id.editText2);
        password=findViewById(R.id.editText6);
        sharedPreference = (SharedPreferences.Editor) getSharedPreferences(MY_GLOBAL,MODE_PRIVATE).edit();
        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newuser(gmail.getText().toString(),password.getText().toString());


            }
        });
    }
    private void newuser(String name,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(Teacher.this,"Registration complete",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Teacher.this,Teacher_front.class));
                sharedPreference.putInt("floe",2);
                sharedPreference.commit();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Teacher.this,"Registration failed",Toast.LENGTH_SHORT).show();
                Log.d("hiiii",e.getMessage());
            }
        });
    }
}
