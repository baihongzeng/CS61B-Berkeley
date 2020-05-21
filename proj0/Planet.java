public class Planet {
	private static final double G = 6.67e-11; //gravitational constant
	public double xxPos; //current x position
	public double yyPos; //current y position
	public double xxVel; //current x velocity
	public double yyVel; //current y velocity
	public double mass; // mass
	public String imgFileName;

	// constructor
	public Planet (double xP, double yP, double xV, 
		double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	// overloaded constructor
	public Planet (Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/** calculates the distance between two Planets, one is itself, the other one is other planet*/
	public double calcDistance (Planet otherPlanet) {
		return Math.sqrt(Math.pow(this.xxPos - otherPlanet.xxPos, 2) + Math.pow(this.yyPos - otherPlanet.yyPos, 2));
	}

	/* returns the force exerted on this planet by the given planet*/
	public double calcForceExertedBy (Planet p) {
		double force = G * this.mass * p.mass / Math.pow(calcDistance(p), 2);
		return force;
	}

	/*describe the force exerted by a given planet in the X directions*/
	public double calcForceExertedByX (Planet p) {
		double force_x = 0;
		if (calcDistance(p) != 0) {
			force_x = calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);	
		}
		return force_x;
	}	
	
	/*describe the force exerted by a given planet in the Y directions*/
	public double calcForceExertedByY (Planet p) {
		double force_y = 0;
		if (calcDistance(p) != 0) {
			force_y = calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);	
		}
		return force_y;
	}	

	/*describe the force exerted by all other planets in the X directions*/
	public double calcNetForceExertedByX (Planet[] allPlanets) {
		double force_net_x = 0;
		for (Planet p : allPlanets) {
			force_net_x += calcForceExertedByX(p);
		}

		return force_net_x;
	}

	/*describe the force exerted by all other planets in the Y directions*/
	public double calcNetForceExertedByY (Planet[] allPlanets) {
		double force_net_y = 0;
		for (Planet p : allPlanets) {
			force_net_y += calcForceExertedByY(p);
		}

		return force_net_y;
	}

	public void update (double dt, double force_net_x, double force_net_y) {
		double acceleration_x = force_net_x / this.mass;
		double acceleration_y = force_net_y / this.mass;
		
		this.xxVel = this.xxVel + acceleration_x * dt;
		this.yyVel = this.yyVel + acceleration_y * dt;

		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	/*draw the Planet’s image at the Planet’s position*/
	public void draw() {
		// (x, y, img) put img on (x, y) position
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}