package chad.budgetkeeper;

import java.util.Calendar;

public class Checking {
    private boolean ispositive;
    private int amount;
    private String name;
    private int date;
    private long id;
    Calendar cal=Calendar.getInstance();


    public Checking(String name, int amount){
        setName(name);
        setAmount(amount);
        setDate();
        setIsPositive(amount);
    }

    public boolean getIsPositive(){
        return(ispositive);
    }

    public int getAmount(){
        return(amount);
    }

    public String getName(){
        return(name);
    }

    public int getDate(){
        return(date);
    }

    public long getId(){
        return(id);
    }

    public void setIsPositive(int amount){
        if(amount>=0){
            this.ispositive=true;
        }
        else if(amount<0){
            this.ispositive=false;
        }
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setDate(){
        this.date=cal.get(Calendar.DATE);
    }

    public void setId(long id){
        this.id=id;
    }

}
