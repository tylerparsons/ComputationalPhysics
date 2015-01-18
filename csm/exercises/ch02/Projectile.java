package csm.exercises.ch02;

import java.lang.Math;

public class Projectile {
	
	private Vector r;
	private Vector v;
	final double g = 9.8;
	
	public Projectile() {
		init();
	}
	
	void init() {
		
		Vector r0 = new Vector(10 + Math.random() * 10, 10 + Math.random() * 10);		
		Vector v0 = new Vector(2 + Math.random() * 5, 2 + Math.random() * 5);
		
		r = new Vector(r0.x, r0.y);
		v = new Vector(v0.x, v0.y);
	}
	
	public void plotMotion(org.opensourcephysics.frames.PlotFrame graph){
		
		double dt = 0.1;
		double t = 0;
		int index = 0;
		
		while( r.y >= 0) {
			graph.append(index++, r.x, r.y);
			//x motion
			r.x = r.x + v.x*dt;
			//y motion
			v.y = v.y - g*dt;
			r.y = r.y + v.y*dt;
			t = t + dt;
			try {
				Thread.sleep((long)(dt*1000));
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
}
