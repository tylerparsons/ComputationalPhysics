package csm.exercises.ch03;

import org.opensourcephysics.tools.ResourceLoader;
import org.opensourcephysics.frames.PlotFrame;

public class CoffeeFilter  {
	
	private PlotFrame graph;
	
	private double[] y;
	private double[] v;
	private double[] a;
	private double[] t;
	
	private double v_term;
	
	final static double g = -9.8067;
	final static double NaN = -1;
	
	public CoffeeFilter() {
		init();
	}
	
	private void init() {
		
		String data = ResourceLoader.getString("/org/opensourcephysics/sip/ch03/falling.txt");
		String[] lines = data.split("\n");
		
		int length = lines.length;
		int offset = 0;
		String[] dataPoint;
		graph = new PlotFrame("v(t)", "a(t)", "a vs. v");
		graph.setDefaultCloseOperation(PlotFrame.EXIT_ON_CLOSE);		
		
		//Count comment lines
		for(int i = 0; i < length; i++) {
			if(lines[i].startsWith("//")) {
				offset++;
				continue;
			}
		}
		//Instantiate arrays to correct length
		y = new double[length-offset];
		v = new double[length-offset];
		a = new double[length-offset];
		t = new double[length-offset];
		
		//Populate arrays
		offset = 0;
		for(int i = 0; i < length; i++) {
			if(lines[i].startsWith("//")) {
				offset++;
				continue;
			}
			dataPoint = lines[i].split("\t");
			t[i-offset] = Double.parseDouble(dataPoint[0]);
			y[i-offset] = Double.parseDouble(dataPoint[1]);
			v[i-offset] = calculateV(i-offset);
			a[i-offset] = calculateA(i-offset);
		}		
	}

	public void plot() {
		graph.setAutoscaleX(true);
		graph.setAutoscaleY(true);
		
		for(int i = 0; i < v.length; i++)
			if (!(v[i] == NaN || a[i] == NaN))
				graph.append(i, v[i], a[i]);
		
		graph.setVisible(true);
	}
	
	public void printResults() {
		System.out.println("Time:\n------------");
		print(t);
		System.out.println("Postion:\n------------");
		print(y);
		System.out.println("Velocity:\n------------");
		print(v);
		System.out.println("Acceleration:\n------------");
		print(a);
		System.out.println("Terminal Velocity:\n------------");
		calculateVTerm();
		System.out.println(v_term);
	}
	
	public void calculateVTerm() {
		int n = 0, V = 0;
		for(int i = 0; i < v.length; i++)
			if (!(v[i] == NaN || a[i] == NaN)) {
				V += g * (v[i]/a[i]);
				n++;
			}
		v_term = V/n;
	}
	
	private double calculateV(int i) {
		if (i == 0 || i == v.length-1)	//Cannot compute instantaneous v at t = 0 or t = t_f, do nothing
			return NaN;
		else		//central difference approximation
			return (y[i+1] - y[i-1])/(t[i+1] - t[i-1]);
	}

	private double calculateA(int i) {
		if (i == 0 || i == a.length-1)	//Cannot compute instantaneous a at t = 0 or t = t_f, do nothing
			return NaN;
		else	//central difference approximation using y (most accurate)
			return (y[i+1] - y[i])/((t[i+1] - t[i])*(t[i+1] - t[i])) - (y[i] - y[i-1])/((t[i+1] - t[i])*(t[i] - t[i-1]));
	}
	
	public void print(double[] arr) {
		for(double d: arr)
			if (d != NaN)
				System.out.println(d);
	}
	
/********
 * Main *
 ********/
	
	public static void main(String[] args) {
		
		CoffeeFilter cf = new CoffeeFilter();
		cf.plot();
		cf.printResults();
		
	}
	
}
