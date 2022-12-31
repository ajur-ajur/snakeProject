package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends JFrame implements ActionListener {
    public JFrame overFrame;
    public JButton yes, no, upload;
    public JTextField username;
    public JLabel input, question;
    public String uName = "";

    GameOver(){
        setLayout(null);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("Game Over!");
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

        //Panggil
        add(no);
        add(yes);
    }

    public void setName(){
        input = new JLabel("Insert Username: ");
        input.setBounds(30, 10, 200, 40);

        username = new JTextField();
        username.setBounds(30, 45, 230, 20);

        question = new JLabel("Save Score? ");
        question.setBounds(30, 60, 200, 40);

        add(input);
        add(question);
        add(username);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == yes){
            uName = username.getText();
            JOptionPane.showMessageDialog(null, uName);
        }
    }
}
