package com.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.jdbc.ScriptRunner;

public class runonlyonce {
   public static void main(String args[]) throws Exception {
      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
      //Getting the connection
      String mysqlUrl = "jdbc:mysql://localhost/orayorayan";
      Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
      System.out.println("Connection established......");
      //Initialize the script runner
      ScriptRunner sr = new ScriptRunner(con);
      //Creating a reader object
      Reader reader = new BufferedReader(new FileReader("ular/src/main/java/com/database/runonlyonce.sql"));
      //Running the script
      sr.runScript(reader);
   }
}
