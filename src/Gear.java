import java.util.ArrayList;



public class Gear {
	
	private int teeth;
	private ArrayList<Gear> touchingGearsbyTeeth = new ArrayList<Gear>();
	private ArrayList<Gear> touchingGearsbyAxis = new ArrayList<Gear>();
	private String name;
	private double degree=0;
	private double angle ;
	private boolean isMover = false;
	private Indicator ind;
	private static final double  cycle= 360;
	
	
	
	
	
	public Gear(int teeth,String name) {
		this.teeth = teeth;
		this.name = name;
		this.angle = (double) cycle / teeth;		
	}
	
	
	
	
	
	public String getName() {
		return name;
	}


	public void setTouchingGearsbyTeeth(Gear ... gears){
		
		for(Gear g : gears){
			touchingGearsbyTeeth.add(g);
		}
	}
	
	
	public void setTouchingGearsbyAxis(Gear ... gears){
		
		for(Gear g : gears){
			touchingGearsbyAxis.add(g);
		}
	}
	
	
	
	public void Spin(double d){
		
		
		degree += d;
		degree = (double) degree  % cycle;
		
		
		
		
		
		for(Gear g: touchingGearsbyAxis ){
			
			g.Spin(d);
		}
		for(Gear g: touchingGearsbyTeeth ){
			
			double y = ((double) this.getTeeth() / g.getTeeth() ) * d;
			g.Spin(y);
		}
		
		if(isMover){
			moveIndicator(ind,d);
		}
		
	}
	
	public void moveIndicator(Indicator ind,double d){
		ind.Spin(d);
	}
	
	
	public void setMoverTrue(Indicator ind) {
		this.ind = ind;
		this.isMover = true;
		
	}

	public double getAngle() {
		return angle;
	}


	public double getDegree() {
		double degree2 ;
		degree2 = Math.round(degree * 1000);
		degree2 = (double) degree2/1000;
		degree2 = (double) degree2  % cycle;
		return degree2;
	}


	public int getTeeth() {
		return teeth;
	}
	
	public void reset(){
		this.degree = 0;		
	}
	

	
	
}
