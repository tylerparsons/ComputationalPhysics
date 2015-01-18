package csm.exercises.ch03;

import java.util.HashMap;

import org.opensourcephysics.frames.PlotFrame;

public class NewtonCooling {

/********
 * Main *
 ********/
	
	public static void main(String[] args) {
		
		HashMap<String, Double> params = new HashMap<String, Double>();
		
		params.put("T_0", 100.0);
		params.put("t_s", 50.0);
		params.put("R", 0.1);
		params.put("dt", 0.01);
		
		// Print initial params
		System.out.println("Initial parameters:");
		for (String key: params.keySet())
			System.out.println(key+":\t"+params.get(key));
		
		// Construct ode, solver
		NewtonCoolingODE coffeeCup = new NewtonCoolingODE(
			params.get("T_0"),
			params.get("t_s"),
			params.get("R")
		);
		Euler solver = new Euler(coffeeCup, 0.1);
		
		// Construct PlotFrame to graph T vs t
		PlotFrame graph = new PlotFrame("Temperature vs. Time", "t", "T");
		graph.setVisible(true);
		
		double t = 0;
		while(t < 10) {
			solver.step();
			graph.append(0, t, coffeeCup.getState()[0]);
			t += 0.01;
		}
		
		// Print final state
		double[] state = coffeeCup.getState();
		System.out.println("Final state:");	
		System.out.println("T:\t"+state[0]);
		System.out.println("t:\t"+state[1]);
		
	}
	
}
