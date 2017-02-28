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
    String loggedUserID;
    float currentBalanceFloat;
    ListView lview;
    UtilityPaymentAdapter lviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_payment);
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");

        currentBalanceFloat = db.getLatestBalance(loggedUserID);
        TextView balanceTopTV = (TextView)findViewById(R.id.utilityTV_balanceTopView);
        balanceTopTV.setText(Float.toString(currentBalanceFloat));


        lview = (ListView) findViewById(R.id.utilityPaymentListView);
        lviewAdapter = new UtilityPaymentAdapter(this, values, number);

        lview.setAdapter(lviewAdapter);
        lview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UtilityPaymentActivity.this);
        alertDialog.setTitle("Confirm Transaction");
        alertDialog.setMessage("Are you sure you want transfer the specified amount?");
        TextView transAmount = (TextView) view.findViewById(R.id.transactionBillAmountTextView);
        final float amount = Float.parseFloat(transAmount.getText().toString());

        alertDialog.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                if (currentBalanceFloat > amount) {
                    TextView transAgainst = (TextView) view.findViewById(R.id.transactionBillAgainstTextView);
                    db.addTransaction(new Transaction(loggedUserID, amount,
                            db.getLatestBalance(loggedUserID) - amount, "Db", transAgainst.getText().toString(), transaction.setDateTime()));
                    Toast.makeText(getApplicationContext(), "Your payment was successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not enough balance for payment of the bill.", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        alertDialog.show();
    }

}
