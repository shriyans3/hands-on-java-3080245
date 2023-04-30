package bank;

import bank.exceptions.AmountException;

public class Account {
  private int Id;
  private String type;
  private double balance;

  public Account(int id,String type,double bal){
    setId(id);
    setType(type);
    setBalance(bal);
  }

  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException{
    if(amount < 1){
      throw new AmountException("Minimum deposit is 1.00");
    }
    else{
      double newbalance = balance + amount;
      setBalance(newbalance);
      DataSource.updateAccountBalance(Id, newbalance);
    }
  }

  public void withdraw(double amount) throws AmountException{
    if(amount < 0 ){
      throw new AmountException(("The Withdrawal amount should be less than 0"));
    }
    else if(amount > getBalance()){
      throw new AmountException("You do not have sufficient funds for withdrawal");
    }
    else{
      double newbalance = balance - amount;
      setBalance(newbalance);
      DataSource.updateAccountBalance(Id, newbalance);
    }
  }
}
