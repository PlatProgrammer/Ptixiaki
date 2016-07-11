import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;




public class Main {


	static Gear b1,b2,b3,c1,c2,d1,d2,e1,e2,e3,e4,e5,e6,f1,f2,g1,g2,h1,h2,i1,k1,k2,l1,l2,m1,m2,m3,n1,n2,o1,p1,p2,q1;



	public static void main(String[] args) {




		LocalDate today = LocalDate.now();
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(today.getYear(), today.getMonthValue()-1, today.getDayOfMonth());

		HashMap<String,Gear> gears = new HashMap<String,Gear>();
		HashMap<String,Indicator> indicators = new HashMap<String,Indicator>();


		initializeGears(gears);
		//initializeGears2(gears);
		connectGears();

		initializeIndicators(indicators,gears);

		//new InputFrame(converter,gears,indicators);
		new MainFrame(gears,indicators);



	}

	public static void initializeIndicators(HashMap<String, Indicator> indicators,HashMap<String,Gear> gears){

		Indicator callipic = new Indicator("callipic", gears.get("o1"),1);	indicators.put(callipic.getName(), callipic);
		Indicator metonic = new Indicator("metonic", gears.get("n1"),5);		indicators.put(metonic.getName(), metonic);
		Indicator saros = new Indicator("saros", gears.get("g1"),4);			indicators.put(saros.getName(), saros);
		Indicator exeligmos = new Indicator("exeligmos", gears.get("i1"),1);	indicators.put(exeligmos.getName(), exeligmos);

	}


	public static void initializeGears(HashMap<String,Gear> gears){


		b1 = new Gear(224,"b1");gears.put(b1.getName(),b1);
		b2 = new Gear(64,"b2");gears.put(b2.getName(),b2);
		b3 = new Gear(32,"b3");gears.put(b3.getName(),b3);
		c1 = new Gear(38,"c1");gears.put(c1.getName(),c1);
		c2 = new Gear(48,"c2");gears.put(c2.getName(),c2);
		d1 = new Gear(24,"d1");gears.put(d1.getName(),d1);
		d2 = new Gear(127,"d2");gears.put(d2.getName(),d2);
		e1 = new Gear(32,"e1");gears.put(e1.getName(),e1);
		e2 = new Gear(32,"e2");gears.put(e2.getName(),e2);
		e3 = new Gear(223,"e3");gears.put(e3.getName(),e3);
		e4 = new Gear(188,"e4");gears.put(e4.getName(),e4);
		e5 = new Gear(50,"e5");gears.put(e5.getName(),e5);
		e6 = new Gear(50,"e6");gears.put(e6.getName(),e6);
		f1 = new Gear(53,"f1");gears.put(f1.getName(),f1);
		f2 = new Gear(30,"f2");gears.put(f2.getName(),f2);
		g1 = new Gear(54,"g1");gears.put(g1.getName(),g1);
		g2 = new Gear(20,"g2");gears.put(g2.getName(),g2);
		h1 = new Gear(60,"h1");gears.put(h1.getName(),h1);
		h2 = new Gear(15,"h2");gears.put(h2.getName(),h2);
		i1 = new Gear(60,"i1");gears.put(i1.getName(),i1);
		k1 = new Gear(50,"k1");gears.put(k1.getName(),k1);
		k2 = new Gear(50,"k2");gears.put(k2.getName(),k2);
		l1 = new Gear(38,"l1");gears.put(l1.getName(),l1);
		l2 = new Gear(53,"l2");gears.put(l2.getName(),l2);
		m1 = new Gear(96,"m1");gears.put(m1.getName(),m1);
		m2 = new Gear(15,"m2");gears.put(m2.getName(),m2);
		m3 = new Gear(27,"m3");gears.put(m3.getName(),m3);
		n1 = new Gear(53,"n1");gears.put(n1.getName(),n1);
		n2 = new Gear(15,"n2");gears.put(n2.getName(),n2);
		o1 = new Gear(60,"o1");gears.put(o1.getName(),o1);
		p1 = new Gear(60,"p1");gears.put(p1.getName(),p1);
		p2 = new Gear(12,"p2");gears.put(p2.getName(),p2);
		q1 = new Gear(20,"q1");gears.put(q1.getName(),q1);



	}

	public static void connectGears(){
		//ConnectGears

		b1.setTouchingGearsbyAxis(b2);
		b2.setTouchingGearsbyTeeth(l1,c1);
		//b3.setTouchingGearsbyTeeth(b0);
		c1.setTouchingGearsbyAxis(c2);
		c2.setTouchingGearsbyTeeth(d1);
		d1.setTouchingGearsbyAxis(d2);
		d2.setTouchingGearsbyTeeth(e2);
		e1.setTouchingGearsbyTeeth(b3);
		e2.setTouchingGearsbyAxis(e5);
		e3.setTouchingGearsbyAxis(e4);
		e4.setTouchingGearsbyTeeth(f1);
		e5.setTouchingGearsbyTeeth(k1);
		e6.setTouchingGearsbyAxis(e1);
		f1.setTouchingGearsbyAxis(f2);
		f2.setTouchingGearsbyTeeth(g1);
		g1.setTouchingGearsbyAxis(g2);
		g2.setTouchingGearsbyTeeth(h1);
		h1.setTouchingGearsbyAxis(h2);
		h2.setTouchingGearsbyTeeth(i1);
		// i1
		k1.setTouchingGearsbyAxis(k2);
		k2.setTouchingGearsbyTeeth(e6);
		l1.setTouchingGearsbyAxis(l2);
		l2.setTouchingGearsbyTeeth(m1);
		m1.setTouchingGearsbyAxis(m2,m3);
		m2.setTouchingGearsbyTeeth(n1);
		m3.setTouchingGearsbyTeeth(e3);
		n1.setTouchingGearsbyAxis(n2);
		n2.setTouchingGearsbyTeeth(p1);
		// o1
		p1.setTouchingGearsbyAxis(p2);
		p2.setTouchingGearsbyTeeth(o1);
	}

	public static void reset(HashMap<String,Gear> gears,HashMap<String, Indicator> indicators){



		Iterator<Entry<String, Gear>> entries = gears.entrySet().iterator();

		while (entries.hasNext()) {
			Entry<String, Gear> entry = entries.next();

			//HashMap.Entry entry = (HashMap.Entry) entries.next();
			
			Gear value = (Gear)entry.getValue();
			value.reset();

		}

		Iterator<Entry<String, Indicator>> entries2 = indicators.entrySet().iterator();

		while (entries2.hasNext()) {
			Entry<String, Indicator> entry2 = entries2.next();

			Indicator value2 = (Indicator)entry2.getValue();
			value2.reset();

		}

	}




	/*public static void initializeGears2(HashMap<String,Gear> gears){


		Gear b1 = new Gear(224,"b1",306.25);gears.put(b1.getName(),b1);
		Gear b2 = new Gear(64,"b2",2.8);gears.put(b2.getName(),b2);
		Gear b3 = new Gear(32,"b3");gears.put(b3.getName(),b3);
		Gear c1 = new Gear(38,"c1");gears.put(c1.getName(),c1);
		Gear c2 = new Gear(48,"c2");gears.put(c2.getName(),c2);
		Gear d1 = new Gear(24,"d1");gears.put(d1.getName(),d1);
		Gear d2 = new Gear(127,"d2");gears.put(d2.getName(),d2);
		Gear e1 = new Gear(32,"e1");gears.put(e1.getName(),e1);
		Gear e2 = new Gear(32,"e2");gears.put(e2.getName(),e2);
		Gear e3 = new Gear(223,"e3");gears.put(e3.getName(),e3);
		Gear e4 = new Gear(188,"e4");gears.put(e4.getName(),e4);
		Gear e5 = new Gear(50,"e5");gears.put(e5.getName(),e5);
		Gear e6 = new Gear(50,"e6");gears.put(e6.getName(),e6);
		Gear f1 = new Gear(53,"f1");gears.put(f1.getName(),f1);
		Gear f2 = new Gear(30,"f2");gears.put(f2.getName(),f2);
		Gear g1 = new Gear(54,"g1");gears.put(g1.getName(),g1);
		Gear g2 = new Gear(20,"g2");gears.put(g2.getName(),g2);
		Gear h1 = new Gear(60,"h1");gears.put(h1.getName(),h1);
		Gear h2 = new Gear(15,"h2");gears.put(h2.getName(),h2);
		Gear i1 = new Gear(60,"i1");gears.put(i1.getName(),i1);
		Gear k1 = new Gear(50,"k1");gears.put(k1.getName(),k1);
		Gear k2 = new Gear(50,"k2");gears.put(k2.getName(),k2);
		Gear l1 = new Gear(38,"l1");gears.put(l1.getName(),l1);
		Gear l2 = new Gear(53,"l2");gears.put(l2.getName(),l2);
		Gear m1 = new Gear(96,"m1");gears.put(m1.getName(),m1);
		Gear m2 = new Gear(15,"m2");gears.put(m2.getName(),m2);
		Gear m3 = new Gear(27,"m3");gears.put(m3.getName(),m3);
		Gear n1 = new Gear(53,"n1");gears.put(n1.getName(),n1);
		Gear n2 = new Gear(15,"n2");gears.put(n2.getName(),n2);
		Gear o1 = new Gear(60,"o1");gears.put(o1.getName(),o1);
		Gear p1 = new Gear(60,"p1");gears.put(p1.getName(),p1);
		Gear p2 = new Gear(12,"p2");gears.put(p2.getName(),p2);
		Gear q1 = new Gear(20,"q1");gears.put(q1.getName(),q1);


	}*/





}
