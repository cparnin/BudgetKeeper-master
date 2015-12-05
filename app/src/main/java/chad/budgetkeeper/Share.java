package chad.budgetkeeper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Share extends Activity implements View.OnClickListener
{

    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);

        sendBtn = (Button) findViewById(R.id.send);
        sendBtn.setOnClickListener(this);
    }
//test

    /*  This is set up (I believe) to open the email app
        then attach the "database" in the email...I think
    */
    public void onClick(View view)
    {
        if (view.getId() == R.id.send)
        {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"email@example.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
            File root = Environment.getExternalStorageDirectory();
            // following database path may need changed
            String pathToMyAttachedFile = "/data/data/chad.budgetkeeper/databases";
            File file = new File(root, pathToMyAttachedFile);
            if (!file.exists() || !file.canRead()) {
                return;
            }
            Uri uri = Uri.fromFile(file);
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
        }
    }

}
