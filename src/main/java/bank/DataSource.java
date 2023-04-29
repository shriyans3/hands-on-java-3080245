package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect(){
    String file = "jdbc:sqlite:resources/bank.db";
    Connection con = null;
    try {
      con = DriverManager.getConnection(file);
      System.out.println("Connected Successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return con;
  }
  
  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer cus = null;
    try(Connection con = connect();
        PreparedStatement stmt = con.prepareStatement(sql)){
      stmt.setString(1,username);
      try(ResultSet resultSet= stmt.executeQuery()){
        cus = new Customer(resultSet.getInt("id"), 
        resultSet.getString("name"), 
        resultSet.getString("username"),
        resultSet.getString("password"),
        resultSet.getInt("account_id"));
      }
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    return cus;
  }

  public static Account getAccount(int account_id){
    String sql1 = "select * from accounts where id = ?";
    Account acc = null;
    try(Connection con = connect();
        PreparedStatement stmt1 = con.prepareStatement(sql1)){
          stmt1.setInt(1,account_id);
          try(ResultSet result = stmt1.executeQuery()){
            acc = new Account(result.getInt("id"), 
            result.getString("type"), 
            result.getInt("balance"));
        }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return acc;
  }
  public static void main(String[] args){
    Customer customer = getCustomer("bsuddock1z@oaic.gov.au");
    System.out.println(customer.getName());
    Account account = getAccount(customer.getAccount_id());
    System.out.println(account.getBalance());
  }
}
