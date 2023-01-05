package com.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
	static final int DELAY = 140;
	static final String DIRECTORY = "src/main/icons/"; //kalau manggil tinggal kasih +filename
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 3;
	int foodEaten;
	int score;

	// types of food
	int appleX, appleY;
	int pineappleX, pineappleY;
	int bananaX, bananaY;
	int starX, starY;

	int rng;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	//image
	public Image head;
	public Image body;
	public Image tail;
	public Image apple;
	public Image pineapple;
	public Image banana;
	public Image star;
	public Image lose;

	gamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		loadimages();
		startGame();
	}

	public void loadimages() {
		ImageIcon iih = new ImageIcon(DIRECTORY + "head300.png");
		head = iih.getImage();
		ImageIcon iib = new ImageIcon(DIRECTORY + "body300.png");
		body = iib.getImage();
		ImageIcon app = new ImageIcon(DIRECTORY + "apple300.png");
		apple = app.getImage();
		ImageIcon pap = new ImageIcon(DIRECTORY + "pineapple300.png");
		pineapple = pap.getImage();
		ImageIcon ban = new ImageIcon(DIRECTORY + "banan300.png");
		banana = ban.getImage();
		ImageIcon str = new ImageIcon(DIRECTORY + "star300.png"); 
		star = str.getImage();
	}

	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {
			// for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
			// 	g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
			// 	g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			// }

			g.drawImage(apple, appleX, appleY, UNIT_SIZE, UNIT_SIZE, this);
			g.drawImage(pineapple, pineappleX, pineappleY, UNIT_SIZE, UNIT_SIZE, this);
			g.drawImage(banana, bananaX, bananaY, UNIT_SIZE, UNIT_SIZE, this);
			g.drawImage(star, starX, starY, UNIT_SIZE, UNIT_SIZE, this);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.drawImage(head, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
				} else {
					g.drawImage(body, x[i], y[i], UNIT_SIZE, UNIT_SIZE, this);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.BOLD, 20));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + foodEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, g.getFont().getSize());
		} else {
			gameOver(g);
		}

	}

	public void randomNumber() {
		newApple();
		Random r = new Random();
		int result = r.nextInt(9);
		if (result <= 4 && result >= 0) {
			newPineapple();
		} else if (result <= 7 && result >= 5) {
			newbanana();
		} else{
			newStar();
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
		//set other entity oob
		pineappleX = 100*UNIT_SIZE;
		pineappleY = 100*UNIT_SIZE;
		bananaX = 100*UNIT_SIZE;
		bananaY = 100*UNIT_SIZE;
		starX = 100*UNIT_SIZE;
		starY = 100*UNIT_SIZE;
	}

	public void newPineapple() {
		pineappleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		pineappleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
		//set other entity oob
		appleX = 100*UNIT_SIZE;
		appleY = 100*UNIT_SIZE;
		bananaX = 100*UNIT_SIZE;
		bananaY = 100*UNIT_SIZE;
		starX = 100*UNIT_SIZE;
		starY = 100*UNIT_SIZE;
	}

	public void newbanana() {
		bananaX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		bananaY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
		//set other entity oob
		appleX = 100*UNIT_SIZE;
		appleY = 100*UNIT_SIZE;
		pineappleX = 100*UNIT_SIZE;
		pineappleY = 100*UNIT_SIZE;
		starX = 100*UNIT_SIZE;
		starY = 100*UNIT_SIZE;
	}

	public void newStar() {
		starX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		starY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
		//set other entity oob
		appleX = 100*UNIT_SIZE;
		appleY = 100*UNIT_SIZE;
		pineappleX = 100*UNIT_SIZE;
		pineappleY = 100*UNIT_SIZE;
		bananaX = 100*UNIT_SIZE;
		bananaY = 100*UNIT_SIZE;
	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;
		}

	}

	public void checkFood() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			foodEaten++;
			randomNumber();
		}
		else if ((x[0] == pineappleX) && (y[0] == pineappleY)){
			bodyParts++;
			foodEaten = foodEaten + 2;
			randomNumber();
		}
		else if ((x[0] == bananaX) && (y[0] == bananaY)){
			bodyParts++;
			foodEaten = foodEaten + 3;
			randomNumber();
		}
		else if ((x[0] == starX) && (y[0] == starY)){
			bodyParts++;
			foodEaten = foodEaten + 5;
			randomNumber();
		}
	}

	public void checkCollisions() {
		// checks if head collides with body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// check if head touches left border
		if (x[0] < 0) {
			running = false;
		}
		// check if head touches right border
		if (x[0] >= SCREEN_WIDTH) {
			running = false;
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if (y[0] >= SCREEN_HEIGHT) {
			running = false;
		}

		if (!running) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {
		// Score
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + foodEaten)) / 2, g.getFont().getSize());
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Helvetica", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
		new gameOver(foodEaten);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (direction != 'R') {
						direction = 'L';
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (direction != 'L') {
						direction = 'R';
					}
					break;
				case KeyEvent.VK_UP:
					if (direction != 'D') {
						direction = 'U';
					}
					break;
				case KeyEvent.VK_DOWN:
					if (direction != 'U') {
						direction = 'D';
					}
					break;
			}
		}
	}
}