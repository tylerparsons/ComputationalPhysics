package csm.exercises.ch07;

import java.awt.Color;
import java.awt.Graphics;

import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;

public class SquareLattice implements Drawable {

	public int x[], y[];
	// SmSn_x = Sum over m (Sum over n of x)
	public long SmSn_x, SmSn_y;
	public long SmSn_x2, SmSn_y2;
	
	public int M;		//number of walkers
	public int N;		//number of steps
	public int L, H;	//Length, height
	
	
	public SquareLattice() {
		M = 100;
		init();
	}
	
	public void init() {
		x = new int[M];
		y = new int[M];

		SmSn_x = 0;
		SmSn_y = 0;
		SmSn_x2 = 0;
		SmSn_y2 = 0;
		
		//place walkers at random initial locations
		for (int i = 0; i < M; i++) {
			x[i] = (int) (Math.random()*L);
			y[i] = (int) (Math.random()*H);
		}
		N = 0;
	}
	
	public void step() {
		//step all walkers
		for(int i = 0; i < M; i++) {
			
			int dx = (int) (Math.random()*4 - 1.9999);
			int dy = (int) (Math.random()*4 - 1.9999);
			
			//boundary check
			//dx, dy elem of -1, 0, 1
			//	--> P(0) = 2P(1) = 2P(-1)
			
			if ((x[i] + dx) < 0 || (x[i]+dx) >= L)
				dx = -dx;
			if ((y[i] + dy) < 0 || (y[i]+dy) >= H)
				dy = -dy;
			
			//step
			x[i] += dx;
			y[i] += dy;
			
			//Do statistics here
			SmSn_x += x[i];
			SmSn_x2 += x[i]*x[i];
			SmSn_y += y[i];
			SmSn_y2 += y[i]*y[i];
			
		}
		N++;
	}
	
	public double xavg () {
		return (double) (((double)SmSn_x)/((double)M*N));
	}
	public double yavg () {
		return (double) (((double)SmSn_y)/((double)M*N));
	}
	public double x2avg () {
		return (double) (((double)SmSn_x2)/((double)M*N));
	}
	public double y2avg () {
		return (double) (((double)SmSn_y2)/((double)M*N));
	}
	public double Dx2() {
		return x2avg() - xavg()*xavg();
	}
	public double Dy2() {
		return y2avg() - yavg()*yavg();
	}
	public double R2() {
		return Dx2() + Dy2();
	}

	public void draw(DrawingPanel dp, Graphics g) {
		
		g.setColor(Color.RED);
		for(int i = 0; i < M; i++) {
			g.fillRect( dp.xToPix(x[i]),
						dp.yToPix(y[i]),
						4, 4);
		}
		
	}

}
