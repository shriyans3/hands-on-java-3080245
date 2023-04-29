package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner ;
  
  public static void main(String[] args){
    System.out.println("Welcome to the Iron Bank!");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();
    if(customer != null){
      Account account = DataSoucre.getAccount(customer.getAccount_id());
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
}
