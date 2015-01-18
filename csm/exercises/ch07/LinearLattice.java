package csm.exercises.ch07;

import java.lang.Math;

public class LinearLattice {

	int A, B, L, N;
	double p; //probability of moving left

	public void step() {
		
		if(Math.random() > p) {
			//move left
			if(A != 0)
				A--;
			if(B != 0)
				B--;
		} else {
			if(A != L-1)
				A++;
			if(B != L-1)
				B++;
		}
		N++;
		
	}
	
	public static void main(String[] args) {
		
		int l = 500, m = 200;
		LinearLattice line = new LinearLattice();
		line.L = l;
		line.p = 0.5;
		
		long SmSl_n = 0;
		long SmSl_n2 = 0;
		
		for(int i = 0; i < m; i++) {
			line.A = (int)(Math.random()*l);
			line.B = (int)(Math.random()*l);
			line.N = 0;
			
			while (line.A != line.B) {
				line.step();
			}
			SmSl_n += line.N;
			SmSl_n2 += line.N*line.N;
		}
		
		//System.out.println("Average number of steps for A and B to meet:");
		System.out.println("\tm = "+ m);
		System.out.println("\t1 = "+ l);
		System.out.println("\t<N> = " + ((double)SmSl_n)/((double)(m*l)));
		System.out.println("\t<N^2> = " + ((double)SmSl_n2)/((double)(m*l)));
		
	}
	
}
