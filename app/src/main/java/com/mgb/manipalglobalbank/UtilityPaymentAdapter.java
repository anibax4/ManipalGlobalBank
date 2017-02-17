package com.mgb.manipalglobalbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by anibax on 2/13/2017.
 */
public class UtilityPaymentAdapter extends BaseAdapter {

    Activity context;
    String title[];
    float amount[];

    public UtilityPaymentAdapter(Activity context, String[] title, float[] amount) {
        super();
        this.context = context;
        this.title = title;
        this.amount = amount;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return title.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtViewAgainst;
        TextView txtViewAmount;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_payments, null);
            holder = new ViewHolder();
            holder.txtViewAgainst = (TextView) convertView.findViewById(R.id.transactionBillAgainstTextView);
            holder.txtViewAmount = (TextView) convertView.findViewById(R.id.transactionBillAmountTextView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtViewAgainst.setText(title[position]);
        holder.txtViewAmount.setText(String.valueOf(amount[position]));
        return convertView;
    }

}
