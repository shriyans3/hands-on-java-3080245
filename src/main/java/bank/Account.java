package bank;

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

}
