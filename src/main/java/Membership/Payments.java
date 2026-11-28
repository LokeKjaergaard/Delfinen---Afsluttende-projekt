package Membership;

import java.util.ArrayList;

public class Payments {

    private ArrayList<YearlyPayment> missingPayments = new ArrayList<>();
    private static final int MAX_PAYMENT = 40000;

    public void addMissingPayment(int year, int amountOwed) {
        missingPayments.add(new YearlyPayment(year, amountOwed));

    }

    public int getTotalOwed() {
        int totalOwed = 0;

        for (YearlyPayment yearlyPayment : missingPayments) {
            totalOwed += yearlyPayment.getAmountOwed();
        }
        return totalOwed;
    }

    public boolean isPaid() {

        if (getTotalOwed() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int registerPayment(int paymentAmount) {
        int surplus = paymentAmount;

        if (paymentAmount > MAX_PAYMENT){
            throw new IllegalArgumentException("Payment is too large!");
        } else if ((paymentAmount < 0) || (paymentAmount == 0)){
            throw new IllegalArgumentException("Payment must be 1 or above");
        }

        for (YearlyPayment yearlyPayment : missingPayments){

            int paying = Math.min(surplus, yearlyPayment.getAmountOwed());
            yearlyPayment.payDebt(paying);
            surplus -= paying;

        }
        return surplus;
    }

}
