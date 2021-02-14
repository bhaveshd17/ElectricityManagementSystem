

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JTextArea;

public class Complaint extends JFrame {

	private JPanel contentPane;
	private JTextField to_email;
	private JComboBox meter_no;
	
	connect c1 = new connect();
	private JTextField compid;
	
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


    public void autoBI() {
		int last_comp_id;
		try {
			connect c1 = new connect();
			String str = "select max(id) from complaint";
			ResultSet rs = c1.s.executeQuery(str);
			
			if(rs.next())
			{
				last_comp_id = rs.getInt(1);
				last_comp_id++;
				compid.setText(Integer.toString(last_comp_id));
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
					Complaint frame = new Complaint();
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
	public Complaint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 500,400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JPanel headpanel = new JPanel();
        headpanel.setBackground(new Color(0, 0, 0));
        headpanel.setBounds(0, 0, 500, 30);
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
        close.setBounds(470, 1, 30, 29);
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
        
        
        JLabel metertxt = new JLabel("Meter No.");
        metertxt.setForeground(new Color(255, 255, 255));
        metertxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        metertxt.setBounds(90, 112, 81, 23);
        contentPane.add(metertxt);
        
        meter_no = new JComboBox();
        meter_no.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		try {
					ResultSet rs1 = c1.s.executeQuery("select * from customer where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'");
					if(rs1.next())
					{
						to_email.setText(rs1.getString("email"));
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
        meter_no.setBounds(208, 111, 189, 30);
        contentPane.add(meter_no);
        

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblEmail.setBounds(90, 172, 81, 23);
        contentPane.add(lblEmail);
        
        
        to_email = new JTextField();
        to_email.setBounds(208, 172, 189, 30);
        to_email.setColumns(10);
        contentPane.add(to_email);
        
        JLabel cmptxt = new JLabel("Complaint");
        cmptxt.setForeground(Color.WHITE);
        cmptxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cmptxt.setBounds(90, 228, 81, 23);
        contentPane.add(cmptxt);
        
        JTextArea complaint = new JTextArea();
        complaint.setBounds(208, 228, 189, 54);
        contentPane.add(complaint);
        
        JLabel comptxt1 = new JLabel("Complaint Id");
        comptxt1.setForeground(Color.WHITE);
        comptxt1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        comptxt1.setBounds(90, 54, 108, 23);
        contentPane.add(comptxt1);
        
        compid = new JTextField();
        compid.setEditable(false);
        compid.setColumns(10);
        compid.setBounds(208, 54, 189, 30);
        autoBI();
        contentPane.add(compid);
        
        
        
        
        JButton btn = new JButton("save");
        btn.setFont(new Font("Calibri", Font.PLAIN, 12));
        btn.setForeground(new Color(255, 255, 255));
        btn.setBackground(new Color(0, 0, 0));
        btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String meterNumber = meter_no.getSelectedItem().toString();
            		String complaintTxt = complaint.getText();
            		String idStr = compid.getText();
            		int idInt = Integer.parseInt(idStr);
            		String pin,city;
        			ResultSet rs = c1.s.executeQuery("select * from customer where meter_no = "+"'"+meterNumber+"'");
        			if(rs.next()) {
        				pin = rs.getString("pin");
        				rs = c1.s.executeQuery("select * from city where pin = "+"'"+pin+"'");
        				if(rs.next()) {
        					city = rs.getString("city");
        					c1.s.executeUpdate("insert into complaint values('"+meterNumber+"','"+complaintTxt+"','"+city+"','"+idInt+"')");
                			JOptionPane.showMessageDialog(null, "Complaint Saved");
        				}
        			}
            		
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		Email email = new Email(to_email.getText(),"Your Complaint - "+complaint.getText() +" is Recorded, Sorry for the inconvenience. After resolving problems we will inform you."+"\n\n"+"THANK YOU,PowerSurge");
        	}
        });
        btn.setBounds(129, 330, 89, 30);
        contentPane.add(btn);
        
        
        JButton btn2 = new JButton("Refresh");
        btn2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Complaint().setVisible(true);
        		dispose();
        	}
        });
        btn2.setFont(new Font("Calibri", Font.PLAIN, 12));
        btn2.setForeground(Color.WHITE);
        btn2.setBackground(Color.BLACK);
        btn2.setBounds(257, 330, 89, 30);
        contentPane.add(btn2);
        
        
        //background
        ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
        Image i3 = bg.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,500,400);
        contentPane.add(bgic);
        
       
       
        
        
	}
}
