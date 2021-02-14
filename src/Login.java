
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	
	/*image icon object for username and password */
	private Image img_user = new ImageIcon(Login.class.getResource("user.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
	private Image img_pass = new ImageIcon(Login.class.getResource("lock.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
	
	/*Calling Constructor*/
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 819, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		/*Heading Label*/
		JLabel head = new JLabel("Power Surge");
		head.setForeground(new Color(255, 255, 255));
		head.setFont(new Font("Calibri", Font.BOLD, 34));
		head.setBounds(300, 44, 202, 70);
		contentPane.add(head);
        
		
        JPanel head_panel = new JPanel();
        head_panel.setBackground(new Color(0, 0, 0));
        head_panel.setBounds(0, 0, 819, 33);
        contentPane.add(head_panel);
        head_panel.setLayout(null);
        /*Closing Button*/
        JLabel close = new JLabel("x");
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setBounds(787, 0, 32, 33);
        head_panel.add(close);
        close.setForeground(new Color(255, 255, 255));
        close.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close these application","confirmation",JOptionPane.YES_NO_OPTION) == 0)
        			Login.this.dispose();
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
        
        
		/*Category Panel*/
		JPanel category_panel = new JPanel();
        category_panel.setLayout(null);
        category_panel.setBackground(new Color(0, 0, 0, 100));
        category_panel.setBounds(169, 140, 260, 44);
        contentPane.add(category_panel);
       
        JComboBox category = new JComboBox();
        category.setBounds(24, 11, 211, 22);
        category.setModel(new DefaultComboBoxModel(new String[] {"--Select Category--", "Administrator","Cashier"}));
        category_panel.add(category);
        
        
		
		/*user name panel*/
		JPanel user_panel = new JPanel();
		user_panel.setBackground(new Color(0, 0, 0,100));
		user_panel.setBounds(169, 215, 260, 44);
		contentPane.add(user_panel);
		user_panel.setLayout(null);
		
		user = new JTextField();
		user.setBackground(new Color(255, 255, 255));
		user.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(user.getText().equals("username"))
					user.setText("");
				else
					user.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(user.getText().equals(""))
					user.setText("username");
			}
		});
		user.setText("username");
		user.setBounds(53, 11, 196, 22);
		user_panel.add(user);
		
		JLabel user_label = new JLabel("");
		user_label.setBounds(8, 0, 30, 44);
		user_label.setIcon(new ImageIcon(img_user));
		user_panel.add(user_label);
		
		/*password panel*/
		JPanel password_panel = new JPanel();
		password_panel.setBackground(new Color(0, 0, 0,100));
		password_panel.setBounds(169, 290, 260, 44);
		contentPane.add(password_panel);
		password_panel.setLayout(null);
		
		pass = new JPasswordField();
		pass.setBackground(new Color(255, 255, 255));
		pass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(pass.getText().equals("password"))
				{
					pass.setEchoChar('*');
					pass.setText("");
				}
				else
					pass.selectAll();
					
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(pass.getText().equals(""))
				{
					pass.setText("password");
				}
			}
		});
		pass.setEchoChar('*');
		pass.setEchoChar((char)0);
		pass.setText("password");
		pass.setBounds(53, 11, 196, 22);
		password_panel.add(pass);
		
		JLabel pass_label = new JLabel("");
		pass_label.setBounds(10, 0, 30, 44);
		pass_label.setIcon(new ImageIcon(img_pass));
		password_panel.add(pass_label);
		
		/*Login Button Panel*/
		JPanel login_panel = new JPanel();
		login_panel.setForeground(new Color(255, 255, 255));
		login_panel.setBackground(new Color(0, 0, 0,100));
		login_panel.setBounds(169, 365, 260, 44);
		contentPane.add(login_panel);
		login_panel.setLayout(null);
		
		/*Login Button*/
		JLabel log = new JLabel("Log In");
		log.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
		        try{
		        	/*Creating Instance of Database connection class*/
		            connect c1 = new connect();
		            
		            String a  = user.getText();
		            String c = category.getSelectedItem().toString();
		            String p = pass.getText();
		            String q  = "select * from users where username = '"+a+"' and category = '"+c+"' and password = '"+p+"'";
		            ResultSet rs = c1.s.executeQuery(q);
		            if(rs.next()){
		            	if(category.getSelectedItem().toString().equals("Administrator"))
		            	{
		            		JOptionPane.showMessageDialog(null, "Login Successful");
			                new Home().setVisible(true);
			                dispose();
		            	}
		            	else if(category.getSelectedItem().toString().equals("Cashier"))
		            	{
		            		JOptionPane.showMessageDialog(null, "Login Successful");
			                new Home1().setVisible(true);
			                dispose();
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "Please Enter Valid Information");
		            	}
		            }
		            else if(user.getText().equals("")||user.getText().equals("username")||pass.getText().equals("")||pass.getText().equals("password")||category.getSelectedItem().toString().equals("--Select Category--"))
		            {
		            	JOptionPane.showMessageDialog(null, "Please Fill All Requirement");
		            }
		            else{
		                JOptionPane.showMessageDialog(null, "Invalid login");
		            }
		        }catch(Exception e1){
		            e1.printStackTrace();
		            System.out.println("error: "+e1);
		        }
				
				}

			
		});
		log.setHorizontalAlignment(SwingConstants.CENTER);
		log.setBounds(0, 0, 260, 45);
		log.setFont(new Font("Calibri", Font.PLAIN, 22));
		log.setForeground(new Color(255, 255, 255));
		login_panel.add(log);
		
		
        
       
        
        /*background*/
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(974,494,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,819,494);
        contentPane.add(bgic);
	}

/*main*/	
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Login frame = new Login();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}