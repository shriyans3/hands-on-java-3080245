package bank;

import java.nio.file.ProviderMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;

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
  
  public static void main(String[] args){
    connect();
  }
}
