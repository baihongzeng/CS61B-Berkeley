public class NBody {
	/*get the radius of the universe, i.e. the drawing range for the map
	  the structure of 'file_name' can be found in /data/3body.txt
	*/

	public static void main (String[] args) {
		/*loading parameters*/
		double T = Double.parseDouble(args[0]); //total time
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);

		/*drawing the background*/
 		drawBackground(radius);
 		/* Draw Planets */
	    drawPlanets(allPlanets);
	    /* Show */
	    StdDraw.show();

	    /* Animations */
	    StdDraw.enableDoubleBuffering();//prevent flickering in the animation
	    double t = 0;
	    while (t < T) {
			/* calc forces */
			double[] xForces = new double[allPlanets.length];
			double[] yForces = new double[allPlanets.length];

			for (int i = 0; i < allPlanets.length; i++) {
				Planet p1 = allPlanets[i];
				xForces[i] = p1.calcNetForceExertedByX(allPlanets);
				yForces[i] = p1.calcNetForceExertedByY(allPlanets);
			}
		

			/* update on each of the planet's position, velocity, and acceleration.*/
			for (int i = 0; i < allPlanets.length; ++i) {
				Planet p = allPlanets[i];
				p.update(dt, xForces[i], yForces[i]);
				drawBackground(radius);
				drawPlanets(allPlanets);
				StdDraw.show();
				StdDraw.pause(1);
			}

			t += dt;
		}

		// print out the final states of all planets after reaching time T
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
		                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}

	public static double readRadius (String file_name) {
		In in = new In(file_name);

		/* Every time you call a read method from the In class,
		 * it reads the next thing from the file, assuming it is
		 * of the specified type. */
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();

		return secondItemInFile;
	}

	/* Given a file name, it should return an array of Planets 
	corresponding to the allPlanets in the file*/
	public static Planet[] readPlanets (String file_name) {
		In in = new In(file_name);

		int planet_num = in.readInt();
		Planet[] result = new Planet[planet_num];

		double universe_radius = in.readDouble();

		for (int i = 0; i < planet_num; i++) {
			 double xxPos = in.readDouble();
			 double yyPos = in.readDouble();
			 double xxVel = in.readDouble();
			 double yyVel = in.readDouble();
			 double mass = in.readDouble();
			 String imgFileName = in.readString();
			 result[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName); 
		}		

		return result;
	}

	private static void drawBackground(double radius) {
	    StdDraw.setScale(-radius, radius); /*The arguments are the coordinates of the minimum and maximum 
	    									x- or y-coordinates that will appear in the canvas*/	    
	    // StdDraw.clear();
	    StdDraw.picture(0, 0, "images/starfield.jpg"); /*StdDraw.picture(x, y, filename) 
	    												plots the image in the given filename on the canvas, 
	    												centered on (x, y)
  														*/
  	}

	/*draw each one of the planets in the planets array you created*/
  	private static void drawPlanets(Planet[] allPlanets) {
	    for (Planet p : allPlanets) {
	      p.draw();
	    }
  	}
}