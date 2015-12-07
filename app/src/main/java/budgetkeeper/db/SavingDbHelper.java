package budgetkeeper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SavingDbHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "BUDGETKEEPER";

    private static final String DATAABASE_NAME = "saving.db";
    private static final int DATABASE_VERSION = 1;

    public static final String SAVING ="Saving";
    public static final String COLUMN_ID = "SavingId";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_Amount= "Amount";
    public static final String COLUMN_ISPOSITIVE="IsPositive";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + SAVING + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ISPOSITIVE + " INTEGER," +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DATE + " REAL, " +
                    COLUMN_Amount + " REAL " +
                    ")";

    public SavingDbHelper(Context context) {
        super(context, DATAABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been creeated");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DRAP TABLE IF EXISTS " + SAVING);
        onCreate(db);
    }
}
