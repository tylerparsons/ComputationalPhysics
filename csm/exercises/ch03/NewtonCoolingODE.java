package csm.exercises.ch03;

import org.opensourcephysics.numerics.ODE;

public class NewtonCoolingODE implements ODE {

	private double[] state;
	private double T, t;
	private double T_s, r;
	
	public NewtonCoolingODE(double T_0, double t_s, double R) {
		T = T_0;
		t = 0;
		T_s = t_s;
		r = R;
		state = new double[2];
	}
	
	@Override
	public void getRate(double[] state, double[] rate) {
		rate[0] = -1* r*(T - T_s);
		rate[1] = 1;
	}

	@Override
	public double[] getState() {
		state[0] = T;
		state[1] = t;
		return state;
	}

}
