package com.dataBase;

import java.sql.*;

public class runConnection {
    public static void main(String[] args){
        dataBasePull db = new dataBasePull();
        db.dbConn();
    }
}
