package Membership;

public class YearlyPayment {

    private int year;
    private int amountOwed;



    public YearlyPayment(int year, int amountOwed){
        this.year = year;
        this.amountOwed = amountOwed;
    }

    public int getYear(){
        return year;
    }

    public int getAmountOwed(){
        return amountOwed;
    }


    public void payDebt(int amount){
        amountOwed = amountOwed - amount;

    }

}
