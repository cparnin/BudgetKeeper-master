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


public class CreditsDebits extends Activity implements AdapterView.OnItemSelectedListener
{

    private RadioButton creditsRadio1, debitRadio1;
    private Spinner spinner1;
    private TextView amount1;
    private EditText amount1Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_debits);

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
        /*amount1 = (TextView) findViewById(R.id.amount1);
        // store amount in database
        try {
            int Value = Integer.parseInt(amount1.getText().toString());
        }
        catch(NumberFormatException ex){
            throw ex;
        }*/
    }

    // CREDIT DEBIT RADIO BUTTON
    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.creditRadioButton1:
                if (checked)
                    // send what user enters as a credit to database entry

                    break;
            case R.id.debitRadioButton1:
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
