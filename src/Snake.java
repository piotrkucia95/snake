import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{
	static Snake snake;
	JFrame frame;
	RenderPanel renderPanel;
	Timer timer;
	ArrayList<Point> snakeParts;
	Point head;
	Point cherry;
	int ticks = 0;
	int direction = -1;
	int points = 0;
	int tailLength = 0;
	int time = 0;
	int difficulty = 2;
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int scale = 10;
	Random random;
	boolean over = false;
	boolean pause = false;
	public Dimension dim;
	
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 597);
		frame.setLocation(dim.width/2-frame.getWidth()/2,  dim.height/2-frame.getWidth()/2);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setResizable(false);
		renderPanel = new RenderPanel();
		frame.add(renderPanel);
		snakeParts = new ArrayList<Point>();
		timer = new Timer(20, this);
		startGame();
		Sound.sound1.loop();
	}

	public void startGame() {
		dim = new Dimension(58,54);
		over = false;
		points = 0;
		tailLength = 0;
		time = 0;
		pause = false;
		direction = -1;
		head = new Point(dim.width/2, dim.height/2);
		random = new Random();
		cherry = new Point((random.nextInt(dim.width)), (random.nextInt(dim.width)));
		snakeParts.clear();	
		for (int i=0; i<tailLength; i++) {
			snakeParts.add(new Point(head.x, head.y));
		}
		timer.start();
	}
	
	public void tailBit() {
		for (int i =1; i<snakeParts.size(); i++) {
			if (head.equals(snakeParts.get(i))){
				over = true;
				Sound.sound1.stop();
				Sound.sound2.play();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderPanel.repaint();
		ticks++;
		if((ticks % difficulty == 0) && (head != null) && (over != true) && (pause != true)) {
			snakeParts.add(new Point(head.x, head.y));
			time++;
			if(direction == UP) {
				if (head.y-1 >= 0) {
					head = new Point(head.x, head.y-1);
				} else {
					over = true;
					Sound.sound1.stop();
					Sound.sound2.play();
				}
			} else if(direction == RIGHT) {
				if (head.x <= (dim.width)) {
					head = new Point(head.x+1, head.y);
				} else {
					over = true;
					Sound.sound1.stop();
					Sound.sound2.play();
				}
			} else if(direction == DOWN) {
				if (head.y <= (dim.height)) {
					head = new Point(head.x, head.y+1);
				} else {
					over = true;
					Sound.sound1.stop();
					Sound.sound2.play();
				}
			} else if(direction == LEFT) {
				if (head.x-1 >= 0) {
					head = new Point(head.x-1, head.y);
				} else {
					over = true;
					Sound.sound1.stop();
					Sound.sound2.play();
				}
			}
			tailBit();

			if (snakeParts.size() > tailLength) {
				snakeParts.remove(0);
			}
			if (head.equals(cherry)) {
				points+=10;
				tailLength++;
				cherry.setLocation((random.nextInt(58)), (random.nextInt(56)));
				Sound.sound3.play();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if (i==KeyEvent.VK_UP && direction !=DOWN) {
			direction=UP;
		}
		if (i==KeyEvent.VK_RIGHT && direction != LEFT) {
			direction=RIGHT;
		}
		if (i==KeyEvent.VK_DOWN && direction != UP) {
			direction=DOWN;
		}
		if (i==KeyEvent.VK_LEFT && direction != RIGHT) {
			direction=LEFT;
		}
		if (i==KeyEvent.VK_ENTER &&  over == true) {
			Sound.sound1.play();
			startGame();
		}
		if (i==KeyEvent.VK_SPACE) {
			if (Sound.sound1.isActive()) {
				Sound.sound1.pause();
			} else {
				Sound.sound1.startAfterPause();
			}
			pause = !pause;
		}
		if (pause == true && (i == KeyEvent.VK_RIGHT || i == KeyEvent.VK_UP || i==KeyEvent.VK_DOWN ||i==KeyEvent.VK_LEFT)){
			if (Sound.sound1.isActive()) {
				Sound.sound1.pause();
			} else {
				Sound.sound1.startAfterPause();
			}
			pause = !pause;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
