package com.src;

import javax.swing.*;
import java.sql.*;

public class hsTable {
    JFrame frame = new JFrame();

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    static final Integer X_WIDTH = 300;
    static final Integer Y_HEIGTH = 300;

    hsTable(){
        JTable tab = new JTable();
        tab.setBounds(30,40,X_WIDTH, Y_HEIGTH);
        JScrollPane sp = new JScrollPane();
        frame.add(sp);
        frame.setSize(X_WIDTH, Y_HEIGTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
