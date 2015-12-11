package budgetkeeper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CheckingDbHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "BUDGETKEEPER";

    private static final String DATABASE_NAME = "Checking2.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CHECKING ="checking";
    public static final String COLUMN_ID = "checkingid";
    public static final String COLUMN_NAME = "name";
    //public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT= "amount";
    public static final String COLUMN_ISDEBIT="isdebit";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + CHECKING + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ISDEBIT + " INTEGER," +
                    COLUMN_NAME + " TEXT, " +
                    //COLUMN_DATE + " REAL, " +
                    COLUMN_AMOUNT + " REAL " +
                    ")";

    public CheckingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CHECKING);
        onCreate(db);
    }
}
