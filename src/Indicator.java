
public class Indicator {

	private double degree=0;
	private double degreeTotal=0;
	private double normal_degree;
	private int reel;
	private final int reels;
	private String name;
	private final double  MAX_DEGREES;
	private static final double  cycle= 360;

	private final int value;





	public Indicator(String name,Gear mover,int reels) {
		this.name = name;
		this.reels = reels;
		MAX_DEGREES = reels * cycle;
		mover.setMoverTrue(this);

		switch(name){

		case "metonic" : value = 235;
		break;

		case "callipic" : value = 4;
		break;

		case "saros" : value = 223;
		break;

		case "exeligmos" : value = 3;
		break;

		default: System.err.println("Indicator error"); 
		value = 0;
		break;
		}

	}


	public void Spin(double d){
		degree += d;
		degreeTotal +=d;
		degree = (double) degree  % MAX_DEGREES;
		normal_degree = (double) degree  % cycle;
		reel = (int) (degree / cycle);

	}


	public double getDegree() {

		double normal_degree2 = Math.round(normal_degree * 1000);
		normal_degree2 = (double) normal_degree2/1000;
		normal_degree2 = (double) normal_degree2  % cycle;

		return normal_degree2;
	}

	public int getReel(){

		return reel;
	}

	public int setResult(){
		double mam = cycle / ( value/reels); // mam = moires ana moina
		
		double result = degreeTotal / mam;
		
		int intResult = (int) Math.ceil(result);
		
		intResult = (intResult%value) +1;
		
		
		return intResult;
	}


	public String getName() {
		return name;
	}

	public void reset(){
		this.degree = 0;
		this.reel=0;
		this.degreeTotal=0;
		this.normal_degree=0;
	}




}
