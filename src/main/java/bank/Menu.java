package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  private Scanner scanner ;
  
  public static void main(String[] args){
    System.out.println("Welcome to the Iron Bank!");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();
    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccount_id());
      menu.showMenu(customer,account);
    }
    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Enter your username :");
    String username = scanner.next();

    System.out.println("Enter your password :");
    String password = scanner.next();

    Customer cust = null;
    try{
        cust = Authenticator.login(username, password);
    }
    catch(LoginException e){
      e.printStackTrace();
    }
    return cust;
  }

  private void showMenu(Customer customer,Account account){
    int selection = 0;

    while(selection != 4 && customer.isAuthenticate()){
      System.out.println("------------------------------------");
      System.out.println("Enter one of the following options :");
      System.out.println("1 : Deposit");
      System.out.println("2 : WithDraw");
      System.out.println("3 : CheckBalance");
      System.out.println("4 : Exit");
      System.out.println("------------------------------------");

      selection = scanner.nextInt();
      double amount = 0;
      switch(selection){
        case(1):
          System.out.println("Enter Deposit Amount");
          amount = scanner.nextDouble();
          try{
            account.deposit(amount);
          }
          catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
          break;
        case(2):
          System.out.println("Enter Amount to withdraw");
          amount = scanner.nextDouble();
          try{
            account.withdraw(amount);
          }
          catch(AmountException e){
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
          }
          break;
        case(3):
          System.out.println("Your current balance is "+account.getBalance());
          break;
        case(4):
          Authenticator.logout(customer);
          System.out.println("Thank you for your business");
          break;
        default:
          System.out.println("Invalid option.Please try again");
          break;
      }
    }
  }
}
