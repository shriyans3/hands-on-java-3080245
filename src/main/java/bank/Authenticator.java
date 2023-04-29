package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  public static Customer login(String username,String password) throws LoginException{
    Customer customer = DataSource.getCustomer(username);
    if(customer == null){
      throw new LoginException("No customer is present"); 
    }
    if(password.equals(customer.getPassword())){
      customer.setAuthenticate(true);
      return customer;
    }
    else throw new LoginException("incorrect password");
  }
  
  public static void logout(Customer customer){
    customer.setAuthenticate(false);
  }
}
