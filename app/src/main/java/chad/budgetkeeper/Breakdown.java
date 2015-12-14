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
import budgetkeeper.db.SavingSource;

public class Breakdown extends Activity
{
// test
    private TextView checkingBreakdown, savingsBreakdown;
    CheckingSource checkdatasource;
    SavingSource savingdatasource;
    SQLiteDatabase checkingdb;
    public static final String LOGTAG = "BUDGETKEEPER";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakdown);
        // tie TextViews to activity id's
        checkingBreakdown= (TextView)findViewById(R.id.credits);
        savingsBreakdown= (TextView)findViewById(R.id.debits);
        // scrollviews
        //TextView creditView = (TextView) findViewById(R.id.creditList);
        //creditView.setMovementMethod(new ScrollingMovementMethod());
        ListView creditList = (ListView) findViewById(R.id.creditList);
        //TextView debitView = (TextView) findViewById(R.id.debitList);
        //debitView.setMovementMethod(new ScrollingMovementMethod());
        ListView debitList = (ListView) findViewById(R.id.debitList);
        // Need to populate the scroll views with database entries
        checkdatasource = new CheckingSource(this);
        checkdatasource.open();
        savingdatasource = new SavingSource(this);
        savingdatasource.open();

        List<Checking> debitcheckings = checkdatasource.checkingDebit();
        List<Checking> debitsavings = savingdatasource.checkingDebit2();
        if(!(debitsavings.isEmpty())) {
            Log.i(LOGTAG,"adding debits");
            debitcheckings.addAll(debitsavings);
        }
        float foodamount1=0;
        float foodamount2=0;
        float gasamount1=0;
        float gasamount2=0;
        float entamount1=0;
        float entamount2=0;
        float payrollamount1=0;
        float payrollamount2=0;
        float billsamount1=0;
        float billsamount2=0;
        float miscamount1=0;
        float miscamount2=0;

        for(int i=0;i<debitcheckings.size();i++){
            if((debitcheckings.get(i).getName()).equals("Food")){
                foodamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
            else if((debitcheckings.get(i).getName()).equals("Gas")){
                gasamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
            else if((debitcheckings.get(i).getName()).equals("Entertainment")){
                entamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
            else if((debitcheckings.get(i).getName()).equals("Payroll")){
                payrollamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
            else if((debitcheckings.get(i).getName()).equals("Bills")){
                billsamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
            else if((debitcheckings.get(i).getName()).equals("Misc")){
                miscamount1 += debitcheckings.get(i).getAmount();
                debitcheckings.remove(i);
            }
        }
        if(foodamount1!=0) {
            Checking food = new Checking();
            food.setAmount(foodamount1);
            food.setName("Food");
            debitcheckings.add(food);
        }
        if(gasamount1!=0) {
            Checking gas = new Checking();
            gas.setAmount(gasamount1);
            gas.setName("Gas");
            debitcheckings.add(gas);
        }
        if(entamount1!=0) {
            Checking ent = new Checking();
            ent.setAmount(entamount1);
            ent.setName("Entertainment");
            debitcheckings.add(ent);
        }
        if(payrollamount1!=0) {
            Checking payroll = new Checking();
            payroll.setAmount(payrollamount1);
            payroll.setName("Payroll");
            debitcheckings.add(payroll);
        }
        if(billsamount1!=0) {
            Checking bills = new Checking();
            bills.setAmount(billsamount1);
            bills.setName("Bills");
            debitcheckings.add(bills);
        }
        if(miscamount1!=0) {
            Checking misc = new Checking();
            misc.setAmount(miscamount1);
            misc.setName("Misc");
            debitcheckings.add(misc);
        }

        List<Checking> creditcheckings = checkdatasource.checkingCredit();
        List<Checking> creditsavings = savingdatasource.checkingCredit2();
        if(!(creditsavings.isEmpty())) {
            Log.i(LOGTAG,"adding credits");
            creditcheckings.addAll(creditsavings);
        }

        for(int i=0;i<creditcheckings.size();i++){
            if((creditcheckings.get(i).getName()).equals("Food")){
                foodamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
            else if((creditcheckings.get(i).getName()).equals("Gas")){
                gasamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
            else if((creditcheckings.get(i).getName()).equals("Entertainment")){
                entamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
            else if((creditcheckings.get(i).getName()).equals("Payroll")){
                payrollamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
            else if((creditcheckings.get(i).getName()).equals("Bills")){
                billsamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
            else if((creditcheckings.get(i).getName()).equals("Misc")){
                miscamount2 += creditcheckings.get(i).getAmount();
                creditcheckings.remove(i);
            }
        }
        if(foodamount2!=0) {
            Checking food2 = new Checking();
            food2.setAmount(foodamount2);
            food2.setName("Food");
            creditcheckings.add(food2);
        }
        if(gasamount2!=0) {
            Checking gas2 = new Checking();
            gas2.setAmount(gasamount2);
            gas2.setName("Gas");
            creditcheckings.add(gas2);
        }
        if(entamount2!=0) {
            Checking ent2 = new Checking();
            ent2.setAmount(entamount2);
            ent2.setName("Entertainment");
            creditcheckings.add(ent2);
        }
        if(payrollamount2!=0) {
            Checking payroll2 = new Checking();
            payroll2.setAmount(payrollamount2);
            payroll2.setName("Payroll");
            creditcheckings.add(payroll2);
        }
        if(billsamount2!=0) {
            Checking bills2 = new Checking();
            bills2.setAmount(billsamount2);
            bills2.setName("Bills");
            creditcheckings.add(bills2);
        }
        if(miscamount2!=0) {
            Checking misc2 = new Checking();
            misc2.setAmount(miscamount2);
            misc2.setName("Misc");
            creditcheckings.add(misc2);
        }
        if(!(debitcheckings.isEmpty())) {
            ArrayAdapter<Checking> adapter = new ArrayAdapter<Checking>(this, android.R.layout.simple_list_item_1, debitcheckings);
            debitList.setAdapter(adapter);
        }
        if(!(creditcheckings.isEmpty())) {
            ArrayAdapter<Checking> adapter2 = new ArrayAdapter<Checking>(this, android.R.layout.simple_list_item_1, creditcheckings);
            creditList.setAdapter(adapter2);
        }
    }

}
