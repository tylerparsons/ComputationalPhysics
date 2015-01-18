package csm.exercises.ch03;

import org.opensourcephysics.numerics.ODE;

public class NuclearDecayODE implements ODE {

	private double lambda;
	private double[] state = new double[2];
	
	public void reset (double n0, double l) {
		lambda = l;
		state[0] = n0;	//N
		state[1] = 0;	//t	
	}
	
	public void getRate(double[] state, double[] rate) {
		rate[0] = -1*lambda*state[0];
		rate[1] = 1;
	}

	public double[] getState() {
		return state;
	}

}
