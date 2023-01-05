package com.game;

import javax.swing.JFrame;

public class gameFrame{
	JFrame frame = new JFrame();
	gameFrame() {
		frame.add(new gamePanel());
		frame.setTitle("Snake");
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}