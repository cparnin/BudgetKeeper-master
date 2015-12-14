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

public class CheckingSource {

    public static final String LOGTAG="BUDGETKEEPER";

    SQLiteOpenHelper checkingdbhelper;
    SQLiteDatabase checkingdb;

    private static final String[] allColumns = {
            CheckingDbHelper.COLUMN_ID,
            CheckingDbHelper.COLUMN_ISDEBIT,
            CheckingDbHelper.COLUMN_NAME,
            //CheckingDbHelper.COLUMN_DATE,
            CheckingDbHelper.COLUMN_AMOUNT};

    public CheckingSource(Context context){
        checkingdbhelper = new CheckingDbHelper(context);
    }

    public void open(){
        checkingdb = checkingdbhelper.getWritableDatabase();
        Log.i(LOGTAG, "Checking Database opened");
    }

    public void close(){
        checkingdbhelper.close();
        Log.i(LOGTAG, "Checking Database closed");
    }

    public Checking create(Checking checking){
        ContentValues values = new ContentValues();
        values.put(CheckingDbHelper.COLUMN_ISDEBIT, checking.getIsDebit());
        values.put(CheckingDbHelper.COLUMN_NAME, checking.getName());
        //values.put(CheckingDbHelper.COLUMN_DATE, checking.getDate());
        values.put(CheckingDbHelper.COLUMN_AMOUNT, checking.getAmount());
        long insertid = checkingdb.insert(CheckingDbHelper.CHECKING, null, values);
        checking.setId(insertid);
        Log.i(LOGTAG, "checking database created");
        return(checking);
    }

    public List<Checking> allChecking(){
        Log.i(LOGTAG, "in findall");
        List<Checking> allcheckings = new ArrayList<Checking>();
        Log.i(LOGTAG, "allocated list checkings");
        Cursor cursor = checkingdb.query(CheckingDbHelper.CHECKING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Checking checking = new Checking();
                checking.setId(cursor.getLong(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ID)));
                checking.setIsDebit(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ISDEBIT)));
                checking.setName(cursor.getString(cursor.getColumnIndex(CheckingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_DATE)));
                checking.setAmount(cursor.getFloat(cursor.getColumnIndex(CheckingDbHelper.COLUMN_AMOUNT)));
                allcheckings.add(checking);
            }
        }
        cursor.close();
        return (allcheckings);
    }




    public List<Checking> checkingDebit() {
        Log.i(LOGTAG, "in findall");
        List<Checking> debitcheckings = new ArrayList<Checking>();
        Log.i(LOGTAG, "allocated list checkings");
        Cursor cursor = checkingdb.query(CheckingDbHelper.CHECKING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Checking checking = new Checking();
                checking.setId(cursor.getLong(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ID)));
                checking.setIsDebit(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ISDEBIT)));
                checking.setName(cursor.getString(cursor.getColumnIndex(CheckingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_DATE)));
                checking.setAmount(cursor.getFloat(cursor.getColumnIndex(CheckingDbHelper.COLUMN_AMOUNT)));
                if (checking.getIsDebit() == 1) {
                    debitcheckings.add(checking);
                }
            }
        }
        cursor.close();
        return (debitcheckings);
    }

    public List<Checking> checkingCredit() {
        Log.i(LOGTAG, "in checkingcredit");
        List<Checking> creditcheckings = new ArrayList<Checking>();
        Log.i(LOGTAG, "allocated list checkings");
        Cursor cursor = checkingdb.query(CheckingDbHelper.CHECKING, allColumns, null, null, null, null, null);
        Log.i(LOGTAG, "initiated cursor");

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Checking checking = new Checking();
                checking.setId(cursor.getLong(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ID)));
                checking.setIsDebit(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_ISDEBIT)));
                checking.setName(cursor.getString(cursor.getColumnIndex(CheckingDbHelper.COLUMN_NAME)));
                //checking.setDate(cursor.getInt(cursor.getColumnIndex(CheckingDbHelper.COLUMN_DATE)));
                checking.setAmount(cursor.getFloat(cursor.getColumnIndex(CheckingDbHelper.COLUMN_AMOUNT)));
                if (checking.getIsDebit() == 0) {
                    creditcheckings.add(checking);
                }
            }
        }
        cursor.close();
        return (creditcheckings);
    }

}
