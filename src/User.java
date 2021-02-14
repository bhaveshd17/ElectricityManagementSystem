

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class User extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private JPasswordField cpassword;
	private JPasswordField password;
	private JTextField fname;
	private JTextField lname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public User() {
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
        close.setBounds(970, 0, 30, 30);
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
        panel.setBackground(new Color(0, 0, 0, 150));
        panel.setBounds(30, 50, 940, 510);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel headtxt = new JLabel("Add Staff");
        headtxt.setFont(new Font("Segoe UI", Font.BOLD, 30));
        headtxt.setForeground(new Color(255, 255, 255));
        headtxt.setBounds(403, 28, 163, 54);
        panel.add(headtxt);
        
        JLabel usernametxt = new JLabel("Username");
        usernametxt.setForeground(new Color(255, 255, 255));
        usernametxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernametxt.setBounds(55, 200, 106, 32);
        panel.add(usernametxt);
        
        username = new JTextField();
        username.setBounds(184, 200, 215, 32);
        panel.add(username);
        username.setColumns(10);
        
        JLabel passtxt = new JLabel("Password");
        passtxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passtxt.setForeground(new Color(255, 255, 255));
        passtxt.setBounds(55, 270, 106, 32);
        panel.add(passtxt);
        
        password = new JPasswordField();
        password.setBounds(651, 270, 215, 32);
        panel.add(password);
        
        JLabel emailtxt = new JLabel("Email");
        emailtxt.setForeground(new Color(255, 255, 255));
        emailtxt.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        emailtxt.setBounds(478, 200, 88, 32);
        panel.add(emailtxt);

        email = new JTextField();
        email.setColumns(10);
        email.setBounds(651, 200, 215, 32);
        panel.add(email);
        
        cpassword = new JPasswordField();
        cpassword.setBounds(184, 270, 215, 32);
        panel.add(cpassword);
        
        JLabel cnftxt = new JLabel("Confirm Password");
        cnftxt.setForeground(Color.WHITE);
        cnftxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cnftxt.setBounds(478, 270, 152, 32);
        panel.add(cnftxt);
        
        
        
        fname = new JTextField();
        fname.setColumns(10);
        fname.setBounds(184, 130, 215, 32);
        panel.add(fname);
        
        JLabel fname_txt = new JLabel("First Name");
        fname_txt.setForeground(Color.WHITE);
        fname_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fname_txt.setBounds(55, 130, 106, 32);
        panel.add(fname_txt);
        
        lname = new JTextField();
        lname.setColumns(10);
        lname.setBounds(651, 130, 215, 32);
        panel.add(lname);
        
        JLabel lname_txt = new JLabel("Last Name");
        lname_txt.setForeground(Color.WHITE);
        lname_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lname_txt.setBounds(478, 130, 106, 32);
        panel.add(lname_txt);
        
        JLabel category_txt = new JLabel("Category");
        category_txt.setForeground(Color.WHITE);
        category_txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        category_txt.setBounds(55, 340, 106, 32);
        panel.add(category_txt);
        
        JComboBox category = new JComboBox();
        category.setBounds(184, 340, 215, 32);
        category.setModel(new DefaultComboBoxModel(new String[] {"--Select Category--", "Administrator","Cashier"}));
        panel.add(category);
        
        JButton add = new JButton("Add ");
        add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String a = fname.getText();
        		String b = lname.getText();
        		String c = username.getText();
                String d = email.getText();
                String e1 = password.getText();
                String f = cpassword.getText();
                String g = category.getSelectedItem().toString();
                
                
                String q1 = "insert into users values('"+a+"','"+b+"','"+c+"','"+d+"','"+e1+"','"+f+"','"+g+"')";

                try{
                    if(e1.equals(f))
                    {
                    	
                    	connect c1 = new connect();
                        c1.s.executeUpdate(q1);
                        JOptionPane.showMessageDialog(null,"Added Successful!!");
                        setVisible(false);
                    }
                    else
                    	JOptionPane.showMessageDialog(null,"Password and Confirm Password Should be Same");
 	                } catch (Exception exception) {
 	                    JOptionPane.showMessageDialog(null, "Username already exist Or Fill all information");
 	                }
 	            }
 	        });
        add.setBackground(new Color(0, 0, 0,100));
        add.setForeground(new Color(255, 255, 255));
        add.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        add.setBounds(420, 420, 152, 43);
        panel.add(add);
        
        
        
      //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
	}
}
