package com.mgb.manipalglobalbank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by anibax on 2/7/2017.
 */
public class Transaction {

    private String userID;
    private float balance;
    private float transactionAmount;
    private String transactionType;
    private String transactionDate;
    private String transactionAgainst;

    public Transaction(){

    }

    public Transaction(String userID, float transactionAmount, float balance, String transactionType,
                       String transactionAgainst,  String transactionDate) {
        this.balance = balance;
        this.transactionAgainst = transactionAgainst;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.userID = userID;
    }

    //    public Transaction(String userID, float balance, float transactionAmount, String transactionType, String transactionDate, String transactionAgainst) {
//        this.userID = userID;
//        this.balance = balance;
//        this.transactionAmount = transactionAmount;
//        this.transactionType = transactionType;
//        this.transactionDate = transactionDate;
//        this.transactionAgainst = transactionAgainst;
//    }

    public String getUserID() {

        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

//    public String setTransactionDate() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
//        return dateFormat.format(date);
//    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAgainst() {
        return transactionAgainst;
    }

    public void setTransactionAgainst(String transactionAgainst) {
        this.transactionAgainst = transactionAgainst;
    }

    public String setDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
