package csm.exercises.ch03;

public class EulerRichardsonFreeFall /*extends org.opensourcephysics.sip.ch02.FallingParticle*/ {

	double v_mid;
	double y;
	double y0;
	double t;
	double v;
	double v0;
	
	final double dt = 0.08;
	final double g = 9.8;
	
	public EulerRichardsonFreeFall(double V0, double Y0) {
		v0 = V0;
		y0 = Y0;
		v = v0;
		y = y0;
	}
	
	//Euler-Richardson Algorithm
	public void step() {
		v_mid = v - 0.5*g*dt;
		v = v - g*dt;
		y = y + v_mid*dt;
		t = t + dt;
	}
	
	public double y(double _t) {
		return y0 + v0*_t - (g/2)*_t*_t;
	}
	
	public double v(double _t) {
		return v0 - g*_t;
	}
	
	public void run() {
		while (y>0) {
			
			System.out.println( "\n------------------------------" +
								"\nt: " + t +
								"\n------------------------------" +
								"\ny: " + y + 
								"\nv: " + v +
								"\n------------------------------" +
								"\nAnalytical y = " + y(t) +
								"\nAnalytical v = " + v(t) +
								"\n------------------------------" + 
								"\nError in y = " + (y - y(t)) +
								"\nError in v = " + (v - v(t)) +
								"\n------------------------------");
			step();
		}
	}

}
