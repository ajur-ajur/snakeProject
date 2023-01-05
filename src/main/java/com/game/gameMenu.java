package com.game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.database.dataBasePull;

public class gameMenu implements ActionListener{
    JFrame menu = new JFrame();
    public JButton play, score, quit;
    public JLabel title;

    static final int SCREEN_HEIGHT = 500;
    static final int SCREEN_WIDTH = 500;

    gamePanel panel = new gamePanel();

    gameMenu(){
        menu.setLayout(null);
        menu.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.setResizable(false);
        menu.setTitle("Jangan baca aku mas");
        setJudul();
        loadButtons();
    }

    public void loadButtons(){
        //Declare buttons
        play = new JButton("PLAY");
        score = new JButton("HIGH SCORE");
        quit = new JButton("QUIT");

        //Set posisi dan ukuran
        play.setBounds(195, 250, 105, 30);
        score.setBounds(195, 300, 105, 30);
        quit.setBounds(195, 350, 105, 30);

        //Hmmmmmm
        play.addActionListener(this);
        score.addActionListener(this);
        quit.addActionListener(this);

        //Panggil ke frame
        menu.add(play);
        menu.add(score);
        menu.add(quit);
    }

    public void setJudul(){
        title = new JLabel("Ular - Ularan");
        title.setBounds(130, 100, 400, 100);
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        menu.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == play){
            new gameFrame();
        }
        else if(e.getSource() == score){
            dataBasePull db = new dataBasePull();
            db.dbConn();
        }
        else if(e.getSource() == quit){
            System.exit(0);
        }
    }
}