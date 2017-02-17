package com.mgb.manipalglobalbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anibax on 2/7/2017.
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {
    public TransactionAdapter(Context context, ArrayList<Transaction> transaction) {
        super(context, 0, transaction);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Transaction transaction = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transactions, parent, false);
        }

        // Lookup view for data population
        TextView tvType = (TextView) convertView.findViewById(R.id.transactionTypeTextView);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.transactionAmountTextView);
        TextView tvAgainst = (TextView) convertView.findViewById(R.id.transactionAgainstTextView);
        TextView tvBalance = (TextView) convertView.findViewById(R.id.transactionBalanceTextView);
        TextView tvDate = (TextView) convertView.findViewById(R.id.transactionDateTextView);

        // Populate the data into the template view using the data object
        tvType.setText(transaction.getTransactionType());
        tvDate.setText(transaction.getTransactionDate());
        tvAgainst.setText(transaction.getTransactionAgainst());
        tvAmount.setText(String.valueOf(transaction.getTransactionAmount()));
        tvBalance.setText(String.valueOf(transaction.getBalance()));

        // Return the completed view to render on screen
        return convertView;
    }
}
