package com.game;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class gameOver implements ActionListener {

    private String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String NAME = "orayorayan";
    private String URL = "jdbc:mysql://localhost/" + NAME;
    private String USER  = "root";
    private String PASS = "";
    
    JFrame over = new JFrame();
    public JButton yes, no;
    public JTextField username;
    public JLabel input, question;
    public String uName = "";
    public int scoreUP;

    interface overPopUp{
        public void loadButtons();
        public void gameOver();
        public void setName();
        public void actionPerformed();
    }

    gameOver(int score){
        scoreUP = score;
        over.setLayout(null);
        over.setSize(300, 200);
        over.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        over.setVisible(true);
        over.setResizable(false);
        over.setTitle("Game Over!");
        loadButtons();
        setName();
    }

    public void loadButtons(){
        //Declare buttons
        no = new JButton("NO");
        yes = new JButton("YES");

        //Set posisi
        no.setBounds(30, 100, 100, 30);
        yes.setBounds(140, 100, 120, 30);

        //ActionListener
        yes.addActionListener(this);
        no.addActionListener(this);

        //Panggil
        over.add(no);
        over.add(yes);
    }

    public void setName(){
        input = new JLabel("Insert Username: ");
        input.setBounds(30, 10, 200, 40);

        username = new JTextField();
        username.setBounds(30, 45, 230, 20);

        question = new JLabel("Save Score? ");
        question.setBounds(30, 60, 200, 40);

        over.add(input);
        over.add(question);
        over.add(username);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == yes){
            uName = username.getText();

            Connection conn = null;

            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PASS);
                String sql = "INSERT INTO orayorayan VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setNull(1, Types.NULL);
                pstmt.setString(2, uName);
                pstmt.setInt(3, scoreUP);
                pstmt.executeUpdate(); //mangstab
            } catch (Exception er) {
                er.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Saved!");
            over.dispose();
        }
        else if(e.getSource() == no){
            over.dispose();
        }
    }
}
