package csm.exercises.ch03;

import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.ODE;

public class Euler implements ODESolver {

	private double stepSize;
	private ODE ode;
	
	private double[] rate;
	
	public Euler(ODE eq) {
		ode = eq;
		initialize(0.01);
	}
	
	public Euler(ODE eq, double size) {
		ode = eq;
		initialize(size);
	}
	
	public double getStepSize() {
		return stepSize;
	}

	public void initialize(double size) {
		stepSize = size;
		rate = new double[ode.getState().length];
		ode.getRate(ode.getState(), rate);
	}
	
	public void setStepSize(double size) {
		stepSize = size;
	}
	
	public double step() {
		int i;
		for(i = 0; i < ode.getState().length; i++) {
			ode.getState()[i] += rate[i]*stepSize;
			ode.getRate(ode.getState(), rate);
		}
		return ode.getState()[i-1];
	}

}
