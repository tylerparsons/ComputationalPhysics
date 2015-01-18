package csm.exercises.ch07;
import java.awt.Graphics;

import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;

import csm.exercises.ch02.Vector;

import java.lang.Math;

public class PartitionedBox_v2 implements Drawable {

	private Vector[] v;
	private Vector[] r;
	private double x_max;
	private double x_part;
	private double y_max;
	private double y_slit;
	private int N;
	
	public PartitionedBox_v2(int n, double x_m, double x_p, double y_m, double y_s) {
		N = n;
		x_max = x_m;
		x_part = x_p;
		y_max = y_m;
		y_slit = y_s;
		init();
	}

	public void init() {
		
		v = new Vector[N];
		r = new Vector[N];
		//Set initial positions, velocities
		for(int i=0; i<N-1; i++) {
			v[i] = new Vector(Math.random() * (x_max/10), Math.random() * (y_max/10));
			r[i] = new Vector(Math.random() * (x_max - x_part), Math.random() * y_max);
		}
		
	}
	
	// Update position
	//	* If particle collides with a wall, richochet
	//		--> Only collide with partition outside of slit region
	public void step() {
		
	}
	
	public void draw(DrawingPanel dp, Graphics g) {
		
	}
}
