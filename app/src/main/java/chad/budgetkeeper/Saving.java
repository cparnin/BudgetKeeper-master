package chad.budgetkeeper;

import java.text.NumberFormat;

public class Saving {
    private int isdebit;
    private float amount;
    private String name;
    private long id;
    //private int date;
    //Calendar cal=Calendar.getInstance();


    public Saving(){

    }

    public Saving(String name,int isdebit, float amount){
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

    public long getId(){
        return(id);
    }

    //public int getDate(){
        //return(date);
    //}

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

    public void setId(long id){
        this.id=id;
    }

    //public void setDate(){
        //this.date=cal.get(Calendar.DATE);
    //}

    @Override
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance().getCurrencyInstance();
        return name + "\n(" + nf.format(amount) + ")";
    }

}
