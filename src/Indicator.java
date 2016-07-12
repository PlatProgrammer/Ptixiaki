
public class Indicator {

	private double degree;
	private double degreeTotal;
	private double normal_degree;
	private int reel;
	private final int reels;
	private String name;
	private final double  MAX_DEGREES;
	private static final double  cycle= 360;

	private final int ipodieresis;
	private double result;
	private final double fakeResult;
	private int intResult;


	public void test(){
		System.out.println(name);
		System.out.println("degree " + degree);
		System.out.println("degreeTotal " + degreeTotal);
		System.out.println("normal_degree " + normal_degree);
		System.out.println("reel " + reel);
		System.out.println("result " +result);
		System.out.println("intResult "+intResult);

	}





	public Indicator(String name,Gear mover,int reels) {

		this.name = name;
		this.reels = reels;
		MAX_DEGREES = reels * cycle;
		mover.setMoverTrue(this);

		switch(name){

		case "metonic" : ipodieresis = 235; fakeResult=132.26845347451726;
		break;

		case "callipic" : ipodieresis = 4; fakeResult=0.5628444828704694;
		break;

		case "saros" : ipodieresis = 223; fakeResult=0;
		break;

		case "exeligmos" : ipodieresis = 3; fakeResult=0;
		break;

		default: System.err.println("Indicator error"); 
		ipodieresis = 0; fakeResult=0;
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
		double mam = cycle / (  (double) ipodieresis/reels); // mam = moires ana sinodiko mina*



		result = degreeTotal / mam;
		//intResult = (int) Math.ceil(result);
		result += fakeResult;

		intResult = (int) Math.floor(result);

		intResult = (intResult%ipodieresis)+1;


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
