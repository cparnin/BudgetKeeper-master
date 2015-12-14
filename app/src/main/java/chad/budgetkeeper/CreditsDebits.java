package chad.budgetkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

import budgetkeeper.db.CheckingSource;
import budgetkeeper.db.SavingSource;

public class CreditsDebits extends Activity implements AdapterView.OnItemSelectedListener,
                                                        View.OnClickListener
{
    String item;
    private RadioGroup radioGroup1, radioGroup2;
    private Spinner spinner1;
    private TextView amount1;
    private EditText amount1Edit;
    private Button submitBtn1;
    private boolean ischecking;
    private int isdebit;
    public static final String LOGTAG="BUDGETKEEPER";

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
        final RadioButton debit = (RadioButton) findViewById(R.id.debitRadioButton1);
        final RadioButton credit = (RadioButton) findViewById(R.id.creditRadioButton1);
        final RadioButton check = (RadioButton) findViewById(R.id.checkingRadioButton1);
        final RadioButton save = (RadioButton) findViewById(R.id.savingsRadioButton1);

        /* Attach CheckedChangeListener to radio group */
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) group.findViewById(checkedId);
                if (null != rb1 && checkedId > -1) {
                    Toast.makeText(CreditsDebits.this, rb1.getText(), Toast.LENGTH_SHORT).show();
                }
                if (rb1.getId() == credit.getId()){
                    isdebit = 0;
                Log.i(LOGTAG, "rb1 credit");
            }

            else if(rb1.getId()==debit.getId())
            {
                isdebit = 1;
                Log.i(LOGTAG, "rb1 debit");
            }
        }
    });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1)
                {
                    Toast.makeText(CreditsDebits.this, rb2.getText(), Toast.LENGTH_SHORT).show();
                }
                if(rb2.getId()==check.getId())
                {
                    ischecking=true;
                    Log.i(LOGTAG, "rb2 checking");
                }
                else if(rb2.getId()==save.getId())
                {
                    ischecking=false;
                    Log.i(LOGTAG, "rb2 saving");
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

        submitBtn1 = (Button)findViewById(R.id.creditDebitSubmit);
        submitBtn1.setOnClickListener(this);
    }

    // WHAT WE SELECTED IN SPINNER
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // On selecting a spinner item
        item = parent.getItemAtPosition(pos).toString();

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
        Log.i(LOGTAG, "in credits/debits onclick" );
        /*
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
        */
        if (view.getId() == R.id.creditDebitSubmit)
        {
            Float uhoh = Float.parseFloat(amount1Edit.getText().toString());
            Log.i(LOGTAG, "in submit");

            if(ischecking){
                Log.i(LOGTAG, "in ischecking");
                CheckingSource checksource = new CheckingSource(this);
                Checking submitchecking = new Checking(item, isdebit, uhoh);
                checksource.open();
                checksource.create(submitchecking);
                checksource.close();
            }
            else if(!ischecking){
                Log.i(LOGTAG, "in issaving");
                SavingSource savesource = new SavingSource(this);
                Saving submitsaving = new Saving(item, isdebit, uhoh);
                savesource.open();
                savesource.create(submitsaving);
                savesource.close();
            }
        }
    }

}
