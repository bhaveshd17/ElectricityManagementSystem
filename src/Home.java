import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000,600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(0, 0, 0));
        panel_3.setBounds(0, 0, 1000, 30);
        contentPane.add(panel_3);
        panel_3.setLayout(null);
        
        /*header*/
        JLabel lblNewLabel = new JLabel("  Electricity Management System - Home");
        lblNewLabel.setBounds(0, 0, 336, 28);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        panel_3.add(lblNewLabel);
        
        /*closing button*/
        JLabel close = new JLabel("x");
        close.setBounds(973, 0, 27, 28);
        close.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(close);
        close.setForeground(new Color(255, 255, 255));
        close.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close these application","confirmation",JOptionPane.YES_NO_OPTION) == 0)
        			Home.this.dispose();
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
        
        /*Menu Bar*/
        JMenuBar menu = new JMenuBar();
        menu.setForeground(new Color(255, 255, 255));
        menu.setBackground(new Color(0, 0, 0,100));
        menu.setBounds(0, 30, 1000, 30);
        contentPane.add(menu);
        
        /*First column*/
        JMenu Home = new JMenu("Home");
        Home.setForeground(new Color(255, 255, 255));
        Home.setBackground(new Color(0, 0, 0,100));
        menu.add(Home);
        
        /*New Customer*/
        JMenuItem newCustomerMenu = new JMenuItem("New Customer");
        newCustomerMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("New Customer"))
                {
                	new NewCustomer().setVisible(true);
                	
                }
        		
        	}
        });
  
        newCustomerMenu.setForeground(new Color(255, 255, 255));
        newCustomerMenu.setBackground(new Color(0, 0, 0,200));
        Home.add(newCustomerMenu);
        
        JSeparator separator = new JSeparator();
        Home.add(separator);
        
        /*Customer Detail*/
        JMenuItem customerDetailMenu = new JMenuItem("Customer Detail");
        customerDetailMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Customer Detail"))
                {
                	new CustomerDetails().setVisible(true);
                	
                }
        	}
        });
        customerDetailMenu.setForeground(new Color(255, 255, 255));
        customerDetailMenu.setBackground(new Color(0, 0, 0,200));
        Home.add(customerDetailMenu);
        
        
       
        
        /*Second column*/
        JMenu Bill = new JMenu("Billing");
        Bill.setForeground(new Color(255, 255, 255));
        Bill.setBackground(new Color(0, 0, 0,100));
        menu.add(Bill);
        
        JMenuItem calculateBillMenu = new JMenuItem("Calculate Bill");
        calculateBillMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Calculate Bill"))
                {
                	new CalculateBill().setVisible(true);
                	
                }
        		
        	}
        });
        calculateBillMenu.setForeground(new Color(255, 255, 255));
        calculateBillMenu.setBackground(new Color(0, 0, 0,200));
        Bill.add(calculateBillMenu);
        
        JSeparator separator_2 = new JSeparator();
        Bill.add(separator_2);
        
        JMenuItem generateBillMenu = new JMenuItem("Generate Bill");
        generateBillMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String msg = e.getActionCommand();
                if(msg.equals("Generate Bill"))
                {
                	new BillGeneration().setVisible(true);
                }
        	}
        });
        generateBillMenu.setForeground(new Color(255, 255, 255));
        generateBillMenu.setBackground(new Color(0, 0, 0,200));
        Bill.add(generateBillMenu);
        
        JMenuItem payBillMenu = new JMenuItem("Pay Bill");
        payBillMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Pay Bill"))
                {
                	new Payment().setVisible(true);
                }
        		        	
        	}
        });
        
        JSeparator separator_3 = new JSeparator();
        Bill.add(separator_3);
        payBillMenu.setForeground(new Color(255, 255, 255));
        payBillMenu.setBackground(new Color(0, 0, 0,200));
        Bill.add(payBillMenu);
        
        JSeparator separator_8 = new JSeparator();
        Bill.add(separator_8);
        
        JMenuItem paymentDetails = new JMenuItem("Payment History");
        paymentDetails.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Payment History"))
                {
                	new PaymentDetails().setVisible(true);
                }
        		
        	}
        });
        paymentDetails.setForeground(new Color(255, 255, 255));
        paymentDetails.setBackground(new Color(0, 0, 0,200));
        Bill.add(paymentDetails);
        
        /*Third Column*/
        JMenu staff = new JMenu("Staff");
        staff.setBackground(new Color(0, 0, 0, 100));
        staff.setForeground(new Color(255, 255, 255));
        menu.add(staff);
        
        JMenuItem addSatffMenu = new JMenuItem("Add Staff");
        addSatffMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String msg = e.getActionCommand();
                if(msg.equals("Add Staff"))
                {
                	new User().setVisible(true);
                }
        	}
        });
        addSatffMenu.setForeground(new Color(255, 255, 255));
        addSatffMenu.setBackground(new Color(0, 0, 0,200));
        staff.add(addSatffMenu);
        
        JSeparator separator_1 = new JSeparator();
        staff.add(separator_1);
        
        JMenuItem staffDetailMenu = new JMenuItem("Staff Records");
        staffDetailMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String msg = e.getActionCommand();
                if(msg.equals("Staff Records"))
                {
                	new StaffDetails().setVisible(true);
                	
                }
        	}
        });
        staffDetailMenu.setForeground(new Color(255, 255, 255));
        staffDetailMenu.setBackground(new Color(0, 0, 0,200));
        staff.add(staffDetailMenu);
        
        
        /*Fourth column*/
        JMenu complaintMenu = new JMenu("Complaint");
        complaintMenu.setBackground(new Color(0, 0, 0, 100));
        complaintMenu.setForeground(new Color(255, 255, 255));
        menu.add(complaintMenu);
        
        JMenuItem complaintsMenu = new JMenuItem("Complaint Form");
        complaintsMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Complaint Form"))
                {
                	new Complaint().setVisible(true);
                	
                }
        		
        	}
        });
        complaintsMenu.setForeground(new Color(255, 255, 255));
        complaintsMenu.setBackground(new Color(0, 0, 0,200));
        complaintMenu.add(complaintsMenu);
        
        JSeparator separator_7 = new JSeparator();
        complaintMenu.add(separator_7);
        
        JMenuItem complaintsDetailMenu = new JMenuItem("Customer Complaints");
        complaintsDetailMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Customer Complaints"))
                {
                	new ShowComplaints().setVisible(true);
                	
                }
        	}
        	
        });
        complaintsDetailMenu.setForeground(new Color(255, 255, 255));
        complaintsDetailMenu.setBackground(new Color(0, 0, 0,200));
        complaintMenu.add(complaintsDetailMenu);
        
        /*Fifth column*/
        JMenu More = new JMenu("More");
        More.setBackground(new Color(0, 0 , 0, 100));
        More.setForeground(new Color(255, 255, 255));
        menu.add(More);
        
        JMenuItem calculatorMenu = new JMenuItem("Calculator");
        calculatorMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
                    Runtime.getRuntime().exec("calc.exe");
                }catch(Exception e1){ }
        	}
        });
        calculatorMenu.setForeground(new Color(255, 255, 255));
        calculatorMenu.setBackground(new Color(0, 0, 0,200));
        More.add(calculatorMenu);
        
        JSeparator separator_4 = new JSeparator();
        More.add(separator_4);
        
        JMenuItem browseMenu = new JMenuItem("Browse");
        browseMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Desktop d = Desktop.getDesktop();
        		try {
					d.browse(new URI("www.google.com"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        browseMenu.setForeground(new Color(255, 255, 255));
        browseMenu.setBackground(new Color(0, 0, 0,200));
        More.add(browseMenu);
        
        JSeparator separator_5 = new JSeparator();
        More.add(separator_5);
        
        JMenuItem notePadMenu = new JMenuItem("NotePad");
        notePadMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try{
                     Runtime.getRuntime().exec("notepad.exe");
                 }catch(Exception e2){ }
        	}
        });
        notePadMenu.setForeground(new Color(255, 255, 255));
        notePadMenu.setBackground(new Color(0, 0, 0,200));
        More.add(notePadMenu);
        
        JSeparator separator_6 = new JSeparator();
        More.add(separator_6);
        
        JMenuItem signOutMenu = new JMenuItem("Sign Out");
        signOutMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String msg = e.getActionCommand();
                if(msg.equals("Sign Out"))
                {
                	if(JOptionPane.showConfirmDialog(null, "Successfully SignOut","confirmation",JOptionPane.YES_NO_OPTION) == 0)

                	new Login().setVisible(true);
                	dispose();
                }
        		
        	}
        });
        signOutMenu.setForeground(new Color(255, 255, 255));
        signOutMenu.setBackground(new Color(0, 0, 0,200));
        More.add(signOutMenu);
        
        
        
        JLabel power_surge_label = new JLabel(" Power Surge");
        power_surge_label.setForeground(new Color(255, 255, 255));
        power_surge_label.setFont(new Font("Segoe UI", Font.BOLD, 70));
        power_surge_label.setBounds(278, 122, 502, 177);
        contentPane.add(power_surge_label);
        
        /*background*/
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);

	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
