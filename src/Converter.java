import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Converter {

	private static final long callibration = 1647044;
	private Calendar c;
	private int era;
	private int y,m,d;
	
	/*Data
	http://www.csgnetwork.com/juliandaydate.html*/


	

	public Converter(Calendar c) {

		this.c=c;
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


	public static long getCallibration() {
		return callibration;
	}
}
