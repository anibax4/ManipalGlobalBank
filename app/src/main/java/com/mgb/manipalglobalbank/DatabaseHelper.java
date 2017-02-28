package com.mgb.manipalglobalbank;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anibax on 2/3/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "User";
    public static final String USER_ID = "_userID";
    public static final String USER_NAME = "_userName";
    public static final String USER_PASSWORD = "_password";
    private static final String DATABASE_NAME = "Bank.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TRANSACTION_TABLE = "Transactions";
    public static final String TRANSACTION_BALANCE = "_balance";
    public static final String TRANSACTION_AMOUNT = "_transactionAmount";
    public static final String TRANSACTION_TYPE = "_transactionType";
    public static final String TRANSACTION_DATE = "_date";
    public static final String TRANSACTION_AGAINST = "_transactionAgainst";
    public static final String MyPREFERENCES = "MyPrefs";

    private Context appContext;

    private static final String DATABASE_CREATE_USER = "create table " + USERS_TABLE +
            "(" + USER_ID + " string primary key, " +
            USER_NAME + " string not null, " +
            USER_PASSWORD + " string not null);";

    private static final String DATABASE_CREATE_TRANSACTION = "create table " + TRANSACTION_TABLE +
            "(" + USER_ID + " string not null, " +
            TRANSACTION_BALANCE + " float not null, " +
            TRANSACTION_TYPE + " string not null, " +
            TRANSACTION_DATE + " string not null, " +
            TRANSACTION_AGAINST + " string not null, " +
            TRANSACTION_AMOUNT + " float not null);";

    public DatabaseHelper(Context c) {
        super (c,DATABASE_NAME,null,DATABASE_VERSION);
        this.appContext = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_USER);
        db.execSQL(DATABASE_CREATE_TRANSACTION);

        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Resources res = appContext.getResources();
        sharedpreferences = appContext.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        if ( sharedpreferences.getBoolean("databaseCreated", true))
        {
            String[] user = res.getStringArray(R.array.user_exec);
            for (int i = 0; i < user.length; i++){
                db.execSQL(user[i]);
            }
            String[] transactions = res.getStringArray(R.array.transaction_exec);
            for (int i = 0; i < transactions.length; i++){
                db.execSQL(transactions[i]);
            }
            editor.putBoolean("databaseCreated",false);
            editor.commit();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TRANSACTION_TABLE);
        onCreate(db);
    }

    public float getLatestBalance(String userID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TRANSACTION_TABLE,new String[] {TRANSACTION_BALANCE},USER_ID + "= ?", new String[] {userID},null, null, "_date DESC");
        cursor.moveToFirst();
        return cursor.getFloat(0);
    }

    public List<Transaction> getNewTransactions(String userID){
        List<Transaction> transactionList = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TRANSACTION_TABLE,null,USER_ID + "= ?", new String[] {userID},null, null, "_date DESC LIMIT 10");
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setUserID(cursor.getString(0));
                transaction.setBalance(Float.parseFloat(cursor.getString(1)));
                transaction.setTransactionType(cursor.getString(2));
                transaction.setTransactionDate(cursor.getString(3));
                transaction.setTransactionAgainst(cursor.getString(4));
                transaction.setTransactionAmount(Float.parseFloat(cursor.getString(5)));
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }
        return transactionList;
    }

    public List<Transaction> getAllTransactions(String userID){
        List<Transaction> transactionList = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TRANSACTION_TABLE,null,USER_ID + "= ?", new String[] {userID},null, null, "_date DESC");
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setUserID(cursor.getString(0));
                transaction.setBalance(Float.parseFloat(cursor.getString(1)));
                transaction.setTransactionType(cursor.getString(2));
                transaction.setTransactionDate(cursor.getString(3));
                transaction.setTransactionAgainst(cursor.getString(4));
                transaction.setTransactionAmount(Float.parseFloat(cursor.getString(5)));
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }
        return transactionList;
    }

    public String getLoginData(String userID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(USERS_TABLE,new String[] {USER_PASSWORD},USER_ID + "= ?", new String[] {userID},null, null, null);
        res.moveToFirst();
        if (res.getCount()==1) {
            return res.getString(0);
        }
        else
            return "";
    }

    public String getUserName(String userID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(USERS_TABLE,new String[] {USER_NAME},USER_ID + "= ?", new String[] {userID},null, null, null);
        res.moveToFirst();
        if (res.getCount()==1) {
            return res.getString(0);
        }
        else
            return "";
    }

    public String getUserID(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(USERS_TABLE,new String[] {USER_ID},USER_NAME + "= ?", new String[] {userName},null, null, null);
        res.moveToFirst();
        if (res.getCount()==1) {
            return res.getString(0);
        }
        else
            return "";
    }

    public List<String> getOtherUsers(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> idList = new ArrayList<String>();
        Cursor cursor = db.query(USERS_TABLE, new String[]{USER_NAME}, USER_ID + "!= ?", new String[]{userID}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                idList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return idList;
    }

    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, transaction.getUserID());
        values.put(TRANSACTION_AMOUNT, transaction.getTransactionAmount());
        values.put(TRANSACTION_BALANCE, transaction.getBalance());
        values.put(TRANSACTION_TYPE, transaction.getTransactionType());
        values.put(TRANSACTION_AGAINST, transaction.getTransactionAgainst());
        values.put(TRANSACTION_DATE, transaction.getTransactionDate());
        db.insert(TRANSACTION_TABLE, null, values);
        db.close();
    }
}
