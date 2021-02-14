

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class Payment extends JFrame {

	private JPanel contentPane;
	private connect c2 = new connect();
	private JTextField date2;
	private JTextField date1;
	private JTextField due_amt;
	private JTextField extra_charge;
	private JTextField meter_no;
	private JComboBox adhar_no;
	private int billid;
	private JTextField month_charge;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
			
			String q  = "select * from customer order by adhar desc";
			ResultSet rs = c2.s.executeQuery(q);
			
			while(rs.next())
			{
				adhar_no.addItem(rs.getString("adhar"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Payment() {
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
        
        JLabel headtxt = new JLabel("Payment");
        headtxt.setHorizontalAlignment(SwingConstants.CENTER);
        headtxt.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headtxt.setForeground(new Color(255, 255, 255));
        headtxt.setBounds(308, 10, 379, 54);
        panel.add(headtxt);
        
        JLabel meternotxt = new JLabel("Meter Number");
        meternotxt.setForeground(new Color(255, 255, 255));
        meternotxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        meternotxt.setBounds(501, 92, 127, 32);
        panel.add(meternotxt);
        
        JLabel adhartxt = new JLabel("Adhar Number");
        adhartxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        adhartxt.setForeground(new Color(255, 255, 255));
        adhartxt.setBounds(68, 92, 145, 32);
        panel.add(adhartxt);
        
        JLabel datetxt = new JLabel("Due Date");
        datetxt.setForeground(new Color(255, 255, 255));
        datetxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        datetxt.setBounds(68, 339, 97, 32);
        panel.add(datetxt);
        
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFrom.setBounds(188, 339, 48, 32);
		panel.add(lblFrom);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTo.setBounds(360, 339, 43, 32);
		panel.add(lblTo);
		
		date2 = new JTextField();
		date2.setColumns(10);
		date2.setBounds(403, 339, 113, 32);
		panel.add(date2);

		date1 = new JTextField();
		date1.setColumns(10);
		date1.setBounds(246, 339, 113, 32);
		panel.add(date1);
		
		adhar_no = new JComboBox();
		adhar_no.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		try {
					ResultSet rs = c2.s.executeQuery("select * from customer as c,bill as b where c.meter_no = b.meter_no and adhar = "+adhar_no.getSelectedItem().toString()+" order by b.bill_id desc LIMIT 1");
					if(rs.next())
					{
						meter_no.setText(rs.getString("meter_no"));
						due_amt.setText(rs.getString("amount"));
						extra_charge.setText(rs.getString("extra_charge"));
						date1.setText(rs.getString("from_date"));
						date2.setText(rs.getString("to_date"));
						billid = Integer.parseInt(rs.getString("bill_id"));
						month_charge.setText(rs.getString("month_charge"));

					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

        	}
        	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	}
        });
		adhar_no.setModel(new DefaultComboBoxModel(new String[] {"Enter Adhar Number"}));
	    fillComboBox();
		adhar_no.setBounds(213, 92, 215, 32);
		panel.add(adhar_no);
	
		
		JLabel due_amt_txt = new JLabel("Amount");
		due_amt_txt.setForeground(Color.WHITE);
		due_amt_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		due_amt_txt.setBounds(68, 280, 145, 32);
		panel.add(due_amt_txt);
		
		due_amt = new JTextField();
		due_amt.setColumns(10);
		
		due_amt.setBounds(213, 280, 215, 32);
		panel.add(due_amt);
		
		JLabel extra_charge_txt = new JLabel("Extra Charges");
		extra_charge_txt.setForeground(Color.WHITE);
		extra_charge_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		extra_charge_txt.setBounds(68, 161, 145, 32);
		panel.add(extra_charge_txt);
		
		extra_charge = new JTextField();
		extra_charge.setColumns(10);
		extra_charge.setBounds(213, 161, 215, 32);
		panel.add(extra_charge);
		
		meter_no = new JTextField();
		meter_no.setColumns(10);
		meter_no.setBounds(661, 92, 215, 32);
		panel.add(meter_no);
		
		JLabel month_charge_txt = new JLabel("Due Charges");
		month_charge_txt.setForeground(Color.WHITE);
		month_charge_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		month_charge_txt.setBounds(68, 220, 145, 32);
		panel.add(month_charge_txt);
		
		month_charge = new JTextField();
		month_charge.setColumns(10);
		month_charge.setBounds(213, 220, 215, 32);
		panel.add(month_charge);
		
	

		
        
        JPanel btn_panel = new JPanel();
		btn_panel.setForeground(new Color(255, 255, 255));
		btn_panel.setBackground(new Color(0, 0, 0));
		btn_panel.setBounds(249, 418, 267, 43);
		panel.add(btn_panel);
		btn_panel.setLayout(null);
		
		
		
		JLabel btn_pay = new JLabel("Pay Bill");
		btn_pay.setBounds(0, 0, 267, 43);
		btn_panel.add(btn_pay);
		btn_pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c2.s.executeUpdate("update bill set payment = 'Paid' where bill_id = "+billid);
					c2.s.executeUpdate("update payment set date1 = "+"'"+date2.getText()+"'"+" where meter_no = "+"'"+meter_no.getText()+"'");
					JOptionPane.showMessageDialog(null,"Payment Successfull");
					setVisible(false);
					new  Payment().setVisible(true);
					                                                         
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
      
       
			}
		        

			
		});
		btn_pay.setHorizontalAlignment(SwingConstants.CENTER);
		btn_pay.setFont(new Font("Calibri", Font.PLAIN, 22));
		btn_pay.setForeground(new Color(255, 255, 255));
		
		
		
		
		
        
        
        
   
        
      //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
        
        
	}
}
