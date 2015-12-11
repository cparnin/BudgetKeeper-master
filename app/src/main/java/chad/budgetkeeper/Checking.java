package chad.budgetkeeper;

import java.text.NumberFormat;

public class Checking {
    private float amount;
    private String name;
    //private int date;
    private long id;
    private int isdebit;
    //Calendar cal=Calendar.getInstance();

    public Checking(){

    }

    public Checking(String name, int isdebit, float amount){
        setName(name);
        setAmount(amount);
        //setDate();
        setIsDebit(isdebit);
    }

    public int getIsDebit(){
        return(isdebit);
    }

    public float getAmount(){
        return(amount);
    }

    public String getName(){
        return(name);
    }

    //public int getDate(){
        //return(date);
    //}

    public long getId(){
        return(id);
    }

    public void setIsDebit(int isdebit){
        if(isdebit==1){
            this.isdebit=1;
        }
        else if(isdebit==0){
            this.isdebit=0;
        }
    }

    public void setAmount(float amount){
        this.amount=amount;
    }

    public void setName(String name){
        this.name=name;
    }

    //public void setDate(){
        //this.date=cal.get(Calendar.DATE);
    //}

    //public void setDate(int date){
        //this.date=date;
    //}

    public void setId(long id){
        this.id=id;
    }

    @Override
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance().getCurrencyInstance();
        return name + "\n(" + nf.format(amount) + ")";
    }

}
