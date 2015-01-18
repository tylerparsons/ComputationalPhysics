package csm.exercises.ch03;

import org.opensourcephysics.frames.PlotFrame;

public class SHO {

	// Constants
	private double k;
	private double m;
	private double w;
	// Variables
	private double x;
	private double t;
	private double v;
	private double E;
	// Graph
	private PlotFrame graph;
	
	public SHO(double K, double M) {
		k = K;
		m = M;
		w = Math.sqrt(k/m);
		init();
	}
	
	void init() {
		graph = new PlotFrame("Time t", "Position x", "Simple harmonic oscillator");
		graph.setVisible(true);
		graph.setDefaultCloseOperation(PlotFrame.EXIT_ON_CLOSE);
	}
	
	public void plotMotion(double x0, double t0, double v0) {
		
		x = x0;
		t = t0;
		v = v0;
		
		int index = 0;
	
		for (double dt = 0.1; t < 20; t = t + dt) {
			
			graph.append(index++, t, x);
			System.out.println( "\n------------------------------" +
								"\nt: " + t +
								"\n------------------------------" +
								"\nx: " + x + 
								"\nv: " + v +
								"\nE: " + E +
								"\n------------------------------" +
								"\nAnalytical x = " + x(t) +
								"\nAnalytical v = " + v(t) +
								"\nAnalytical E = " + E(v(t), x(t)) + 
								"\n------------------------------" + 
								"\nError in x = " + (x - x(t)) +
								"\nError in v = " + (v - v(t)) +
								"\nError in E = " + (E - E(v(t), x(t))) + 
								"\n------------------------------");
			
			//Analytical solutions x(t) = cos(wt), v(t) = -wsin(wt)
			//	-> satisfies initial conditions x0 = 1, v0 = 0
			v = v - (k/m)*x*dt;
			x = x + v*dt;
			E = E(v, x);
		}
	}
	
	public double x(double t) {
		return Math.cos(w*t);
	}
	
	public double v(double t) {
		return -1*w*Math.sin(w*t);
	}
	
	public double E(double _v, double _x) {
		return (m/2)*(_v*_v) + (k/2)*(_x*_x);
	}
	
}
