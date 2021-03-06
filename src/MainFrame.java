import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;





@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private HashMap<String,Gear> gears;
	private HashMap<String,Indicator> indicators;	
	private JTextArea b1text,b2text,b3text,c1text,c2text,d1text,d2text,e1text,e2text,e3text,e4text,e5text,e6text,f1text,f2text,
			g1text,g2text,h1text,h2text,i1text,k1text,k2text,l1text,l2text,m1text,m2text,m3text,n1text,n2text,o1text,p1text,p2text;
	private JTextArea callipicText,metonicText,sarosText,exeligmosText;
	private JLabel b1label,b2label,b3label,c1label,c2label,d1label,d2label,e1label,e2label,e3label,e4label,e5label,e6label,f1label,f2label,g1label,
			g2label,h1label,h2label,i1label,k1label,k2label,l1label,l2label,m1label,m2label,m3label,n1label,n2label,o1label,p1label,p2label;
	private JLabel callipicLabel,metonicLabel,sarosLabel,exeligmosLabel;
	private JLabel calculateLbl;
	private JPanel gearResultPanel,indicatorResultPanel,inputPanel,progressPanel;
	private JButton btnNewDate;	
	private ButtonListener listener;
	private JLabel gifLabel,yearLbl,dateLbl,monthLbl;	
	private ImageIcon imageIcon;	
	private JProgressBar progressBar;
	private boolean before = false;	
	private JSpinner daySpinner,monthSpinner,yearSpinner;
	private LocalDate today,spinnerDate,date1;
	private int day,month,year;
	private String error;
	
	


	public MainFrame(final HashMap<String,Gear> gears, final HashMap<String,Indicator> indicators) {

		URL iconURL = getClass().getResource("/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());


		this.gears = gears;
		this.indicators = indicators;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		today=LocalDate.now();





		gearResultPanel = new JPanel();
		gearResultPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		indicatorResultPanel = new JPanel();
		indicatorResultPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		inputPanel = new JPanel();
		inputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		progressPanel = new JPanel();
		progressPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(gearResultPanel, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(progressPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(indicatorResultPanel, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
										.addGap(18)
										.addComponent(inputPanel, 0, 0, Short.MAX_VALUE)))
						.addContainerGap(12, GroupLayout.PREFERRED_SIZE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(gearResultPanel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(indicatorResultPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(progressPanel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				);

		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setForeground(new Color(51, 153, 255));
		progressBar.setMaximum(99);
		progressBar.setName("");
		progressBar.setStringPainted(true);
		GroupLayout gl_progressPanel = new GroupLayout(progressPanel);
		gl_progressPanel.setHorizontalGroup(
				gl_progressPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_progressPanel.createSequentialGroup()
						.addContainerGap(24, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(20))
				);
		gl_progressPanel.setVerticalGroup(
				gl_progressPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_progressPanel.createSequentialGroup()
						.addContainerGap(49, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(46))
				);
		progressPanel.setLayout(gl_progressPanel);

		btnNewDate = new JButton("New Date");
		listener = new ButtonListener();
		btnNewDate.addActionListener(listener);




		

		//ftf.setEditable(true); 

		calculateLbl = new JLabel("Calculating...");
		calculateLbl.setVisible(false);
		calculateLbl.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
		calculateLbl.setForeground(Color.RED);


		JPanel panel_3 = new JPanel();

		daySpinner = new JSpinner();
		//daySpinner.setModel(new SpinnerNumberModel(today.getDayOfMonth(), 1, 31, 1));
		daySpinner.setModel(new SpinnerNumberModel(12, 1, 31, 1));

		monthSpinner = new JSpinner();
		monthSpinner.setModel(new SpinnerNumberModel(today.getMonthValue(), 1, 12, 1));
		monthSpinner.setModel(new SpinnerNumberModel(5, 1, 12, 1));

		yearSpinner = new JSpinner();
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearSpinner);
		editor.getFormat().setGroupingUsed(false);
		yearSpinner.setEditor(editor);
		yearSpinner.setModel(new SpinnerNumberModel(0, new Integer(-205), null,1));
		//yearSpinner.setValue(today.getYear());
		yearSpinner.setValue(-204);

		dateLbl = new JLabel("Date");

		monthLbl = new JLabel("Month");

		yearLbl = new JLabel("Year");










		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
				gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
						.addGap(95)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(86, Short.MAX_VALUE))
				.addGroup(gl_inputPanel.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnNewDate)
						.addGap(162))
				.addGroup(gl_inputPanel.createSequentialGroup()
						.addContainerGap(163, Short.MAX_VALUE)
						.addComponent(calculateLbl)
						.addGap(164))
				.addGroup(gl_inputPanel.createSequentialGroup()
						.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addGap(109)
										.addComponent(dateLbl)
										.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
										.addComponent(monthLbl))
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addGap(101)
										.addComponent(daySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
										.addComponent(monthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addGap(32)
										.addComponent(yearSpinner, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addGap(45)
										.addComponent(yearLbl)))
						.addContainerGap(95, Short.MAX_VALUE))
				);
		gl_inputPanel.setVerticalGroup(
				gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
						.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addGap(67)
										.addComponent(calculateLbl))
								.addGroup(gl_inputPanel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(dateLbl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
												.addComponent(monthLbl)
												.addComponent(yearLbl))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(daySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(monthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(yearSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnNewDate)
						.addContainerGap(57, Short.MAX_VALUE))
				);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		gifLabel = new JLabel("");
		gifLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		gifLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gifLabel.setMaximumSize(new Dimension(10, 10));
		gifLabel.setPreferredSize(new Dimension(10, 10));
		gifLabel.setSize(new Dimension(10, 10));
		panel_3.add(gifLabel);

		URL url = MainFrame.class.getResource("/gif.gif");
		imageIcon = new ImageIcon(url);






		gifLabel.setVisible(false);
		inputPanel.setLayout(gl_inputPanel);
		exeligmosLabel = new JLabel("exeligmos");
		sarosLabel = new JLabel("saros");
		metonicLabel = new JLabel("metonic");

		callipicLabel = new JLabel("callipic");
		exeligmosText = new JTextArea();
		exeligmosText.setEditable(false);
		sarosText = new JTextArea();
		sarosText.setEditable(false);
		metonicText = new JTextArea();
		metonicText.setEditable(false);

		callipicText = new JTextArea();
		callipicText.setEditable(false);
		GroupLayout gl_indicatorResultPanel = new GroupLayout(indicatorResultPanel);
		gl_indicatorResultPanel.setHorizontalGroup(
				gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_indicatorResultPanel.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_indicatorResultPanel.createSequentialGroup()
										.addComponent(exeligmosLabel)
										.addGap(18))
								.addGroup(gl_indicatorResultPanel.createSequentialGroup()
										.addComponent(sarosLabel)
										.addGap(29))
								.addGroup(gl_indicatorResultPanel.createSequentialGroup()
										.addComponent(metonicLabel)
										.addGap(23))
								.addGroup(gl_indicatorResultPanel.createSequentialGroup()
										.addComponent(callipicLabel)
										.addGap(27)))
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(callipicText, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(metonicText, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(sarosText, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(exeligmosText, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(35, Short.MAX_VALUE))
				);
		gl_indicatorResultPanel.setVerticalGroup(
				gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_indicatorResultPanel.createSequentialGroup()
						.addGap(24)
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(exeligmosLabel)
								.addComponent(exeligmosText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(sarosText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sarosLabel))
						.addGap(18)
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(metonicText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(metonicLabel))
						.addGap(18)
						.addGroup(gl_indicatorResultPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(callipicText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(callipicLabel))
						.addContainerGap(24, Short.MAX_VALUE))
				);
		gl_indicatorResultPanel.linkSize(SwingConstants.VERTICAL, new Component[] {exeligmosText, sarosText, metonicText, callipicText});
		gl_indicatorResultPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {exeligmosText, sarosText, metonicText, callipicText});
		indicatorResultPanel.setLayout(gl_indicatorResultPanel);




		b1label = new JLabel("b1");
		b2label = new JLabel("b2");
		b3label = new JLabel("b3");
		c1label = new JLabel("c1");
		c2label = new JLabel("c2");
		d1label = new JLabel("d1");
		d2label = new JLabel("d2");

		b1text = new JTextArea();
		b1text.setEditable(false);
		b2text = new JTextArea();
		b2text.setEditable(false);
		b3text = new JTextArea();
		b3text.setEditable(false);
		c1text = new JTextArea();
		c1text.setEditable(false);
		c2text = new JTextArea();
		c2text.setEditable(false);
		d1text = new JTextArea();
		d1text.setEditable(false);
		d2text = new JTextArea();
		d2text.setEditable(false);
		e1label = new JLabel("e1");
		e2label = new JLabel("e2");
		e3label = new JLabel("e3");
		e4label = new JLabel("e4");
		e5label = new JLabel("e5");
		e6label = new JLabel("e6");
		f1label = new JLabel("f1");
		e1text = new JTextArea();
		e1text.setEditable(false);
		e2text = new JTextArea();
		e2text.setEditable(false);
		e3text = new JTextArea();
		e3text.setEditable(false);
		e4text = new JTextArea();
		e4text.setEditable(false);
		e5text = new JTextArea();
		e5text.setEditable(false);
		e6text = new JTextArea();
		e6text.setEditable(false);
		f1text = new JTextArea();
		f1text.setEditable(false);
		f2label = new JLabel("f2");
		g1label = new JLabel("g1");
		g2label = new JLabel("g2");
		h1label = new JLabel("h1");
		h2label = new JLabel("h2");
		i1label = new JLabel("i1");
		k1label = new JLabel("k1");
		g1text = new JTextArea();
		g1text.setEditable(false);
		h1text = new JTextArea();
		h1text.setEditable(false);
		h2text = new JTextArea();
		h2text.setEditable(false);
		i1text = new JTextArea();
		i1text.setEditable(false);
		k1text = new JTextArea();
		k1text.setEditable(false);
		f2text = new JTextArea();
		f2text.setEditable(false);
		g2text = new JTextArea();
		g2text.setEditable(false);
		k2label = new JLabel("k2");
		l1label = new JLabel("l1");
		l2label = new JLabel("l2");
		m1label = new JLabel("m1");
		m2label = new JLabel("m2");
		m3label = new JLabel("m3");
		n1label = new JLabel("n1");
		k2text = new JTextArea();
		k2text.setEditable(false);
		l1text = new JTextArea();
		l1text.setEditable(false);
		l2text = new JTextArea();
		l2text.setEditable(false);
		m1text = new JTextArea();
		m1text.setEditable(false);
		m2text = new JTextArea();
		m2text.setEditable(false);
		m3text = new JTextArea();
		m3text.setEditable(false);
		n1text = new JTextArea();
		n1text.setEditable(false);
		n2label = new JLabel("n2");
		n2text = new JTextArea();
		n2text.setEditable(false);
		o1label = new JLabel("o1");
		o1text = new JTextArea();
		o1text.setEditable(false);
		p1label = new JLabel("p1");
		p1text = new JTextArea();
		p1text.setEditable(false);
		p2label = new JLabel("p2");
		p2text = new JTextArea();
		p2text.setEditable(false);
		
		GroupLayout gl_gearResultPanel = new GroupLayout(gearResultPanel);
		gl_gearResultPanel.setHorizontalGroup(
			gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gearResultPanel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(d2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(d2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(f1label, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(d1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(d1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e6label))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(c2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(c2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e5label))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(c1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(c1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e4label))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(b3label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(b3text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e3label))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(b2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(b2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e2label))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(b1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(b1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(e1label)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(e2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(e1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(e3text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(e5text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(f1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(e4text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(e6text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(g1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(g1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(l1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(l1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(o1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(o1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(f2label, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(f2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(k2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(k2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(n2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(n2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(g2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(g2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(l2label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(l2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(p1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(p1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addComponent(k1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(k1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(n1label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(n1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_gearResultPanel.createSequentialGroup()
							.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_gearResultPanel.createSequentialGroup()
									.addComponent(h1label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(h1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m1label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(m1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(p2label))
								.addGroup(gl_gearResultPanel.createSequentialGroup()
									.addComponent(h2label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(h2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m2label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(m2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_gearResultPanel.createSequentialGroup()
									.addComponent(i1label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(i1text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(m3label, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(m3text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(p2text, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_gearResultPanel.setVerticalGroup(
			gl_gearResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gearResultPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(b1label)
						.addComponent(b1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e1label)
						.addComponent(e1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(f2label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(f2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(k2label)
						.addComponent(k2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(n2label)
						.addComponent(n2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(b2label)
						.addComponent(b2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e2label)
						.addComponent(e2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(g1label)
						.addComponent(g1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(l1label)
						.addComponent(l1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(o1label)
						.addComponent(o1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(b3label)
						.addComponent(b3text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e3label)
						.addComponent(e3text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(g2label)
						.addComponent(g2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(l2label)
						.addComponent(l2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(p1label)
						.addComponent(p1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(c1label)
						.addComponent(c1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e4label)
						.addComponent(h1label)
						.addComponent(h1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e4text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(m1label)
						.addComponent(m1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(p2label)
						.addComponent(p2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(c2label)
						.addComponent(c2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e5label)
						.addComponent(e5text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(h2label)
						.addComponent(h2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(m2label)
						.addComponent(m2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(d1label)
						.addComponent(d1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e6label)
						.addComponent(i1label)
						.addComponent(i1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(e6text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(m3label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(m3text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_gearResultPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(d2label)
						.addComponent(d2text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(f1label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(f1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(k1label)
						.addComponent(k1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(n1label)
						.addComponent(n1text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(125))
		);
		gl_gearResultPanel.linkSize(SwingConstants.VERTICAL, new Component[] {b1text, b2text, b3text, c1text, c2text, d1text, d2text, e1text, e2text, e3text, e4text, e5text, e6text, f1text, g1text, h1text, h2text, i1text, k1text, f2text, g2text, k2text, l1text, l2text, m1text, m2text, m3text, n1text, n2text, o1text, p1text, p2text});
		gl_gearResultPanel.linkSize(SwingConstants.VERTICAL, new Component[] {b1label, b2label, b3label, c1label, c2label, d1label, d2label, e1label, e2label, e3label, e4label, e5label, e6label, f1label, f2label, g1label, g2label, h1label, h2label, i1label, k1label, k2label, l1label, l2label, m1label, m2label, m3label, n1label, n2label, o1label, p1label, p2label});
		gl_gearResultPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {b1text, b2text, b3text, c1text, c2text, d1text, d2text, e1text, e2text, e3text, e4text, e5text, e6text, f1text, g1text, h1text, h2text, i1text, k1text, f2text, g2text, k2text, l1text, l2text, m1text, m2text, m3text, n1text, n2text, o1text, p1text, p2text});
		gl_gearResultPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {b1label, b2label, b3label, c1label, c2label, d1label, d2label, e1label, e2label, e3label, e4label, e5label, e6label, f1label, f2label, g1label, g2label, h1label, h2label, i1label, k1label, k2label, l1label, l2label, m1label, m2label, m3label, n1label, n2label, o1label, p1label, p2label});
		gearResultPanel.setLayout(gl_gearResultPanel);
		
		getContentPane().setLayout(groupLayout);

		getRootPane().setDefaultButton(btnNewDate);
		

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


		this.pack();

		this.setVisible(true);

	}

	public void getValues()	{
		day=(Integer)daySpinner.getValue();
		month = (Integer)monthSpinner.getValue();
		year = (Integer)yearSpinner.getValue();		
	}
	
	
	class ButtonListener implements ActionListener{
		

		int[] noLeap={31,28,31,30,31,30,31,31,30,31,30,31};
		int[] Leap={31,29,31,30,31,30,31,31,30,31,30,31};

		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			spinnerDate=null;
			getValues();
			
			if(checkDate(day, month, year)){
				
				MyTask task = new MyTask();
				task.execute();
				
			}
			else{
				
				JOptionPane.showMessageDialog(null,
						error,
						"Error",
						JOptionPane.ERROR_MESSAGE);

			}


		}

		public boolean isLeapYear(int year) {

			if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
				return true;
			} else {
				return false;
			}
		}

		public boolean checkDate(int day,int month,int year){
			boolean good = true;
			if(year < - 4712){
				System.out.println("Too early");
				error = "Too early";
				good = false;
			}
			else
			{
				if(year==0){
					System.out.println("No year 0");
					error = "No year 0";
					good = false;

				}
				else{			

					if(year == 1582 && month ==10 && (day>=5 && day<=14)){
						System.out.println("no 1582");
						error = "no 1582";
						good = false;
					}
					else{

						if(isLeapYear(year)){
							if(day>Leap[month-1]){
								System.out.println("Wrong Datee");
								error = "Wrong Date";
								good = false;
							}
						}
						else{
							if(day>noLeap[month-1]){
								System.out.println("Wrong Date");
								error = "Wrong Date";
								good = false;
							}
						}
					}
				}
			}


			return good;
		}

		public void show(){

			b1text.setText(Double.toString(gears.get("b1").getDegree()));

			b2text.setText(Double.toString(gears.get("b2").getDegree()));

			b3text.setText(Double.toString(gears.get("b3").getDegree()));

			c1text.setText(Double.toString(gears.get("c1").getDegree()));

			c2text.setText(Double.toString(gears.get("c2").getDegree()));

			d1text.setText(Double.toString(gears.get("d1").getDegree()));

			d2text.setText(Double.toString(gears.get("d2").getDegree()));

			e1text.setText(Double.toString(gears.get("e1").getDegree()));

			e2text.setText(Double.toString(gears.get("e2").getDegree()));

			e3text.setText(Double.toString(gears.get("e3").getDegree()));

			e4text.setText(Double.toString(gears.get("e4").getDegree()));

			e5text.setText(Double.toString(gears.get("e5").getDegree()));

			e6text.setText(Double.toString(gears.get("e6").getDegree()));

			f1text.setText(Double.toString(gears.get("f1").getDegree()));

			f2text.setText(Double.toString(gears.get("f2").getDegree()));

			g1text.setText(Double.toString(gears.get("g1").getDegree()));

			g2text.setText(Double.toString(gears.get("g2").getDegree()));

			h1text.setText(Double.toString(gears.get("h1").getDegree()));

			h2text.setText(Double.toString(gears.get("h2").getDegree()));

			i1text.setText(Double.toString(gears.get("i1").getDegree()));

			k1text.setText(Double.toString(gears.get("k1").getDegree()));

			k2text.setText(Double.toString(gears.get("k2").getDegree()));

			l1text.setText(Double.toString(gears.get("l1").getDegree()));

			l2text.setText(Double.toString(gears.get("l2").getDegree()));

			m1text.setText(Double.toString(gears.get("m1").getDegree()));

			m2text.setText(Double.toString(gears.get("m2").getDegree()));

			m3text.setText(Double.toString(gears.get("m3").getDegree()));

			n1text.setText(Double.toString(gears.get("n1").getDegree()));

			n2text.setText(Double.toString(gears.get("n2").getDegree()));

			o1text.setText(Double.toString(gears.get("o1").getDegree()));

			p1text.setText(Double.toString(gears.get("p1").getDegree()));

			p2text.setText(Double.toString(gears.get("p2").getDegree()));



			/*callipicText.setText(Integer.toString(indicators.get("callipic").getReel())  + " x " + Double.toString(indicators.get("callipic").getDegree()));

			metonicText.setText(Integer.toString(indicators.get("metonic").getReel())  + " x " + Double.toString(indicators.get("metonic").getDegree()));

			sarosText.setText(Integer.toString(indicators.get("saros").getReel())  + " x " + Double.toString(indicators.get("saros").getDegree()));

			exeligmosText.setText(Integer.toString(indicators.get("exeligmos").getReel())  + " x " + Double.toString(indicators.get("exeligmos").getDegree()));*/

			callipicText.setText(Integer.toString(indicators.get("callipic").setResult()));
			metonicText.setText(Integer.toString(indicators.get("metonic").setResult()));
			sarosText.setText(Integer.toString(indicators.get("saros").setResult()));
			exeligmosText.setText(Integer.toString(indicators.get("exeligmos").setResult()));



			
			gifLabel.setIcon(null);
			gifLabel.setText("Set date");



		}

		private class MyTask extends SwingWorker<Void, Integer> {

			int t = 0;
			int t2 = 0;



			@Override
			public Void doInBackground() throws Exception{

				calculate();


				return null;
			}
			
			

			public void calculate(){
				
				
				spinnerDate = LocalDate.of(year,month, day);

				GregorianCalendar calendar = new GregorianCalendar();

				if(spinnerDate.getYear()<0){
					calendar.set(spinnerDate.getYear()+1, spinnerDate.getMonthValue(), spinnerDate.getDayOfMonth());
				}
				else{
					calendar.set(spinnerDate.getYear(), spinnerDate.getMonthValue(), spinnerDate.getDayOfMonth());
				}
				

				Converter c = new Converter(calendar);
				


				
				long x = 0,y = 0;	

				
				if(before && spinnerDate.isAfter(date1)){

					x = c.cal_to_jd();

					
					GregorianCalendar temp = new GregorianCalendar();
					
					if(date1.getYear()<0){
						temp.set(date1.getYear()+1, date1.getMonthValue(), date1.getDayOfMonth());
					}
					else{
						temp.set(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth());
					}

					Converter c2 = new Converter(temp);



					long xTemp = c2.cal_to_jd();


					y =Math.abs(xTemp - x);

					temp=null;
				}
				else if(before && spinnerDate.compareTo(date1) == 0){
					y = 0;
				}
				else{
					reset();
					x = c.cal_to_jd();
					y = Math.abs(x - Converter.getSarosCallibration());
				}

				
				date1 = spinnerDate;
				before = true;

				

				if(y!=0){
					start_graphics();
					spin_by_days(y);
				}
				
				/*start_graphics();
				
				spin_by_days(Converter.getDifference());
				
				System.out.println(Converter.getDifference());*/
				
				


			}
			
			public void start_graphics(){
				
				btnNewDate.setEnabled(false);
				progressBar.setVisible(true);
				calculateLbl.setVisible(true);	
				gifLabel.setVisible(true);
				gifLabel.setText("");
				gifLabel.setIcon(imageIcon);
				getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
			}
			
			public void end_graphics(){
				show();  // this is run on the Swing event thread
				getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				calculateLbl.setVisible(false);
				progressBar.setValue(progressBar.getMaximum());
				btnNewDate.setEnabled(true);
				indicators.get("metonic").test();
				indicators.get("callipic").test();
			}
			
			public void spin_by_days(long days){
				
				
				

				double angle_day = (double) 360/365.25;
				double a2 = angle_day/4;


				for(long i=0;i<days;i++){

					for(int j=0;j<4;j++){

						gears.get("b1").Spin(a2);

					}
					checkProccess(days);


				}

			}
			
			
			
			
			
			
			public void checkProccess(long days){
				if(t>=days/100){
					t2+=1;
					publish(t2);					
					t=0;						
				}
				t++;
			}
			
			@Override
			protected void process(List<Integer> ts){
				int l  = ts.get(ts.size() - 1);

				progressBar.setValue(l);

			}
			@Override
			protected void done() {
				end_graphics();
			}

			
			public void reset(){
				Main.reset(gears, indicators);

			}



		}

	}
}
