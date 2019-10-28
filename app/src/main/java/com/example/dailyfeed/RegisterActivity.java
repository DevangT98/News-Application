package com.example.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtcnfpass, edtemail, edtpass, edtuser;
    Button btnreg, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtuser = findViewById(R.id.username);
        edtpass = findViewById(R.id.password);
        edtcnfpass = findViewById(R.id.confirmpassword);
        btnreg = findViewById(R.id.register);
        edtemail = findViewById(R.id.emailTextView);
        btnLogin = findViewById(R.id.login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert();


            }
        });

    }
    public void insert() {
        try {
            String username = edtuser.getText().toString().trim();
            String email = edtemail.getText().toString().trim();
            String password = edtpass.getText().toString().trim();
            String confirmpassword = edtcnfpass.getText().toString().trim();

            if (username.equals("") || password.equals("") || email.equals("") || confirmpassword.equals(""))
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            else {
                SQLiteDatabase DB = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
                DB.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR,email VARCHAR, pass VARCHAR, conpass VARCHAR)");
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(this, "PASSWORD NOT MATCHING", Toast.LENGTH_LONG).show();
                } else {
                    String sql = "insert into user(user,email,pass,conpass)values(?,?,?,?)";
                    SQLiteStatement statement = DB.compileStatement(sql);
                    statement.bindString(1, username);
                    statement.bindString(2, email);
                    statement.bindString(3, password);
                    statement.bindString(4, confirmpassword);
                    statement.execute();
                    Toast.makeText(this, "RECORD ADDED", Toast.LENGTH_SHORT).show();
                    edtuser.setText("");
                    edtpass.setText("");
                    edtemail.setText("");
                    edtcnfpass.setText("");
                    edtuser.requestFocus();
                }
            }
            } catch(Exception ex){

                Toast.makeText(this, "Record fail", Toast.LENGTH_SHORT).show();

            }


    }
}






