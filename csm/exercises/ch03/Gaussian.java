package csm.exercises.ch03;

import org.opensourcephysics.numerics.Function;
import org.opensourcephysics.frames.PlotFrame;
import java.lang.Math;

// f(x) = ae^(-bx^2)
public class Gaussian implements Function {

	private double a, b;
	
	public Gaussian(double A, double B) {
		a = A;
		b = B;
	}
	
	public double evaluate(double x) {
		return a* Math.pow(Math.E, (-1*b*x*x));
	}
	
	public void plotFunction(double xmin , double xmax) {
		PlotFrame frame = new PlotFrame ( "x" , "y" , "Function" ) ;
		double n = 100; // number of points
		double x = xmin , dx = (xmax - xmin ) / ( n-1);
		for ( int i = 0 ; i < 100; i++) {
			frame.append( 0 , x , evaluate(x)) ;
			x += dx ;
		}
		frame.setVisible(true);
	}
	
	public double derivative(double x, double dx) {
		return (evaluate(x+dx)-evaluate(x))/dx;
	}
}
