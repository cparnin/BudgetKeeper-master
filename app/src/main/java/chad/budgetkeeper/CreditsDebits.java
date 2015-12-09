package chad.budgetkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreditsDebits extends Activity implements AdapterView.OnItemSelectedListener,
                                                        View.OnClickListener
{

    private RadioGroup radioGroup1, radioGroup2;
    private Spinner spinner1;
    private TextView amount1;
    private EditText amount1Edit;
    private Button submitBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_debits);

        /* Initialize Radio Groups and attach click handler */
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.clearCheck();

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1)
                {
                    Toast.makeText(CreditsDebits.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1)
                {
                    Toast.makeText(CreditsDebits.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        // SPINNER FOR DROP DOWN CHOICE
        spinner1 = (Spinner) findViewById(R.id.type1Spinner);
        spinner1.setOnItemSelectedListener(this);

        // set up spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.type1Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        // EDIT TEXT FOR AMOUNT
        amount1Edit = (EditText) findViewById(R.id.amountEditText1);
        amount1 = (TextView) findViewById(R.id.amount1);
        /*
        // store amount in database
        try {
            int Value = Integer.parseInt(amount1.getText().toString());
        }
        catch(NumberFormatException ex){
            throw ex;
        }*/

        submitBtn1 = (Button)findViewById(R.id.creditDebitSubmit);
        submitBtn1.setOnClickListener(this);
    }

    // WHAT WE SELECTED IN SPINNER
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

        // send to database entry
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

    public void onClear(View v)
    {
        /* Clears all selected radio buttons to default */
        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
    }

    public void onSubmit(View v)
    {
        RadioButton rb1 = (RadioButton) radioGroup1.findViewById(radioGroup1.getCheckedRadioButtonId());
        Toast.makeText(CreditsDebits.this, rb1.getText(), Toast.LENGTH_SHORT).show();

        RadioButton rb2 = (RadioButton) radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
        Toast.makeText(CreditsDebits.this, rb2.getText(), Toast.LENGTH_SHORT).show();
    }

    // click to corresponding activity
    public void onClick(View view)
    {

        if (view.getId() == R.id.creditRadioButton1)
        {
           // do stuff
        }
        if (view.getId() == R.id.debitRadioButton1)
        {
            //do stuff
        }
        if (view.getId() == R.id.checkingRadioButton1)
        {
            // do stuff
        }
        if (view.getId() == R.id.savingsRadioButton1)
        {
            //do stuff
        }
        if (view.getId() == R.id.creditDebitSubmit)
        {
            //do stuff
        }
    }

}
