package com.example.fbase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtUserName,edtEmail,edtPassWord,edtConfirmPassword,edtFullName,edtPhoneNumber;
    private Button btnSignIn,btnSignUp;
    private ImageButton imageButtonMatPassword,imageButtonNhamMatPassword;
    private ImageButton imageButtonMatConfirmPassword,imageButtonNhamMatConfirmPassword;
    boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPassWord= (EditText) findViewById(R.id.edtPassword);
        edtFullName= (EditText) findViewById(R.id.edtFullName);
        edtPhoneNumber= (EditText) findViewById(R.id.edtPhone);
        edtConfirmPassword= (EditText) findViewById(R.id.edtConfirmPassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        imageButtonMatPassword=(ImageButton) findViewById(R.id.imgMatPassword);
        imageButtonNhamMatPassword=(ImageButton) findViewById(R.id.imgNhamMatPassword);
        imageButtonMatConfirmPassword=(ImageButton) findViewById(R.id.imgMatConfirmPassword);
        imageButtonNhamMatConfirmPassword=(ImageButton) findViewById(R.id.imgNhamMatConfirmPassword);
        imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
        imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                if(edtUserName.getText().length()!=0&& edtEmail.getText().length()!=0
                        && edtPassWord.getText().length()!=0&&edtConfirmPassword.getText().length()!=0){
                    if(edtPassWord.getText().toString().equals(edtConfirmPassword.getText().toString())){
                        Cursor dataAccount = DangNhapActivity.database.GetData("SELECT * FROM TaiKhoan");
                        while (dataAccount.moveToNext()){
                            String userName =dataAccount.getString(0);
                            String password =dataAccount.getString(1);
                            if(userName.equals(edtUserName.getText().toString())){
                                Toast.makeText(DangKyActivity.this, "Ten tai khoan da co nguoi su dung", Toast.LENGTH_SHORT).show();
                                status=true;
                            }
                        }
                        if(!dataAccount.moveToNext() && !status ){
                            TaiKhoan tk = new TaiKhoan();
                            tk.setUserName(edtUserName.getText().toString());
                            tk.setPassword(edtPassWord.getText().toString());
                            tk.setFullname(edtFullName.getText().toString());
                            tk.setEmail(edtEmail.getText().toString());
                            tk.setPhone(edtPhoneNumber.getText().toString());
                            DangNhapActivity.database.insertTaiKhoan(tk);
                            Toast.makeText(DangKyActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(DangKyActivity.this, "Mat Khau Khong Khop", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(DangKyActivity.this, "Ban can nhap day du thong tin!!", Toast.LENGTH_SHORT).show();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        imageButtonMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatPassword.setVisibility(View.INVISIBLE);
            }
        });
        imageButtonMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.INVISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.VISIBLE);
            }
        });
        imageButtonNhamMatConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonMatConfirmPassword.setVisibility(View.VISIBLE);
                imageButtonNhamMatConfirmPassword.setVisibility(View.INVISIBLE);
            }
        });

    }
}