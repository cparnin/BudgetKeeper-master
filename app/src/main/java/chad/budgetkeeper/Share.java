package chad.budgetkeeper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Share extends Activity
{

    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);

        sendBtn = (Button) findViewById(R.id.send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    public void sendEmail()
    {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Share with..."));
            finish();
            Log.i("Finished...", "");
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(Share.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.share, menu);
//        return true;
//    }

}
