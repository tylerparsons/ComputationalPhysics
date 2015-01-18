package csm.exercises.ch07;

import java.awt.Color;
import java.awt.Graphics;

import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;

import java.lang.Math;

public class PartitionedBox implements Drawable {

	public double[] x, y;
	public double x_max, x_part, y_max;
	public int R;	//Particle Radius
	public double t, dt_dn;
	public int N;
	public int nLeft;
	
	public PartitionedBox() {
		init();
	}
	
	public PartitionedBox(int n, int radius, double x_m, double y_m, double x_p, double t0, double dt_dn) {
		setParams(n, radius, x_m, y_m, x_p, t0, dt_dn);
		init();
	}
	
	public void setParams(int n, int radius, double x_m, double y_m, double x_p, double t0, double dtdn) {
		N = n;
		R = radius;
		nLeft = N;
		x_max = x_m;
		y_max = y_m;
		x_part = x_p;
		t = t0;
		dt_dn = dtdn;
	}

	public void init() {
		x = new double[N];
		y = new double[N];
		//Set initial positions
		for(int i=0; i<N; i++) {
			x[i] = Math.random() * x_part;
			y[i] = Math.random() * y_max;
		}
	}
	
	public void step() {
		int i = (int) (Math.random()*N);
		if(x[i] < x_part) {
			nLeft--;
			x[i] = Math.random()*(x_max - x_part) + x_part;
			y[i] = Math.random()* y_max;
			t += dt_dn;
		}
		else {
			nLeft++;
			x[i] = Math.random()*x_part;
			y[i] = Math.random()*y_max;
			t += dt_dn;
		}
	}
	
	public void draw(DrawingPanel dp, Graphics g) {
		
		//Draw Box
		g.setColor(Color.BLACK);
		
		int top = dp.yToPix(y_max);
		int bottom = dp.yToPix(0);
		int left = dp.xToPix(0);
		int right = dp.xToPix(x_max);
		int part = dp.xToPix(x_part);
		
		g.drawLine(left, bottom, left, top);
		g.drawLine(right, bottom, right, top);
		g.drawLine(left, bottom, right, bottom);
		g.drawLine(left, top, right, top);
		g.drawLine(part, top, part, bottom/2 + R);
		g.drawLine(part, bottom/2 - R, part, bottom);
		
		//Draw Particles
		g.setColor(Color.RED);
		
		for (int i = 0; i < x.length; i++) {
			g.fillOval( dp.xToPix(x[i]),
						dp.yToPix(y[i]),
						2*R, 2*R);
		}
	}
}
