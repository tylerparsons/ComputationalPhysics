package csm.exercises.ch07;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

import csm.exercises.ch07.PartitionedBox;

public class BoxEquilibrium extends AbstractSimulation {

	PlotFrame graph;
	PlotFrame picture;
	PartitionedBox box;
	
	protected void doStep() {
		box.step();
		graph.append(1, box.t, ((double)box.nLeft)/((double)box.N));
	}
	
	public void initialize() {
		
		picture.clearDrawables();
		picture.addDrawable(box);
		
		box.N = (int) control.getDouble("N");
		box.nLeft = box.N;
		box.R = (int) control.getDouble("Radius");
		box.x_part = control.getDouble("x_part");
		box.x_max = control.getDouble("x_max");
		box.y_max = control.getDouble("y_max");
		box.t = control.getDouble("t0");
		box.dt_dn = control.getDouble("dt_dn");
		
		box.init();
		
		picture.setPreferredMinMax(0, box.x_max, 0, box.y_max);
		graph.setPreferredMinMax(0, control.getDouble("t_max"), 0, 1);
		
	}
	
	public void reset() {
		control.setValue("N", 10);
		control.setValue("Radius", 2);
		control.setValue("x_part", 50);
		control.setValue("x_max", 100);
		control.setValue("y_max", 50);
		control.setValue("t_max", 300);
		control.setValue("t0", 0);
		control.setValue("dt_dn", 0.01);
		enableStepsPerDisplay(true);
	}
	
	public BoxEquilibrium() {	
		box = new PartitionedBox();
		picture = new PlotFrame("x", "y",
				"Gas molecules in a Partitioned Box");
		picture.addDrawable(box);
		
		graph = new PlotFrame("t", "nLeft/(N-nLeft)",
				"Proportion of molecules in left half to that of right");
		graph.setAutoscaleX(true);
		graph.setAutoscaleY(false);
	}
	
	public static void main(String[] args) {
		SimulationControl.createApp(new BoxEquilibrium());
	}

}
