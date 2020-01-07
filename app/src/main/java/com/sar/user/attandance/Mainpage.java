package com.sar.user.attandance;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Mainpage extends AppCompatActivity {

      FirebaseDatabase firebaseDatabase;
      FirebaseAuth firebaseAuth;
      DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("userss");
        final TextView textView=findViewById(R.id.textView);
        final TextView textView2=findViewById(R.id.textView2);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference.child(firebaseAuth.getUid()).child("attadnace").setValue(0);
     databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             String a=dataSnapshot.child("rollnum").getValue().toString();
             textView.setText("Your rollnumber is -> "+a);
             String b=dataSnapshot.child("attadnace").getValue().toString();
             textView2.setText("Your attandance=> "+b);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });   }
}
