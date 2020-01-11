package com.sar.user.attandance;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mainpage extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,databaseReference22;
    SharedPreferences shared;
    private FirebaseDatabase databaseReference2;
    AlertDialog.Builder alert;
    EditText editText;
    ArrayList<String> listitem;
    ListView listView;
    Myadapter myadapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared= (SharedPreferences) getSharedPreferences(MainActivity.MY_GLOBAL,MODE_PRIVATE);

        final int ab=shared.getInt("floe",0);
        if(ab==0)
        {
            startActivity(new Intent(Mainpage.this,Vs.class));
        }
        if(ab==2)
        {
            startActivity(new Intent(Mainpage.this,Teacher_front.class));
        }
        setContentView(R.layout.activity_mainpage);
        listitem=new ArrayList<>();
        listView=findViewById(R.id.list);
       // listitem.add("DEF");
        myadapter =new Myadapter(Mainpage.this,listitem);
        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {

                            Log.d("jjjjjl",dataSnapshot1.getKey());
                            String e=dataSnapshot1.getKey();
                            if(e.equals("rollnumber"))
                            {
                                continue;
                            }
                            else
                            {
                                listitem.add(e);
                                myadapter.notifyDataSetChanged();
                            }





                    }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        //final TextView textView = findViewById(R.id.textView);
        //final TextView textView2 = findViewById(R.id.textView2);

        FloatingActionButton floatingActionButton2=findViewById(R.id.floatf);



        listView.setAdapter(myadapter);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(Mainpage.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.box2, null);
                alert.setView(view);
                alert.show();
                rac(view);
            }

            private void rac(View view) {
                editText =view.findViewById(R.id.edit_text25);
                Button button=view.findViewById(R.id.button5);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            final DatabaseReference roofref=FirebaseDatabase.getInstance().getReference();
                             roofref.addListenerForSingleValueEvent(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                     if(dataSnapshot.hasChild(editText.getText().toString()))
                                     {
                                         Toast.makeText(Mainpage.this,"Done",Toast.LENGTH_SHORT).show();
                                         roofref.child(editText.getText().toString()).child("users").child(FirebaseAuth.getInstance().getUid()).setValue("kanha");
                                         roofref.child(editText.getText().toString()).child("class").addListenerForSingleValueEvent(new ValueEventListener() {
                                             @Override
                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                 String bc = (String) dataSnapshot.getValue();
                                                 FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid()).child(bc).setValue("subject");

                                             }

                                             @Override
                                             public void onCancelled(@NonNull DatabaseError databaseError) {

                                             }
                                         });



                                     }
                                     else
                                     {
                                         Toast.makeText(Mainpage.this,"Not Done",Toast.LENGTH_SHORT).show();

                                     }
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError databaseError) {

                                 }
                             });


                    }
                });

            }

        });

    }

}

