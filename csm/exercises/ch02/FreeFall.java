package csm.exercises.ch02;

import org.opensourcephysics.frames.PlotFrame;

public class FreeFall {
	
	public static void main(String[] args) {
		
		PlotFrame graph = new PlotFrame("x(t)", "y(t)", "Motion of an object in free fall.");
		graph.showDataTable(true);
		graph.setVisible(true);
		graph.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		Projectile p = new Projectile();
		p.plotMotion(graph);
		
	}
	
}
