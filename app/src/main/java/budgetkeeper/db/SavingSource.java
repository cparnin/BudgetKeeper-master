package budgetkeeper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import chad.budgetkeeper.Checking;
import chad.budgetkeeper.Saving;

public class SavingSource {

    public static final String LOGTAG="BUDGETKEEPER";

    private static final String[] allColumns = {
            SavingDbHelper.COLUMN_ID,
            SavingDbHelper.COLUMN_ISDEBIT,
            SavingDbHelper.COLUMN_NAME,
            //CheckingDbHelper.COLUMN_DATE,
            SavingDbHelper.COLUMN_AMOUNT};

    SQLiteOpenHelper savingdbhelper;
    SQLiteDatabase savingdb;

    public SavingSource(Context context){
        savingdbhelper = new SavingDbHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "Saving Database opened");
        savingdb = savingdbhelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Saving Database closed");
        savingdbhelper.close();
    }

    public Saving create(Saving saving){
        ContentValues values = new ContentValues();
        values.put(SavingDbHelper.COLUMN_ISDEBIT, saving.getIsDebit());
        values.put(SavingDbHelper.COLUMN_NAME, saving.getName());
        //values.put(CheckingDbHelper.COLUMN_DATE, checking.getDate());
        values.put(SavingDbHelper.COLUMN_AMOUNT, saving.getAmount());
        long insertid = savingdb.insert(SavingDbHelper.SAVING, null, values);
        saving.setId(insertid);
        Log.i(LOGTAG, "saving database created");
        return(saving);
    }

    public List<Saving> savingAll(){
        Log.i(LOGTAG, "in savingdebit");
        List<Saving> allsavings = new ArrayList<Saving>();
        Log.i(LOGTAG, "allocated list savings");
        Cursor cursor = savingdb.query(SavingDbHelper.SAVING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Saving saving = new Saving();
                saving.setId(cursor.getLong(cursor.getColumnIndex(SavingDbHelper.COLUMN_ID)));
                saving.setIsDebit(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_ISDEBIT)));
                saving.setName(cursor.getString(cursor.getColumnIndex(SavingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_DATE)));
                saving.setAmount(cursor.getFloat(cursor.getColumnIndex(SavingDbHelper.COLUMN_AMOUNT)));
                allsavings.add(saving);
            }
        }
        cursor.close();
        return (allsavings);
    }

    public List<Saving> savingDebit() {
        Log.i(LOGTAG, "in savingdebit");
        List<Saving> debitsavings = new ArrayList<Saving>();
        Log.i(LOGTAG, "allocated list savings");
        Cursor cursor = savingdb.query(SavingDbHelper.SAVING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Saving saving = new Saving();
                saving.setId(cursor.getLong(cursor.getColumnIndex(SavingDbHelper.COLUMN_ID)));
                saving.setIsDebit(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_ISDEBIT)));
                saving.setName(cursor.getString(cursor.getColumnIndex(SavingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_DATE)));
                saving.setAmount(cursor.getFloat(cursor.getColumnIndex(SavingDbHelper.COLUMN_AMOUNT)));
                if (saving.getIsDebit() == 1) {
                    debitsavings.add(saving);
                }
            }
        }
        cursor.close();
        return (debitsavings);
    }

    public List<Saving> savingCredit() {
        Log.i(LOGTAG, "in savingdebit");
        List<Saving> creditsavings = new ArrayList<Saving>();
        Log.i(LOGTAG, "allocated list savings");
        Cursor cursor = savingdb.query(SavingDbHelper.SAVING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Saving saving = new Saving();
                saving.setId(cursor.getLong(cursor.getColumnIndex(SavingDbHelper.COLUMN_ID)));
                saving.setIsDebit(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_ISDEBIT)));
                saving.setName(cursor.getString(cursor.getColumnIndex(SavingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_DATE)));
                saving.setAmount(cursor.getFloat(cursor.getColumnIndex(SavingDbHelper.COLUMN_AMOUNT)));
                if (saving.getIsDebit() == 0) {
                    creditsavings.add(saving);
                }
            }
        }
        cursor.close();
        return (creditsavings);
    }









    public List<Checking> checkingDebit2() {
        Log.i(LOGTAG, "in checkingdebit2");
        List<Checking> debitcheckings2 = new ArrayList<Checking>();
        Log.i(LOGTAG, "allocated list checkingsd2");
        Cursor cursor = savingdb.query(SavingDbHelper.SAVING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            Log.i(LOGTAG,"inside first if");
            while (cursor.moveToNext()) {
                Log.i(LOGTAG,"inside while");
                Checking checking = new Checking();
                checking.setId(cursor.getLong(cursor.getColumnIndex(SavingDbHelper.COLUMN_ID)));
                checking.setIsDebit(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_ISDEBIT)));
                checking.setName(cursor.getString(cursor.getColumnIndex(SavingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_DATE)));
                checking.setAmount(cursor.getFloat(cursor.getColumnIndex(SavingDbHelper.COLUMN_AMOUNT)));
                if (checking.getIsDebit() == 1) {
                    Log.i(LOGTAG,"inside if");
                    debitcheckings2.add(checking);
                }
            }
        }
        Log.i(LOGTAG,"returnig after dc2");
        cursor.close();
        return (debitcheckings2);
    }

    public List<Checking> checkingCredit2() {
        Log.i(LOGTAG, "in checkingcredit2");
        List<Checking> creditcheckings2 = new ArrayList<Checking>();
        Log.i(LOGTAG, "allocated list checkings2");
        Cursor cursor = savingdb.query(SavingDbHelper.SAVING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                Checking checking = new Checking();
                checking.setId(cursor.getLong(cursor.getColumnIndex(SavingDbHelper.COLUMN_ID)));
                checking.setIsDebit(cursor.getInt(cursor.getColumnIndex(SavingDbHelper.COLUMN_ISDEBIT)));
                checking.setName(cursor.getString(cursor.getColumnIndex(SavingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_DATE)));
                checking.setAmount(cursor.getFloat(cursor.getColumnIndex(SavingDbHelper.COLUMN_AMOUNT)));
                if (checking.getIsDebit() == 0) {

                    creditcheckings2.add(checking);
                }
            }
        }
        cursor.close();
        return (creditcheckings2);
    }

}
