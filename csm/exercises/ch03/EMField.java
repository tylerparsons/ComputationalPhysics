package csm.exercises.ch03;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.controls.SimulationControl;

public class EMField extends AbstractSimulation {

	Euler solver;
	ChargedParticle r;
	PlotFrame graph;
	
	private double x0;
	private double vx0;
	private double y0;
	private double vy0;
	private double t0;
	private double dt;
	private double E;
	private double B;
	
	protected void doStep() {
		graph.append(0, r.getState()[0], r.getState()[2]);
		solver.step();
	}
	
	public void initialize() {
		
		x0 = control.getDouble("x0");
		vx0 = control.getDouble("vx0");
		y0 = control.getDouble("y0");
		vy0 = control.getDouble("vy0");
		t0 = control.getDouble("t0");
		dt = control.getDouble("dt");
		E = control.getDouble("E");
		B = control.getDouble("B");
		
		r.setState(x0, vx0, y0, vy0, t0);
		r.setParams(E, B);
		solver.setStepSize(dt);
		graph.setPreferredMinMax(-50, 50, -25, 25);
	}
	
	public void reset() {
		control.setValue("x0", 10);
		control.setValue("vx0", 0);
		control.setValue("y0", 10);
		control.setValue("vy0", 0);
		control.setValue("t0", 0);
		control.setValue("dt", 0.01);
		control.setValue("E", 1);
		control.setValue("B", 1);
		enableStepsPerDisplay(true);
	}
	
	public EMField() {
		r = new ChargedParticle();
		solver = new Euler(r);
		
		graph = new PlotFrame("y", "x",
				"Motion of a charged particle in an EM Field");
		graph.addDrawable(r);
	}
	
	public static void main(String[] args) {
		SimulationControl.createApp(new EMField());
	}

}
