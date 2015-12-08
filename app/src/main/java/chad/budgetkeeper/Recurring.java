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


public class Recurring extends Activity implements AdapterView.OnItemSelectedListener,
                                                    View.OnClickListener
{

    private RadioGroup radioGroup2;
    private Spinner spinner2, spinner3;
    private TextView amount2;
    private EditText amount2Edit;
    private Button submitBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recurring);

        /* Initialize Radio Group and attach click handler */
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1)
                {
                    Toast.makeText(Recurring.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SPINNERS FOR DROP DOWN CHOICES
        spinner2 = (Spinner) findViewById(R.id.type2Spinner);
        spinner2.setOnItemSelectedListener(this);
        spinner3 = (Spinner) findViewById(R.id.type3Spinner);
        spinner3.setOnItemSelectedListener(this);

        // set up spinners
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type2Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.type3Array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        // EDIT TEXT FOR AMOUNT
        amount2Edit = (EditText) findViewById(R.id.amountEditText2);
        amount2 = (TextView) findViewById(R.id.amount2);
        /*
        // store amount in database
        try {
            int Value = Integer.parseInt(amount2.getText().toString());
        }
        catch(NumberFormatException ex){
            throw ex;
        }*/

        submitBtn2 = (Button)findViewById(R.id.recurringSubmit);
        submitBtn2.setOnClickListener(this);
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
        radioGroup2.clearCheck();
    }

    public void onSubmit(View v)
    {
        RadioButton rb = (RadioButton) radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
        Toast.makeText(Recurring.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    // click to corresponding activity
    public void onClick(View view)
    {

        if (view.getId() == R.id.creditRadioButton2)
        {
            // do stuff
        }
        if (view.getId() == R.id.debitRadioButton2)
        {
            //do stuff
        }
        if (view.getId() == R.id.recurringSubmit)
        {
            // submit to database
        }
    }
}
