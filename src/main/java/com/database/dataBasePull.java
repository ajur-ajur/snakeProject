package com.database;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class dataBasePull {
    private String DRIVER;
    private String URL;
    private String USER;
    private String PASS;
    private String NAME;

    static final int X_WIDTH = 300; 
    static final int Y_HEIGT = 400;

    public void dbConn() {
        System.out.println("Masuk...");
        DRIVER = "com.mysql.cj.jdbc.Driver";
        NAME = "orayorayan";
        URL = "jdbc:mysql://localhost/" + NAME;
        USER = "root";
        PASS = "";
        dbConn(URL, USER, PASS, NAME);
    }

    public void dbConn(String URL, String USER, String PASS, String NAME) {
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
        this.NAME = NAME;
        connectDatabase();
    }

    public void connectDatabase() {

        String collumns[] = {"ID", "USERNAME", "SCORE"};
        String data[][] = new String[20][3];

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * from orayorayan ORDER by score DESC";
            rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id_player");
                String id_str = String.valueOf(id);
                int score = rs.getInt("score");
                String score_str = String.valueOf(score);
                String user = rs.getString("user_name");
                data[i][0] = id_str;
                data[i][1] = user;
                data[i][2] = score_str;
                i++;
            }
            JTable tab = new JTable(data, collumns);
            tab.setBounds(30, 40, X_WIDTH - 100, Y_HEIGT - 100);
            JScrollPane sp = new JScrollPane(tab);
            JFrame frame = new JFrame("High Score");
            frame.setSize(X_WIDTH, Y_HEIGT);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.add(sp);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
