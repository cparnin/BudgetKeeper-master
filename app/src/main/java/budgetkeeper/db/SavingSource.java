package budgetkeeper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SavingSource {

    public static final String LOGTAG="BUDGETKEEPER";

    SQLiteOpenHelper savingdbhelper;
    SQLiteDatabase savingdb;

    public SavingSource(Context context){
        savingdbhelper = new CheckingDbHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "Saving Database opened");
        savingdb = savingdbhelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG, "Saving Database closed");
        savingdbhelper.close();
    }

}
