package com.example.fbase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    public static String check = "";
    private EditText edtPassWord, edtUseName;
    private TextView editTextForgotPassword;
    private Button btnSignIn, btnSignIn_SignUp;
    private ImageView imageViewFbLogin, imageViewEmailLogin;
    private ImageButton imageButtonMat, imageButtonNhamMat;
    public static DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        database = new DatabaseHelper(this);
//        database.deleteAll();
        edtUseName = (EditText) findViewById(R.id.edtUserName);
        edtPassWord = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn_SignUp = (Button) findViewById(R.id.btnSignIn_SignUp);
        imageViewFbLogin = (ImageView) findViewById(R.id.imgfblogin);
        imageViewEmailLogin = (ImageView) findViewById(R.id.imggmaillogin);
        imageButtonMat = (ImageButton) findViewById(R.id.imgMat);
        imageButtonNhamMat = (ImageButton) findViewById(R.id.imgNhamMat);
        editTextForgotPassword = (TextView) findViewById(R.id.forgotPassword);
        imageButtonNhamMat.setVisibility(View.INVISIBLE);
        controlButton();
    }

    boolean status = false;

    private void controlButton() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUseName.getText().length() != 0 && edtPassWord.getText().length() != 0) {
                    Cursor dataAccount = database.GetData("SELECT * FROM TaiKhoan");
                    while (dataAccount.moveToNext()) {
                        String userName = dataAccount.getString(0);
                        String password = dataAccount.getString(1);
                        if (edtUseName.getText().toString().equals(userName) && edtPassWord.getText().toString().equals(password)) {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            check = "Đăng nhập thành công";
                            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                            intent.putExtra("user",edtUseName.getText().toString());
                            startActivity(intent);
                            edtUseName.setText("");
                            edtPassWord.setText("");
                            status = true;
                            break;
                        }
                    }
                    if (!dataAccount.moveToNext() && !status) {
                        Toast.makeText(DangNhapActivity.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DangNhapActivity.this, "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignIn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, com.example.fbase.DangKyActivity.class);
                startActivity(intent);
            }
        });
        imageButtonMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imageButtonNhamMat.setVisibility(View.VISIBLE);
                imageButtonMat.setVisibility(View.INVISIBLE);
            }
        });
        imageButtonNhamMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imageButtonNhamMat.setVisibility(View.INVISIBLE);
                imageButtonMat.setVisibility(View.VISIBLE);
            }
        });

    }
}
