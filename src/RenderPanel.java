import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{	
	int curColor = 32896;
	int snakeColor = 13701441;
	int cherryColor = 5572881;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(curColor));
		g.fillRect(0, 0, 600, 600);		
		Snake snake = MenuView.snake;
		
		g.setColor(new Color(snakeColor));
		for(Point point : snake.snakeParts) {
			g.fillRect(point.x*Snake.scale, point.y*Snake.scale, Snake.scale, Snake.scale);
		}
		g.fillRect(snake.head.x*Snake.scale, snake.head.y*Snake.scale, Snake.scale, Snake.scale);
		
		g.setColor(new Color(cherryColor));
		g.fillRect(snake.cherry.x*Snake.scale, snake.cherry.y*Snake.scale, Snake.scale, Snake.scale);
		
		g.setColor(Color.white);
		String string = "Points: " + snake.points + ", Length: " + snake.tailLength + ", Time: " + snake.time /(40/snake.difficulty) +"s";
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);
		
		string = "Game Over! Press ENTER";
		if (snake.over == true) {
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight());
		}

		string = "Paused!";
		if (snake.pause == true && snake.over ==false) {
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight());
		}
	
	};
}
