package csm.exercises.ch03;

import java.awt.Color;
import java.awt.Graphics;

import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.numerics.ODE;

public class ChargedParticle implements ODE, Drawable {

	private double E, B;
	private double[] state = new double[5];
	
	public void setState(double x, double vx, double y, double vy, double t) {
		state[0] = x;
		state[1] = vx;
		state[2] = y;
		state[3] = vy;
		state[4] = t;
	}
	
	public void setParams(double e, double b) {
		E = e;
		B = b;
	}

	public void getRate(double[] state, double[] rate) {
		rate[0] = state[1];			// x' = vx
		rate[1] = state[3]*B;		// x'' = vyB
		rate[2] = state[3];			// y' = vy
		rate[3] = E - state[1]*B;	// y'' = E - vyB
		rate[4] = 1;				// t
	}

	public double[] getState() {
		return state;
	}
	
	public void draw(DrawingPanel dp, Graphics g) {
		// particle
		int x = dp.xToPix(state[0]);
		int y = dp.yToPix(state[2]);
		g.setColor(Color.RED);
		g.fillOval(x - 2, y - 2, 4, 4);
		g.setColor(Color.BLACK);
		// x-axis
		g.drawLine( dp.xToPix(-50), dp.yToPix(0),
					dp.xToPix(50), dp.yToPix(0));
		// y-axis
		g.drawLine( dp.xToPix(0), dp.yToPix(-50),
					dp.xToPix(0), dp.yToPix(50));
	}

}
