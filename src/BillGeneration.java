

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class BillGeneration extends JFrame {

	private JPanel contentPane;
	private JComboBox meter_no;
	private connect c = new connect();

	
	public void fillComboBox() {
		
		try {
			
			String q  = "select * from customer order by meter_no desc";
			ResultSet rs = c.s.executeQuery(q);
			
			while(rs.next())
			{
				meter_no.addItem(rs.getString("meter_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillGeneration frame = new BillGeneration();
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
	public BillGeneration() {
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
        close.setBounds(974, 0, 26, 30);
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
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(30, 101, 587, 446);
        contentPane.add(scrollPane);
        
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setColumns(5);
        scrollPane.setViewportView(text);
        text.setFont(new Font("Calibri", Font.PLAIN, 22));
        text.setBackground(new Color(192, 192, 192));
        
        
        JLabel billtxt = new JLabel("Generate Bill");
        billtxt.setForeground(new Color(255, 255, 255));
        billtxt.setFont(new Font("Segoe UI", Font.BOLD, 26));
        billtxt.setBounds(232, 40, 165, 42);
        contentPane.add(billtxt);
        
        JLabel metertxt = new JLabel("Meter No.");
        metertxt.setForeground(new Color(255, 255, 255));
        metertxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        metertxt.setBounds(696, 143, 81, 23);
        contentPane.add(metertxt);
        
        meter_no = new JComboBox();
        meter_no.setModel(new DefaultComboBoxModel(new String[] {"Meter_Number"}));
        meter_no.setEnabled(true);
        fillComboBox();
        meter_no.setBounds(787, 143, 131, 30);
        contentPane.add(meter_no);
        
        JLabel datetxt = new JLabel("Date");
        datetxt.setForeground(Color.WHITE);
        datetxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        datetxt.setBounds(696, 219, 90, 23);
        contentPane.add(datetxt);
        
        JDateChooser date2 = new JDateChooser();
        date2.setDateFormatString("YYYY-MM-dd");
        date2.setBounds(787, 219, 131, 30);
        contentPane.add(date2);
  
        
        JButton genbtn = new JButton("Generate Bill");
        genbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        genbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
                    String date_from_user = ((JTextField)date2.getDateEditor().getUiComponent()).getText();
                    ResultSet rs1 = c.s.executeQuery("select * from bill where to_date <= "+"'"+date_from_user+"'");                    
                    
                    if(rs1.next())
                    {
                    	text.setText("\t Power Surge Limited\n\t    ELECTRICITY BILL\n\n");
                        text.append("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                        
                        ResultSet rs = c.s.executeQuery("select * from customer where meter_no="+meter_no.getSelectedItem().toString());
                        
                        if(rs.next()){
                            text.append("\n   Customer Name:\t"+rs.getString("fname")+" "+rs.getString("lname"));
                            text.append("\n   Meter Number:\t"+rs.getString("meter_no"));
                            text.append("\n   Address:\t\t"+rs.getString("address"));
                            text.append("\n   State:\t\t"+rs.getString("state"));
                            text.append("\n   City:\t\t"+rs.getString("city"));
                            text.append("\n   Email:\t\t"+rs.getString("email"));
                            text.append("\n   Phone Number:\t"+rs.getString("phone"));
                            text.append("\n");
                            
                        }
                        
                       
                        text.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        String user_class = rs.getString("user_class");
                        rs = c.s.executeQuery("select * from tax where meter_type = "+"'"+user_class+"'");
                        
                        if(rs.next()){
                        	text.append("\n   Meter Type:\t\t"+rs.getString("meter_type"));
                        	text.append("\n   Phase Code:\t\t"+rs.getString("phase"));
                        	text.append("\n   Bill Type:\t\t"+rs.getString("bill_type"));
                        	text.append("\n   Meter Rent:\t\t"+rs.getString("meter_rent"));
                        	text.append("\n   MCB Rent:\t\t"+rs.getString("mcb_rent"));
                        	text.append("\n   Service Tax:\t\t"+rs.getString("service_rent"));
                        	text.append("\n   GST:\t\t"+rs.getString("gst"));
                        	text.append("\n");
                            
                        }
                        
                        text.append("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
    					rs = c.s.executeQuery("select * from customer as c,bill as b where c.meter_no = b.meter_no and c.meter_no = "+meter_no.getSelectedItem().toString()+" order by b.bill_id desc LIMIT 1");
    
                        if(rs.next()){
                        	LocalDate last_month = LocalDate.now().minusMonths(1);
                        	text.append("\n   Last Reading Date :\t"+last_month);
                        	text.append("\n   Last Paid Bill Date:\t"+rs.getString("from_date"));
                        	text.append("\n   Current Readig Date :\t"+rs.getString("to_date"));
                        	text.append("\n   Units Consumed:\t"+rs.getString("units"));
                        	text.append("\n   Extra Charges :\t"+rs.getString("extra_charge"));
                        	text.append("\n   Monthly Charges :\t"+rs.getString("month_charge"));
                        	text.append("\n--------------------------------------------------------------------------------------------------------------------------------------------------------");
                        	text.append("\n   Total Amount :\t"+rs.getString("amount"));
                        	text.append("\n   Payment Status :\t"+rs.getString("payment"));
                        	text.append("\n\n\n");
                        	
                        	text.append("Signature and Stamp\n");
                        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd   HH:mm:ss");  
                    		LocalDateTime now = LocalDateTime.now();  
                        	text.append(dtf.format(now));
                        	text.append("\n....................................\n");
                        	
                        	new Email(rs.getString("email"),text.getText()+"\n\n"+"Pay Using following Link"+"\n"+"https://paytm.com/electricity-bill-payment");
                            
                        	
                        }
                        	
                      
                         
                        
                    }
                    
                    
                    else {
                    	JOptionPane.showMessageDialog(null, "Data Not Found");
                    }
                    
                    
                    
                }catch(Exception e1){
                    e1.printStackTrace();
                    
                }
        	}
        });
        genbtn.setBackground(new Color(0, 0, 0));
        genbtn.setForeground(new Color(255, 255, 255));
        genbtn.setBounds(734, 337, 165, 37);
        contentPane.add(genbtn);
        

        JButton print_btn = new JButton("Print Bill");
        print_btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        print_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	        int num =0;
            		try
            		{
            			text.print();
            			FileOutputStream fout = new FileOutputStream("receipt number.txt",true);
            		
            			num++;		
            			fout.write(num);
            			fout.close();
            			
            		}
            		catch(Exception e1)
            		{
            			e1.printStackTrace();
            		}
        	}
        });
        print_btn.setForeground(Color.WHITE);
        print_btn.setBackground(Color.BLACK);
        print_btn.setBounds(734, 420, 165, 37);
        contentPane.add(print_btn);
        
       
        
        
        //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setFont(new Font("Calibri", Font.PLAIN, 22));
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
        
       
    
        
        
	}
}
