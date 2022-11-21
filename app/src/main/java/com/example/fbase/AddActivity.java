package com.example.fbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText ten, anh, tenThuong, dacTinh, mauLa;
    Button btnSave, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ten = (EditText) findViewById(R.id.txtName);
        tenThuong = (EditText) findViewById(R.id.txttinhtrang);
        dacTinh = (EditText) findViewById(R.id.txtdactinh);
        mauLa = (EditText) findViewById(R.id.txtmau);
        anh = (EditText) findViewById(R.id.ivanh);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
    }

    private void insertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("ten", ten.getText().toString());
        map.put("tenThuong", tenThuong.getText().toString());
        map.put("dacTinh", tenThuong.getText().toString());
        map.put("mauLa", tenThuong.getText().toString());
        map.put("anh", anh.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("cayxanh").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private  void clearAll(){
        ten.setText("");
        tenThuong.setText("");
        dacTinh.setText("");
        mauLa.setText("");
        anh.setText("");
    }
}