package com.example.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtuser, edtpass;
    Button buttonLogin, buttonRegister;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtuser = findViewById(R.id.username);
        edtpass = findViewById(R.id.password);

        buttonLogin = findViewById(R.id.login);
        buttonRegister = findViewById(R.id.register);
        sp = getSharedPreferences("dailyfeed",Context.MODE_PRIVATE);
        editor = sp.edit();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();


            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void login() {
        String user = edtuser.getText().toString();
        String pass = edtpass.getText().toString();

        if (user.equals("") || pass.equals("")) {
            Toast.makeText(this, "Username or Password is blank", Toast.LENGTH_SHORT).show();
        } else if (null != checkUser(user, pass)) {

            String userDb = checkUser(user, pass);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("uname", userDb);
            startActivity(i);
            finish();
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            edtuser.setText("");
            edtpass.setText("");
            edtuser.requestFocus();
        }


    }

    public String checkUser(String user, String pass) {

        SQLiteDatabase db = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select id,email,user,pass from user where user = ? and pass = ?", new String[]{user, pass});
        Log.i("YAY",""+db.rawQuery("select id,email,user,pass from user where user = ? and pass = ?", new String[]{user, pass}));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String username = cursor.getString(2);
            String email = cursor.getString(1);
            /*SharedPreferences.Editor sp = getSharedPreferences("username", MODE_PRIVATE).edit();
            sp.putString("user", username);
            sp.putString("email",email);
            sp.apply();*/
            editor.putString("user",username);
            editor.putString("email",email);
            editor.commit();
            cursor.close();
            return username;
        }
        return null;
    }
}
