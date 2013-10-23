package degianmind.pkg0_lab2;

import java.util.Random;
import java.awt.*;

public class flyingMonster {
	float x, y;
	int dx, dy, numS, radius, iter;
	static float dt;
	boolean stop;
	Color col;
	Polygon kord = new Polygon();

	public boolean check (int xt, int yt) {
		return (Math.pow((x-xt),2) + Math.pow((y - yt), 2)) <= Math.pow(radius, 2); 
	}

	public boolean delMonster (int xt, int yt, Graphics g) {
		if (check(xt,yt)) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(g2.getBackground());
			g2.fillPolygon(kord);
			return true;
		}
		return false;
	}

	public flyingMonster (int numS, int x, int y) {
		Random rand = new Random();
		this.x = x;
		this.y = y;
		this.numS = numS;
		radius = rand.nextInt(10) + 15;
		dx = rand.nextInt(10) - 5;
		dy = (int) ((5 - Math.abs(dx)) * Math.pow((-1), rand.nextInt(2)));
		iter = 0;
		dt = 500;
		col = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		stop = false;
		collision();
		kordnew();
	}

	private void kordnew () {
		int xk, yk;
		double b;

		kord.reset();
		if (iter < 0) {
			if ( x < (radius+1) || x > (-radius+599) ) {
				dx *= -1;
			} else {dy *= -1;}
			collision();
		}
		for (int i = 0; i < numS; i++) {
			b = 2 * Math.PI * i / numS;
			xk = (int) (x + radius * Math.cos(i * 2 * Math.PI / numS));
			yk = (int) (y + radius * Math.sin(i * 2 * Math.PI / numS));
			kord.addPoint(xk, yk);
		}
		iter--;
	}

	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(g2.getBackground());
		g2.fillPolygon(kord);
		g2.setColor(col);
		kordnew();
		g2.fillPolygon(kord);
		
	}

	private void repaint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(g2.getBackground());
		g2.fillPolygon(kord);
		g2.setColor(col); 
		g2.fillPolygon(kord);
	}

	public void run (Graphics g) {
		float dt;
		if (stop) {
			repaint(g);
			return;
		}
		dt = this.dt / 1000;
		x += dx * dt;
		y += dy * dt;
		paint(g);
	}

	private void collision () {
		float dt;
		dt = this.dt /1000;
		int lx = -1, ly = -1;
		if (dx != 0) {
			if (dx < 0) { 
				lx = (int) ((x - radius) / Math.abs(dx * dt));
			}
			else {
				lx = (int) ((600 - x - radius) / Math.abs(dx * dt));
			}
		}
		if (dy != 0) {
			if (dy < 0) { 
				ly = (int) ((y - radius) / Math.abs(dy * dt));
			}
			else {
				ly = (int)((332 - y - radius) / Math.abs(dy * dt));
			}
		}
		if (lx < 0 || lx > ly) {iter = ly;}
		if (ly < 0 || ly > lx) {iter = lx;}
	}
}