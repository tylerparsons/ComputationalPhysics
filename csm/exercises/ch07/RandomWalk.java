package csm.exercises.ch07;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class RandomWalk extends AbstractSimulation {

	PlotFrame graph;
	PlotFrame picture;
	SquareLattice walk;
	
	protected void doStep() {
		walk.step();
	}
	
	public void initialize() {
		picture.clearDrawables();
		picture.addDrawable(walk);
		
		walk.M = (int) control.getDouble("M");
		walk.L = (int) control.getDouble("L");
		walk.H = (int) control.getDouble("H");
		
		walk.init();
		picture.setPreferredMinMax(0, walk.L, 0, walk.H);
	}
	
	public void reset() {
		control.setValue("M", 10);
		control.setValue("L", 100);
		control.setValue("H", 100);
		enableStepsPerDisplay(true);
	}
	
	public void stopRunning() {
		control.println("N = " + walk.N);
		control.println("<x> = " + walk.xavg());
		control.println("<y> = " + walk.yavg());
		control.println("Dx2 = " + walk.Dx2());
		control.println("Dy2 = " + walk.Dy2());
		control.println("R2 = " + walk.R2());
	}
	
	public RandomWalk() {
		walk = new SquareLattice();
		picture = new PlotFrame("x", "y",
				"Random Walk of M actors");
	}

	public static void main(String[] args) {
		SimulationControl.createApp(new RandomWalk());
	}
	
}
