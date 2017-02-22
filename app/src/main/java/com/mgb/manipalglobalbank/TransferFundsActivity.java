package com.mgb.manipalglobalbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class TransferFundsActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    String loggedUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_funds);
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner Drop down elements

        List<String> users = db.getOtherUsers(loggedUserID);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
