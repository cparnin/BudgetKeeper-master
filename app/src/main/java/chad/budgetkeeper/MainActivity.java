package chad.budgetkeeper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import budgetkeeper.db.CheckingSource;
import budgetkeeper.db.SavingSource;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    // clickable buttons to other activities
    private Button breakdownBtn, creditsDebitsBtn, recurringBtn, shareBtn;
    // display text views
    private TextView checkingBal, savingsBal;
    //data sources for db
    CheckingSource checkingsource;
    SavingSource savingsource;
    //calendar
    //Calendar cal=Calendar.getInstance();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tie TextViews to activity id's
        checkingBal= (TextView)findViewById(R.id.checkingDisplay);
        savingsBal= (TextView)findViewById(R.id.savingsDisplay);

        /*
        CORY:

        This is my code from displaying a list of scores in a game
        Maybe you can use some of it to display info from database...
        Of course, this isn't what we want, but we do need something like it

        SharedPreferences scorePrefs = getSharedPreferences(PlayGame.GAME_PREFS, 0);
        String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");
        StringBuilder scoreBuild = new StringBuilder("");
        for(String score : savedScores)
            scoreBuild.append(score+"\n");
        //display scores
        scoreView.setText(scoreBuild.toString());
        */

        // tie buttons to activity id's
        creditsDebitsBtn = (Button) findViewById(R.id.creditsdebits);
        recurringBtn = (Button) findViewById(R.id.recurring);
        breakdownBtn = (Button) findViewById(R.id.breakdown);
        shareBtn = (Button) findViewById(R.id.share);

        creditsDebitsBtn.setOnClickListener(this);
        recurringBtn.setOnClickListener(this);
        breakdownBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //instancing data source for db
        checkingsource = new CheckingSource(this);
        savingsource = new SavingSource(this);

    }

    @Override
    // click to corresponding activity
    public void onClick(View view)
    {
        if (view.getId() == R.id.creditsdebits)
        {
            Intent creditDebitIntent = new Intent(this, CreditsDebits.class);
            this.startActivity(creditDebitIntent);
        }
        else if (view.getId() == R.id.recurring)
        {
            Intent recurringIntent = new Intent(this, Recurring.class);
            this.startActivity(recurringIntent);
        }
        else if (view.getId() == R.id.breakdown)
        {
            Intent breakdownIntent = new Intent(this, Breakdown.class);
            this.startActivity(breakdownIntent);
        }
        else
        {
            Intent shareIntent = new Intent(this, Share.class);
            this.startActivity(shareIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://chad.budgetkeeper/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://chad.budgetkeeper/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    protected void onResume(){
        super.onResume();
        checkingsource.open();
        savingsource.open();
    }

    @Override
    protected void onPause(){
        super.onPause();
        checkingsource.close();
        savingsource.close();
    }
}
