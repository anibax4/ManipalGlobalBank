package com.mgb.manipalglobalbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TransferFundsActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    Transaction transaction = new Transaction();
    String loggedUserID;
    float currentBalanceFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_funds);
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");
        currentBalanceFloat = db.getLatestBalance(loggedUserID);

        TextView balanceTopTV = (TextView)findViewById(R.id.transferTV_balanceTopView);
        balanceTopTV.setText(Float.toString(currentBalanceFloat));

        Spinner spinner = (Spinner) findViewById(R.id.againstUserIDSpinner);
        List<String> users = db.getOtherUsers(loggedUserID);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void transferOnClick(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TransferFundsActivity.this);
        alertDialog.setTitle("Confirm Transaction");
        alertDialog.setMessage("Are you sure you want transfer the specified amount?");
        alertDialog.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = getIntent();
                String loggedUserID = intent.getStringExtra("userid");
                Spinner transAgainst = (Spinner) findViewById(R.id.againstUserIDSpinner);
                String againstUser = db.getUserID(transAgainst.getSelectedItem().toString());
                EditText amountET = (EditText) findViewById(R.id.transferAmountET);
                float amount = Float.parseFloat(amountET.getText().toString());

                db.addTransaction(new Transaction(loggedUserID,amount,
                        db.getLatestBalance(loggedUserID)-amount,"Db",db.getUserName(againstUser),transaction.setDateTime()));
                db.addTransaction(new Transaction(againstUser,amount,
                        db.getLatestBalance(againstUser)+amount,"Cr",db.getUserName(loggedUserID),transaction.setDateTime()));

                TextView balanceTopTV = (TextView)findViewById(R.id.transferTV_balanceTopView);
                balanceTopTV.setText(Float.toString(currentBalanceFloat));
                balanceTopTV.invalidate();
                Toast.makeText(getApplicationContext(), "Your transaction was successful", Toast.LENGTH_SHORT).show();
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
