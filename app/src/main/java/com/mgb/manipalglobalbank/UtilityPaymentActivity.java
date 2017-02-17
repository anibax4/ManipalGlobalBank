package com.mgb.manipalglobalbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UtilityPaymentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    static String values[] = new String[] {"Mobile Recharge","TV EMI","Mobile EMI"};
    static float number[] = {1000.00f, 4312.34f, 1245.67f};
    DatabaseHelper db = new DatabaseHelper(this);
    Transaction transaction = new Transaction();


    ListView lview;
    UtilityPaymentAdapter lviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_payment);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_payments, R.id.transactionBillAgainstTextView, R.id.transactionBillAmountTextView, values);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>()
//        listItem = (ListView)findViewById(R.id.utilityPaymentListView);
//        listItem.setAdapter(adapter);


        lview = (ListView) findViewById(R.id.utilityPaymentListView);
        lviewAdapter = new UtilityPaymentAdapter(this, values, number);

        lview.setAdapter(lviewAdapter);
        lview.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UtilityPaymentActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Transaction");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want transfer the specified amount?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                Intent intent = getIntent();
                String loggedUserID = intent.getStringExtra("userid");
                TextView transAmount = (TextView) findViewById(R.id.transactionBillAmountTextView);
                TextView transAgainst = (TextView) findViewById(R.id.transactionBillAgainstTextView);
                float amount = Float.parseFloat(transAmount.getText().toString());


                db.addTransaction(new Transaction(loggedUserID,amount,
                        db.getLatestBalance(loggedUserID)-amount,"Db",transAgainst.getText().toString(),transaction.setDateTime()));
                // Write your code here to invoke YES event
                Toast.makeText(getApplicationContext(), "Your payment was successful", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

}
