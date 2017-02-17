package com.mgb.manipalglobalbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LatestTransactionActivity extends AppCompatActivity {

    private Transaction transactionDBoperator;
    ListView listview;
    DatabaseHelper transactionDB;
    String loggedUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_transaction);
        transactionDB = new DatabaseHelper(this);
// Create the adapter to convert the array to views
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");
        TransactionAdapter adapter = new TransactionAdapter(this, (ArrayList<Transaction>) transactionDB.getNewTransactions(loggedUserID));
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.transactionsListView);
        listView.setAdapter(adapter);


//    transactionDBoperator = new Transactions(this);
//    try
//    {
//        transactionDBoperator.open();
//    }
//    catch (SQLException e) {e.printStackTrace();
//    }
//    List values = transactionDBoperator.getAllTransactions();
//
//    //listview=(ListView)findViewById(R.id.list);
//// Use the SimpleCursorAdapter to show the elements in a ListView
//    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, values);
//    setListAdapter(adapter);
}

//    public void addUser(View view) {
//        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
//        EditText text = (EditText) findViewById(R.id.editText1);
//        Transaction stud = transactionDBoperator.addTransaction(text.getText().toString());
//        adapter.add(stud);
//    }

//    public void deleteFirstUser(View view) {
//        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
//        Student stud =null;
//        if (getListAdapter().getCount() >0) {stud = (Student) getListAdapter().getItem(0);
//            transactionDBoperator.deleteStudent(stud);
//            adapter.remove(stud);
//        }
//    }

//    @Override
//    protected void onResume() {
//        try
//        {
//            transactionDBoperator.open();
//        }
//        catch
//                (SQLException e) {e.printStackTrace();
//        }
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {transactionDBoperator.close();
//        super.onPause();
//    }
}
