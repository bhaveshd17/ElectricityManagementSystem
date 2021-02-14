

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class CalculateBill extends JFrame {

	private JPanel contentPane;
	private JComboBox meter_no;
	private JTextField units;
	private JTextField bill_id;
	private JTextField phase;
	private JTextField category;
	private connect c1 = new connect();
	private JTextField date2;
	private double recent_amount;
	
	public void autoBI() {
		int last_bill_id;
		try {
			connect c1 = new connect();
			String str = "select max(bill_id) from bill";
			ResultSet rs = c1.s.executeQuery(str);
			
			if(rs.next())
			{
				last_bill_id = rs.getInt(1);
				last_bill_id++;
				bill_id.setText(Integer.toString(last_bill_id));
			}
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
	}
	
	
	
	//method and variables
	private Double total = null,extra = null;
	private Double month_charge = null;
	
	
	public void calc(double month_charge,Double p1) {
	try {
		ResultSet rs = c1.s.executeQuery("select * from bill where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'"+"order by bill_id desc LIMIT 1");
		if(rs.next())
			recent_amount = Double.parseDouble(rs.getString("amount"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
    	if(phase.getText().equalsIgnoreCase("1 Phase") && category.getText().equalsIgnoreCase("Residential+Commercial"))
        {
        	extra = 50+12.15+150.06+150;
        	if(month_charge >= 100.0)
        		total = (p1*21.05)+extra+month_charge+recent_amount;
        	else
        		total = (p1*21.05)+extra+month_charge;
        	this.month_charge = month_charge;
        }
        else if(phase.getText().equalsIgnoreCase("3 Phase") && category.getText().equalsIgnoreCase("Residential+Commercial"))
        {
        	extra = 50+12.15+200.76+150;
            if(month_charge >=100.0)
            	total = (p1*22.89)+extra+month_charge+recent_amount;
            else
            	total = (p1*22.89)+extra+month_charge;
            this.month_charge = month_charge;
        }
        else if(phase.getText().equalsIgnoreCase("1 Phase") && category.getText().equalsIgnoreCase("Residential"))
        {
        	extra = 50+12.15+80.56+150;
        	if(month_charge >= 100.0)
        		total = (p1*15.55)+extra+month_charge+recent_amount;
        	else
        		total = (p1*15.55)+extra+month_charge;
        	this.month_charge = month_charge;
        }
        else if(phase.getText().equalsIgnoreCase("3 Phase") && category.getText().equalsIgnoreCase("Residential"))
        {
        	extra = 50+12.15+90.36+150;
        	if(month_charge >= 100.0)
        		total = (p1*16.78)+extra+month_charge+recent_amount;
        	else
        		total = (p1*16.78)+extra+month_charge;
        	this.month_charge = month_charge;
        }
        else if(phase.getText().equalsIgnoreCase("1 Phase") && category.getText().equalsIgnoreCase("Commercial"))
        {
        	extra = 50+12.15+120.36+150;
        	if(month_charge >= 100.0)
        		total = (p1*20.12)+extra+month_charge+recent_amount;
        	else
        		total = (p1*20.12)+extra+month_charge;
        	this.month_charge = month_charge;
        }
        else if(phase.getText().equalsIgnoreCase("3 Phase") && category.getText().equalsIgnoreCase("Commercial"))
        {
        	extra = 50+12.15+106.36+150;
        	if(month_charge >= 100.0)
        		total = (p1*21.36)+extra+month_charge+recent_amount;
        	else
        		total = (p1*21.36)+extra+month_charge;
        	this.month_charge = month_charge;
        }
        else
        {
        	extra = null;
        	total = null;
        }
    }       
	
	
	public int dayDiff(String current_d,String d2) {
		String time1 = "11:00 AM";
		String time2 = "12:15 AM";

		String format = "yyyy-MM-dd hh:mm a";

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Date dateObj1 = null,dateObj2 = null;
		try {
			dateObj1 = sdf.parse(current_d + " " + time1);
			dateObj2 = sdf.parse(d2 + " " + time2);
			DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) ((dateObj1.getTime() - dateObj2.getTime()) / (24 * 60 * 60 * 1000));
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculateBill frame = new CalculateBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void fillComboBox() {
		
		try {
			
			String q  = "select * from customer order by meter_no desc";
			ResultSet rs = c1.s.executeQuery(q);
			
			while(rs.next())
			{
				meter_no.addItem(rs.getString("meter_no"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CalculateBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000,600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JPanel headpanel = new JPanel();
        headpanel.setBackground(new Color(0, 0, 0));
        headpanel.setBounds(0, 0, 1000, 30);
        contentPane.add(headpanel);
        headpanel.setLayout(null);
        //end of panel
        
        //header
        JLabel lblNewLabel = new JLabel("Electricity Management System");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(10, 0, 263, 30);
        headpanel.add(lblNewLabel);
        
        //closing button
        JLabel close = new JLabel("x");
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setBounds(966, 0, 34, 30);
        headpanel.add(close);
        close.setForeground(new Color(255, 255, 255));
        close.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		close.setForeground(new Color(192,208,189));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		close.setForeground(new Color(255, 255, 255));
        	}
        });
        close.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0,150));
        panel.setBounds(30, 50, 940, 510);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel headtxt = new JLabel("Electricity Bill Calculation");
        headtxt.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headtxt.setForeground(new Color(255, 255, 255));
        headtxt.setBounds(308, 10, 379, 54);
        panel.add(headtxt);
        
        JLabel meternotxt = new JLabel("Meter Number");
        meternotxt.setForeground(new Color(255, 255, 255));
        meternotxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        meternotxt.setBounds(501, 92, 127, 32);
        panel.add(meternotxt);
        
        JLabel unittxt = new JLabel("Unit");
        unittxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        unittxt.setForeground(new Color(255, 255, 255));
        unittxt.setBounds(68, 260, 96, 32);
        panel.add(unittxt);
        
        JLabel datetxt = new JLabel("Date ");
        datetxt.setForeground(new Color(255, 255, 255));
        datetxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        datetxt.setBounds(68, 344, 59, 32);
        panel.add(datetxt);
        
        
        units = new JTextField();
        units.setBounds(188, 260, 215, 32);
        panel.add(units);
        units.setColumns(10);
        
        JLabel bill_idtxt = new JLabel("Bill Id");
        bill_idtxt.setForeground(Color.WHITE);
        bill_idtxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        bill_idtxt.setBounds(68, 92, 127, 32);
        panel.add(bill_idtxt);
        
        bill_id = new JTextField();
        bill_id.setEditable(false);
        bill_id.setColumns(10);
        bill_id.setBounds(188, 92, 215, 32);
        autoBI();
        panel.add(bill_id);
        
        
        
        meter_no = new JComboBox();
        meter_no.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		try {
					ResultSet rs = c1.s.executeQuery("select * from customer where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'");
					if(rs.next())
					{
						phase.setText(rs.getString("phase"));
						category.setText(rs.getString("user_class"));
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				        Date date = new Date();
				        date2.setText(dateFormat.format(date));
				    

					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

        	}
        	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	}
        });
        meter_no.setModel(new DefaultComboBoxModel(new String[] {"Meter_Number"}));
        meter_no.setEnabled(true);
        fillComboBox();
        meter_no.setBounds(655, 92, 215, 32);
        panel.add(meter_no);
		
		JLabel phasetxt = new JLabel("Phase");
		phasetxt.setForeground(Color.WHITE);
		phasetxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		phasetxt.setBounds(68, 176, 127, 32);
		panel.add(phasetxt);
		
		JLabel categtxt = new JLabel("Meter Type");
		categtxt.setForeground(Color.WHITE);
		categtxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		categtxt.setBounds(501, 176, 127, 32);
		panel.add(categtxt);
		
		phase = new JTextField();
		phase.setEditable(false);
		phase.setColumns(10);
		phase.setBounds(188, 176, 215, 32);
		panel.add(phase);
		
		category = new JTextField();
		category.setEditable(false);
		category.setColumns(10);
		category.setBounds(655, 176, 215, 32);
		panel.add(category);
		
		date2 = new JTextField();
		date2.setColumns(10);
		date2.setBounds(188, 344, 215, 32);
		panel.add(date2);
		
		
		
		
        
        JPanel btn_panel = new JPanel();
		btn_panel.setForeground(new Color(255, 255, 255));
		btn_panel.setBackground(new Color(0, 0, 0,150));
		btn_panel.setBounds(314, 431, 136, 43);
		panel.add(btn_panel);
		btn_panel.setLayout(null);
		
		
		JLabel btn_calc = new JLabel("Calculate");
		btn_calc.setBounds(0, 0, 136, 43);
		btn_panel.add(btn_calc);
		btn_calc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String pay = "Not-Paid";
				String b = units.getText();
				double p1 = Double.parseDouble(b);
				String d1 = null,d2 = null,current_d = null;
				
				String a = meter_no.getSelectedItem().toString();
				int b_id = Integer.parseInt(bill_id.getText());
				String phaseString = phase.getText();
				String categoryString = category.getText();
			   

            	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                Date date = new Date();
                current_d = dateFormat.format(date);
    			
                try{
                	ResultSet rs1 = c1.s.executeQuery("SELECT to_date FROM bill WHERE meter_no = "+a+" order by bill_id DESC LIMIT 1");
                	if(rs1.next())
                	{
                		String last_date = rs1.getString("to_date");
                		int diff1 = dayDiff(current_d,last_date);
                		System.out.println(diff1);
                		if(diff1<28)
                			JOptionPane.showMessageDialog(null,"your bill is upto date1");
                		else {
                			
                			ResultSet rs = c1.s.executeQuery("select * from payment where meter_no = "+a);
                 			if(rs.next())
                 				d2 = rs.getString("date1");
                			
                			int diff = dayDiff(current_d,d2);
                			System.out.println(diff);
                            
                			
                 			if(diff<=30)
                 		    {
                 				JOptionPane.showMessageDialog(null,"your bill is upto date");
                 				int a1 = 3/0;
                 				
                 		    }
                 			else if(diff<= 60 && diff>30)
                 				 calc(0.0,p1);
                 			else if(diff<= 90 && diff>60)
                				 calc(100.0,p1);
                 			else if(diff<= 120 && diff>90)
                				 calc(200.0,p1);
                 			else if(diff<= 150 && diff>120)
                				 calc(300.0,p1);
                 			else if(diff<= 180 && diff>150)
                				 calc(400.0,p1);
                 			else
                 				JOptionPane.showMessageDialog(null,"Connection Cut!!");
                 			
                 			 String q = "insert into bill values('"+a+"','"+b_id+"','"+d2+"','"+current_d+"','"+b+"','"+total+"','"+phaseString+"','"+categoryString+"','"+extra+"','"+pay+"','"+month_charge+"')";
                             
                             connect c1 = new connect();
                             c1.s.executeUpdate(q);
                             JOptionPane.showMessageDialog(null,"Bill Updated");
                       
                		}

                	}
         			
                }
                catch(ArithmeticException e3) {
                	System.out.println("thank you");
                }
                catch(Exception ae){
                    ae.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Bill of meter for this Date is already calculated");
                }
                
			}	
		});
		btn_calc.setHorizontalAlignment(SwingConstants.CENTER);
		btn_calc.setFont(new Font("Calibri", Font.PLAIN, 22));
		btn_calc.setForeground(new Color(255, 255, 255));
		
		JPanel refresh_panel = new JPanel();
		refresh_panel.setLayout(null);
		refresh_panel.setForeground(Color.WHITE);
		refresh_panel.setBackground(new Color(0, 0, 0, 150));
		refresh_panel.setBounds(533, 431, 136, 43);
		panel.add(refresh_panel);
		
		JLabel refresh = new JLabel("Refresh");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				    setVisible(false);
					CalculateBill frame = new CalculateBill();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		refresh.setHorizontalAlignment(SwingConstants.CENTER);
		refresh.setForeground(Color.WHITE);
		refresh.setFont(new Font("Calibri", Font.PLAIN, 22));
		refresh.setBounds(0, 0, 136, 43);
		refresh_panel.add(refresh);
		
		
		
		
		
        
        
        
   
        
        //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
        
        
	}
}
