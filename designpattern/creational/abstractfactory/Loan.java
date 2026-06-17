package designpattern.creational.abstractfactory;

abstract class Loan {

  protected double rate;

  public void calculateLoanPayment(double loanAmount, int years) {
    System.out.println(
        "your monthly EMI is " + loanAmount * years * rate / 12 + " for the amount" + loanAmount
            + " you have borrowed");
  }
}
