package chad.budgetkeeper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import budgetkeeper.db.CheckingSource;

public class Breakdown extends Activity
{
// test
    private TextView checkingBreakdown, savingsBreakdown;
    CheckingSource datasource;
    SQLiteDatabase checkingdb;
    public static final String LOGTAG = "BUDGETKEEPER";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakdown);
        Log.i(LOGTAG,"1" );
        // tie TextViews to activity id's
        checkingBreakdown= (TextView)findViewById(R.id.credits);
        savingsBreakdown= (TextView)findViewById(R.id.debits);
        Log.i(LOGTAG,"2" );
        // scrollviews
        //TextView creditView = (TextView) findViewById(R.id.creditList);
        //creditView.setMovementMethod(new ScrollingMovementMethod());
        ListView creditList = (ListView) findViewById(R.id.creditList);
        Log.i(LOGTAG,"3" );
        //TextView debitView = (TextView) findViewById(R.id.debitList);
        //debitView.setMovementMethod(new ScrollingMovementMethod());
        ListView debitList = (ListView) findViewById(R.id.debitList);
        Log.i(LOGTAG,"4" );
        // Need to populate the scroll views with database entries
        datasource = new CheckingSource(this);
        Log.i(LOGTAG,"5" );
        datasource.open();
        Log.i(LOGTAG, "6");
        List<Checking> checkings = datasource.findAll();
        Log.i(LOGTAG,"7" );
        if(!(checkings.isEmpty())) {
            ArrayAdapter<Checking> adapter = new ArrayAdapter<Checking>(this, android.R.layout.simple_list_item_1, checkings);
            debitList.setAdapter(adapter);
        }
    }

}
