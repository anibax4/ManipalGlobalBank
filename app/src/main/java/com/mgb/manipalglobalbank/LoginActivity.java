package com.mgb.manipalglobalbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper loginDB;
    String user;
    String password;
    EditText userEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEditText = (EditText) findViewById(R.id.editText_userid);
        passwordEditText = (EditText) findViewById(R.id.editText_password);
        loginDB = new DatabaseHelper(this);
    }

    public void login(View v){
        user = userEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (user.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter UserID and Password",Toast.LENGTH_LONG).show();
        }
        else {
            String passDb = loginDB.getLoginData(user);
            if (password.equalsIgnoreCase(passDb)) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("userid", user);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Welcome to Manipal Global Bank", Toast.LENGTH_LONG).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Wrong UserID or Password", Toast.LENGTH_LONG).show();
        }
    }
}
