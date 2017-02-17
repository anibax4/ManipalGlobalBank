package com.mgb.manipalglobalbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class StatementActivity extends AppCompatActivity {
    DatabaseHelper transactionDB;
    String loggedUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        transactionDB = new DatabaseHelper(this);
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");
        TransactionAdapter adapter = new TransactionAdapter(this, (ArrayList<Transaction>) transactionDB.getAllTransactions(loggedUserID));

        ListView listView = (ListView) findViewById(R.id.transactionsListView);
        listView.setAdapter(adapter);
    }
}
