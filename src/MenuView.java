import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuView {
	public static Snake snake;
	JFrame frame;
	JPanel panel;
	Dimension dim;
	ButtonGroup difficulty;
	JRadioButton difficulty1;
	JRadioButton difficulty2;
	JRadioButton difficulty5;
	JRadioButton difficulty10;
	JButton start;
	JButton quit;
	JLabel choose;
	JLabel notChoosen;
	
	public MenuView() {
		dim = new Dimension(0, 0);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 597);
		frame.setLocation(dim.width/2-frame.getWidth()/2,  dim.height/2-frame.getWidth()/2);
		frame.setVisible(true);
		frame.setResizable(false);
		dim = frame.getSize();
		panel = new JPanel();
		panel.setSize(dim);
		panel.setLocation(0, 0);
		panel.setLayout(null);
		difficulty = new ButtonGroup();
		difficulty1 = new JRadioButton("Hard");
		difficulty2 = new JRadioButton("Medium");
		difficulty5 = new JRadioButton("Easy");
		difficulty10 = new JRadioButton("Very Easy");
		choose = new JLabel("Difficulty level:");
		notChoosen = new JLabel("Choose difficulty!");
		
		ActionListener startGame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(difficulty.isSelected(null) == true) {
					notChoosen.setVisible(true);
				}
				else {
					snake = new Snake();
					notChoosen.setVisible(false);
					if(difficulty1.isSelected() == true) {
						snake.difficulty = 1;
					} else if(difficulty2.isSelected() == true) {
						snake.difficulty = 2;
					} else if(difficulty5.isSelected() == true) {
						snake.difficulty = 5;
					} else if(difficulty10.isSelected() == true) {
						snake.difficulty = 10;
					}		
				}
			}
		};
		ActionListener exit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		};
		
		choose.setSize(200, 20);
		choose.setLocation(30, 120);
		notChoosen.setSize(200,20);
		notChoosen.setLocation(30, 500);
		notChoosen.setVisible(false);
		difficulty.add(difficulty1);
		difficulty.add(difficulty2);
		difficulty.add(difficulty5);
		difficulty.add(difficulty10);
		difficulty1.setSize(100, 20);
		difficulty1.setLocation(50, 150);
		difficulty2.setSize(100, 20);
		difficulty2.setLocation(170, 150);
		difficulty5.setSize(100, 20);
		difficulty5.setLocation(290, 150);
		difficulty10.setSize(100, 20);
		difficulty10.setLocation(410, 150);
		start = new JButton("Start Game");
		start.setSize(300, 60);
		start.setLocation(dim.width/2-start.getWidth()/2, dim.height/2-start.getHeight()/2);
		start.addActionListener(startGame);
		quit = new JButton("Exit");
		quit.setSize(300, 60);
		quit.setLocation(dim.width/2-start.getWidth()/2, dim.height/2-start.getHeight()/2+100);
		quit.addActionListener(exit);
		
		panel.add(difficulty1);
		panel.add(difficulty2);
		panel.add(difficulty5);
		panel.add(difficulty10);
		panel.add(choose);
		panel.add(start);
		panel.add(quit);
		panel.add(notChoosen);
		frame.add(panel);
	}
	
}
