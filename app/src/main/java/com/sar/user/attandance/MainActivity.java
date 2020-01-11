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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText gmail,password,rollnum,name;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Map<String,String> map;
    Map<String,String> map1;
    SharedPreferences.Editor sharedPreference;
    public static final String  MY_GLOBAL = "my_global";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
       sharedPreference = (SharedPreferences.Editor) getSharedPreferences(MY_GLOBAL,MODE_PRIVATE).edit();

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null) {
            Intent intent = new Intent(this, Mainpage.class);
            startActivity(intent);

        }

         gmail=findViewById(R.id.editText4);
         password=findViewById(R.id.editText);
         rollnum=findViewById(R.id.editText3);
         name=findViewById(R.id.editText5);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map=new HashMap<>();
                map.put("rollnumber",rollnum.getText().toString());
                newuser(gmail.getText().toString(),password.getText().toString());
                map1=new HashMap<>();
                map1.put(rollnum.getText().toString(),name.getText().toString());



            }
        });
          firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference();



    }
    private void newuser(String name,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(MainActivity.this,"Registration complete",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Mainpage.class));
                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(map);
                databaseReference.child("users_roll").setValue(map);
                databaseReference.child("users_name").setValue(map1);
                Log.d("hiiiii",firebaseAuth.getCurrentUser().getUid());
                sharedPreference.putInt("floe",1);
                sharedPreference.commit();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                Log.d("hiiii",e.getMessage());
            }
        });
    }

}

