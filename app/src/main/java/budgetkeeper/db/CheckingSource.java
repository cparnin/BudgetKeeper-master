package budgetkeeper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import chad.budgetkeeper.Checking;

public class CheckingSource {

    public static final String LOGTAG="BUDGETKEEPER";

    SQLiteOpenHelper checkingdbhelper;
    SQLiteDatabase checkingdb;

    public CheckingSource(Context context){
        checkingdbhelper = new CheckingDbHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "Checking Database opened");
        checkingdb = checkingdbhelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Checking Database closed");
        checkingdbhelper.close();
    }

    public Checking create(Checking checking){
        ContentValues values = new ContentValues();
        values.put(CheckingDbHelper.COLUMN_ISPOSITIVE, checking.getIsPositive());
        values.put(CheckingDbHelper.COLUMN_NAME, checking.getName());
        values.put(CheckingDbHelper.COLUMN_DATE, checking.getDate());
        values.put(CheckingDbHelper.COLUMN_AMOUNT, checking.getAmount());
        long insertid = checkingdb.insert(CheckingDbHelper.CHECKING, null, values);
        checking.setId(insertid);
        return(checking);
    }
}
