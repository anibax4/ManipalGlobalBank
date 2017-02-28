package com.mgb.manipalglobalbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    float currentBalanceFloat;
    String loggedUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        loggedUserID = intent.getStringExtra("userid");
        currentBalanceFloat = databaseHelper.getLatestBalance(loggedUserID);

        TextView balanceTopTV = (TextView)findViewById(R.id.tv_balanceTopView);
        balanceTopTV.setText(Float.toString(databaseHelper.getLatestBalance(loggedUserID)));
        Log.v("USERID: ",loggedUserID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView balanceTopTV = (TextView)findViewById(R.id.tv_balanceTopView);
        balanceTopTV.setText(Float.toString(databaseHelper.getLatestBalance(loggedUserID)));
    }

    public void last10TransactionsOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, LatestTransactionActivity.class);
        intent.putExtra("userid", loggedUserID);
        startActivity(intent);
    }

    public void statementOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, StatementActivity.class);
        intent.putExtra("userid", loggedUserID);
        startActivity(intent);
    }

    public void transferFundsOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, TransferFundsActivity.class);
        intent.putExtra("userid", loggedUserID);
        startActivity(intent);
    }

    public void utilityPaymentOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, UtilityPaymentActivity.class);
        intent.putExtra("userid", loggedUserID);
        startActivity(intent);
    }

    public void logoutOnClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
