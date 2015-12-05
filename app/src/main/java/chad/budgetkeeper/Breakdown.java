package chad.budgetkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Breakdown extends Activity
{

    private TextView checkingBreakdown, savingsBreakdown;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakdown);

        // tie TextViews to activity id's
        checkingBreakdown= (TextView)findViewById(R.id.credits);
        savingsBreakdown= (TextView)findViewById(R.id.debits);

        // scrollviews
        TextView creditView = (TextView) findViewById(R.id.creditList);
        creditView.setMovementMethod(new ScrollingMovementMethod());

        TextView debitView = (TextView) findViewById(R.id.debitList);
        debitView.setMovementMethod(new ScrollingMovementMethod());

        // Need to populate the scroll views with database entries

    }
}
