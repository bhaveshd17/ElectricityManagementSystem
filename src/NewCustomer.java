

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JDateChooser;

public class NewCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField fname;
	private JTextField lname;
	private JTextField city;
	private JTextField state;
	private JTextField pin;
	private JTextField meter_no;
	private JTextField email;
	private JTextField deposit;
	private JTextField phone;
	private JTextField adhar;
	private JTextField date1;
	
	public void autoMN() {
		int last_meter_no;
		try {
			connect c1 = new connect();
			String str = "select max(meter_no) from customer";
			ResultSet rs = c1.s.executeQuery(str);
			
			if(rs.next())
			{
				last_meter_no = rs.getInt(1);
				last_meter_no++;
				meter_no.setText(Integer.toString(last_meter_no));
			}
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
	}

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
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
	public NewCustomer() {
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
        close.setBounds(962, 0, 38, 30);
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
        scrollPane.setBounds(30, 50, 940, 528);
        contentPane.add(scrollPane);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        scrollPane.setViewportView(panel);
        
        fname = new JTextField();
        fname.setColumns(10);
        
        JLabel pintxt = new JLabel("Pin Code");
        pintxt.setVerticalAlignment(SwingConstants.TOP);
        pintxt.setForeground(Color.WHITE);
        pintxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel addresstxt = new JLabel("Address");
        addresstxt.setVerticalAlignment(SwingConstants.TOP);
        addresstxt.setForeground(Color.WHITE);
        addresstxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel fnametxt = new JLabel("First Name");
        fnametxt.setVerticalAlignment(SwingConstants.TOP);
        fnametxt.setForeground(Color.WHITE);
        fnametxt.setFont(new Font("Sitka Heading", Font.PLAIN, 20));
        
        lname = new JTextField();
        lname.setColumns(10);
        
        JLabel personaltxt = new JLabel("Personal Details ");
        personaltxt.setVerticalAlignment(SwingConstants.TOP);
        personaltxt.setForeground(Color.WHITE);
        personaltxt.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
        
        JLabel lnametxt = new JLabel("Last Name");
        lnametxt.setVerticalAlignment(SwingConstants.TOP);
        lnametxt.setForeground(Color.WHITE);
        lnametxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel citytxt = new JLabel("City");
        citytxt.setVerticalAlignment(SwingConstants.TOP);
        citytxt.setForeground(Color.WHITE);
        citytxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel statetxt = new JLabel("State");
        statetxt.setVerticalAlignment(SwingConstants.TOP);
        statetxt.setForeground(Color.WHITE);
        statetxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        city = new JTextField();
        city.setColumns(10);
        
        state = new JTextField();
        state.setColumns(10);
        
        JLabel user_classtxt = new JLabel("Meter Type");
        user_classtxt.setVerticalAlignment(SwingConstants.TOP);
        user_classtxt.setForeground(Color.WHITE);
        user_classtxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        pin = new JTextField();
        pin.setColumns(10);
        
        JTextArea address = new JTextArea();
        address.setRows(5);
        
        JLabel userreqtxt = new JLabel("User Requirment");
        userreqtxt.setVerticalAlignment(SwingConstants.TOP);
        userreqtxt.setForeground(Color.WHITE);
        userreqtxt.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
        
        JComboBox phase = new JComboBox();
        phase.setToolTipText("");
        phase.setMaximumRowCount(5);
        phase.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
        phase.setEditable(true);
        phase.setModel(new DefaultComboBoxModel(new String[] {"--Select--", "1 phase", "3 phase"}));
		
        
        JLabel phasetxt = new JLabel("Type of Phase");
        phasetxt.setVerticalAlignment(SwingConstants.TOP);
        phasetxt.setForeground(Color.WHITE);
        phasetxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        
        JComboBox user_class = new JComboBox();
        phase.setToolTipText("");
        phase.setMaximumRowCount(5);
        user_class.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
        user_class.setEditable(true);
        user_class.setModel(new DefaultComboBoxModel(new String[] {"--Select--", "Residential", "Residential+Commercial", "Commercial", "Industrial"}));

        
        JLabel emailtxt = new JLabel("Email Id");
        emailtxt.setForeground(Color.WHITE);
        emailtxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel additionaltxt = new JLabel("Additional Details");
        additionaltxt.setForeground(Color.WHITE);
        additionaltxt.setFont(new Font("Sitka Subheading", Font.BOLD, 25));
        
        JLabel deposittxt = new JLabel("Deposite (in Rs)");
        deposittxt.setForeground(Color.WHITE);
        deposittxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel datetxt = new JLabel("Date");
        datetxt.setForeground(Color.WHITE);
        datetxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        meter_no = new JTextField();
        meter_no.setEditable(false);
        autoMN();
        meter_no.setFont(new Font("Tahoma", Font.PLAIN, 12));
        meter_no.setColumns(10);
        
        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 12));
        email.setColumns(10);
        
        deposit = new JTextField();
        deposit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        deposit.setColumns(10);
        
        JLabel phonetxt = new JLabel("Phone No.");
        phonetxt.setForeground(Color.WHITE);
        phonetxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        JLabel metertxt = new JLabel("Meter no.");
        metertxt.setForeground(Color.WHITE);
        metertxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        phone = new JTextField();
        phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phone.setColumns(10);
        
        JLabel adhartxt = new JLabel("Adhar No.");
        adhartxt.setForeground(Color.WHITE);
        adhartxt.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
        
        adhar = new JTextField();
        adhar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        adhar.setColumns(10);
        

        date1 = new JTextField();
        date1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        date1.setColumns(10);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = new Date();
        date1.setText(dateFormat.format(date));
        
        JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			    String a = fname.getText();
				String b = lname.getText();
				String c  = address.getText();
				String d = city.getText();
			    String ef = pin.getText();
				String g = state.getText();
                String h = user_class.getSelectedItem().toString();
                String i = phase.getSelectedItem().toString();
                String j = meter_no.getText();
                String k = adhar.getText();
                String l = email.getText();
                String m = deposit.getText();
                String n = phone.getText();
                String o= date1.getText();
                String q1 = "insert into customer values('"+a+"','"+b+"','"+c+"','"+d+"','"+ef+"','"+g+"','"+h+"','"+i+"', '"+j+"','"+k+"','"+l+"','"+m+"','"+n+"','"+o+"')";
                String q2 = "insert into payment values('"+o+"','"+j+"')";
                System.out.println(n.length());
               try{
                   connect c1 = new connect();
                   if(a==""||b==""||c==""||d==""||ef==""||g==""||h=="--Select--"||i=="--Select--"||j==""||k==""||l==""||m==""||n==""||o=="")
                	   JOptionPane.showMessageDialog(null, "Fill All Input"); 
                   else if(n.length() != 10)
                	   JOptionPane.showMessageDialog(null, "Enter 10 digit Phone Number");
                   else if(k.length() != 12)
                	   JOptionPane.showMessageDialog(null, "Enter 12 digit Adhar Number");
                   else {
                	   c1.s.executeUpdate(q1);
                       c1.s.executeUpdate(q2);
                       JOptionPane.showMessageDialog(null,"Registration Successful!!");
                       setVisible(false);
                   }
                   
	                } catch (Exception exception) {
	                    JOptionPane.showMessageDialog(null, "Enter Correct Value");
	                }
	            }
	        });
        button.setForeground(new Color(0, 0, 0));
        button.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 25));
        button.setBackground(new Color(255, 255, 255,150));
        
        
      //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
        
        
        
        
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(personaltxt, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(fnametxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        			.addGap(64)
        			.addComponent(fname, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(109)
        			.addComponent(lnametxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        			.addGap(54)
        			.addComponent(lname, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(addresstxt, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        			.addGap(73)
        			.addComponent(address, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(109)
        			.addComponent(citytxt, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        			.addGap(75)
        			.addComponent(city, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(pintxt, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        			.addGap(73)
        			.addComponent(pin, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(113)
        			.addComponent(statetxt, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
        			.addGap(90)
        			.addComponent(state, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(userreqtxt, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(user_classtxt)
        			.addGap(64)
        			.addComponent(user_class, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(109)
        			.addComponent(phasetxt, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
        			.addGap(32)
        			.addComponent(phase, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(additionaltxt, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(emailtxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        			.addGap(99)
        			.addComponent(email, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(74)
        			.addComponent(phonetxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        			.addGap(54)
        			.addComponent(phone, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(deposittxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
        			.addGap(41)
        			.addComponent(deposit, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(74)
        			.addComponent(adhartxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        			.addGap(54)
        			.addComponent(adhar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(40)
        			.addComponent(datetxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addComponent(date1, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
        			.addGap(74)
        			.addComponent(metertxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
        			.addGap(44)
        			.addComponent(meter_no, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(384)
        			.addComponent(button, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(21)
        			.addComponent(personaltxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        			.addGap(33)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(fnametxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(fname, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(lnametxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lname, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(47)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(addresstxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(4)
        					.addComponent(address, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(citytxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(city, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(36)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(pintxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(pin, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(statetxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(state, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(75)
        			.addComponent(userreqtxt)
        			.addGap(40)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(user_classtxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(user_class, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(phasetxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(phase, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(76)
        			.addComponent(additionaltxt, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(emailtxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(email, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(phonetxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(phone, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(47)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(deposittxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(deposit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(adhartxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(adhar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(47)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(datetxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addComponent(date1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(2)
        					.addComponent(metertxt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(1)
        					.addComponent(meter_no, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
        			.addGap(68)
        			.addComponent(button))
        );
        panel.setLayout(gl_panel);
        
        
        
        

	}
}
