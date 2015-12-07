package chad.budgetkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Recurring extends Activity implements AdapterView.OnItemSelectedListener
{

    private RadioButton creditsRadio2, debitRadio2;
    private Spinner spinner2, spinner3;
    private TextView amount2;
    private EditText amount2Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recurring);

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
        // store amount in database
        //int Value = Integer.parseInt(amount2.getText().toString());
    }

    // CREDIT DEBIT RADIO BUTTON
    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.creditRadioButton2:
                if (checked)
                    // send what user enters as a credit to database entry
                    break;
            case R.id.debitRadioButton2:
                if (checked)
                    // send what user enters as a debit to database entry
                    break;
        }
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

}
