package degianmind.pkg0_lab2;

import java.util.Random;
import java.awt.*;

public class quakeStr {
	float x, y;
	String str;
	static float dt;
	Color col;
	boolean shake, stop;

	public boolean check (int xt, int yt) {
		if ( (xt > x) & (xt < x + str.length() *8) )
			if( (yt < y + 5) & (yt > y - 15) )
				return true;
		return false;
	}

	public boolean delstr (int xt, int yt, Graphics g) {
		if (check(xt, yt)) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(g2.getBackground());
			g2.drawString(str, x, y);
			return true;
		}
		return false;
	}

	public quakeStr (String str, int x, int y) {
		Random rand = new Random();
		shake = true;
		this.x = x;
		this.y = y;
		this.str = str;
		dt = 500;
		stop = false;
		col = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}

	private void kordnew () {
		Random rand = new Random();
		if (stop) return;
			if (shake) { 
				y += rand.nextInt(5);
				shake = !shake;
			}
		else{
			y -= rand.nextInt(5);
			shake = !shake;
		}
	}

	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(g2.getBackground());
		g2.drawString(str, x, y);
		kordnew();
		g2.setColor(col);
		g2.drawString(str, x, y);
	}

	public void run (Graphics g) {
		paint(g);
	}
}
