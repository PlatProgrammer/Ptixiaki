import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Converter {

	private static final long callibration = 1647044;
	private int era;
	private int y,m,d;
	
	/*Data
	http://www.csgnetwork.com/juliandaydate.html*/


	

	public Converter(Calendar c) {

		
		era = c.get(Calendar.ERA);
		y = c.get(Calendar.YEAR);
		m  = c.get(Calendar.MONTH) ;
		d = c.get(Calendar.DAY_OF_MONTH);
		


	}
	
	public void print(){
		
		System.out.println("era " + era);
		System.out.println("y " + y);
		System.out.println("m " + m);
		System.out.println("d " + d);
	}
	
	

	




	public long cal_to_jd(){





		int jy, ja, jm;			




		//		if( y < 0 )  ++y;
		if( era == GregorianCalendar.BC )
			y = -y + 1;

		if( m > 2 ) {
			jy = y;
			jm = m + 1;
		} else {
			jy = y - 1;
			jm = m + 13;
		}

		long intgr =  (long) Math.floor( Math.floor(365.25*jy) + Math.floor(30.6001*jm) + d + 1720995 );

		//check for switch to Gregorian calendar
		long gregcal = 15 + 31*( 10 + 12*1582 );

		if( d + 31*(m + 12*y) >= gregcal ) {
			ja = (int) Math.floor(0.01*jy);
			intgr += 2 - ja + Math.floor(0.25*ja);
		}


		return intgr;


		/*//correct for half-day offset
			var dayfrac = h/24.0 - 0.5;
			if( dayfrac < 0.0 ) {
				dayfrac += 1.0;
				--intgr;
			}

			//now set the fraction of a day
			var frac = dayfrac + (mn + s/60.0)/60.0/24.0;

			//round to nearest second
			var jd0 = (intgr + frac)*100000;
			var jd  = Math.floor(jd0);
			if( jd0 - jd > 0.5 ) ++jd;
			return jd/100000;*/




	}
	
	
	public long military(int year,int month,int day){
		int K,I,J;
		long JD;
		I= year;
		J= month;
		K= day;
		
		JD= K-32075+1461*(I+4800+(J-14)/12)/4+367*(J-2-(J-14)/12*12)*2/12-3*((I+4900+(J-14)/12)/100)/4;
		
		return JD;
		
	}
	
	public long military2(int year,int month,int day){
			int Y = year;
			int M = month;
			int D = day;
			int	A = Y/100;
			int  B = A/4;
			int C = 2-A+B;
			int  E = (int) (365.25*(Y+4716));
			int  F = (int) (30.6001*(M+1));
			long  JD= (long) (C+D+E+F-1524.5);
				 
				 return JD;
	}


	public static long getCallibration() {
		return callibration;
	}
}
