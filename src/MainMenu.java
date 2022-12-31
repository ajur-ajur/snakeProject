package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener{
    public JFrame menuFrame;
    public JButton play, score, quit;
    public JLabel title;

    static final int SCREEN_HEIGHT = 500;
    static final int SCREEN_WIDTH = 500;

    MainMenu(){
        setLayout(null);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("Jangan baca aku mas");
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

        quit.addActionListener(this);

        //Panggil ke frame
        add(play);
        add(score);
        add(quit);
    }

    public void setJudul(){
        title = new JLabel("Ular - Ularan");
        title.setBounds(130, 100, 400, 100);
        title.setFont(new Font("Helvetica", Font.BOLD, 40));
        add(title);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == play){
            this.add(new GameFrame());
            System.exit(0);
        }
        // else if(e.getSource() == score){
        //     this.add(new HighScoreDB());
        //     System.exit(0);
        // }
        else if(e.getSource() == quit){
            System.exit(0);
        }
    }
}