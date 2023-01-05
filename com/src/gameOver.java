package com.src;

import javax.swing.*;
import java.awt.event.*;

public class gameOver implements ActionListener {
    JFrame over = new JFrame();
    public JButton yes, no;
    public JTextField username;
    public JLabel input, question;
    public String uName = "";
    int scoreUP;

    gameOver(int score){
        System.out.print(score);
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
            JOptionPane.showMessageDialog(null, "Saved!");
            over.dispose();
        }
        else if(e.getSource() == no){
            over.dispose();
        }
    }
}
