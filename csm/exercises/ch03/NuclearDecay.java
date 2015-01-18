package csm.exercises.ch03;

import org.opensourcephysics.controls.AbstractCalculation;
import org.opensourcephysics.controls.CalculationControl;
import org.opensourcephysics.frames.PlotFrame;

import csm.exercises.ch03.Euler;

import java.lang.Math;

public class NuclearDecay extends AbstractCalculation {
	
	Euler solver;
	PlotFrame graph;
	NuclearDecayODE N;
	
	double dt;
	double lambda;
	double t;
	
	public void reset() {
		control.setValue("lambda", 1);
		control.setValue("dt", 0.01);
		control.setValue("t", 1);
	}

	public void calculate() {
		lambda = control.getDouble("lambda");
		dt = control.getDouble("dt");
		t = control.getDouble("t");
		N.reset(1, lambda);
		solver.setStepSize(dt);
		int i = 0;	//tracks data point index
		double T;
		for(T = 0; N.getState()[0] > 0.2; T += dt) {
			solver.step();
			//graph.append(i++, N.getState()[1],	//t
			//				  N.getState()[0]);	//N
		}
		control.println("Numerical = " + T);
		control.println("Analytic  = " + Math.log(5)/lambda);
	}
	
	public void init() {
		N = new NuclearDecayODE();
		solver = new Euler(N);
		graph = new PlotFrame("t", "N/N0", "");
		graph.setAutoscaleX(true);
		graph.setAutoscaleY(true);
		graph.setVisible(false);
	}
	
	public NuclearDecay() {
		init();
	}
	
	public static void main(String[] args) {
		CalculationControl.createApp(new NuclearDecay());
	}
	
}
