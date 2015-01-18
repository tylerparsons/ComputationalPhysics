package csm.exercises.ch03;

import org.opensourcephysics.numerics.ODE;

public class EulerRichardson extends Euler {

	private double dt;
	
	private double[] state;
	private double[] rate;
	
	public EulerRichardson(ODE eq, double size) {
		super(eq, size);
	}
	
	@Override
	public double step() {
		int i;
		double rate_mid;
		state[0] = state[0] + rate[0]*dt;
		for(i = 1; i < state.length - 1; i++) {
			//Step all physical states using EulerRichardson
			rate_mid = rate[i] - 0.5*rate[(i-1)]*dt;	//go back 1/2 step to find rate_mid
			state[i] = state[i] + rate_mid*dt;
		}
		// Step time
		state[i] = state[i] + rate[i]*dt;
		return state[i];	//current time
	}

}
