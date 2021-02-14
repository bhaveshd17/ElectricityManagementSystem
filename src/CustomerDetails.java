

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class CustomerDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox meter_no;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDetails frame = new CustomerDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillComboBox() {
		
		try {
			connect c1 = new connect();
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

	/**
	 * Create the frame.
	 */
	public CustomerDetails() {
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
        
        //header
        JLabel lblNewLabel = new JLabel("Electricity Management System");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(0, 0, 263, 28);
        panel_3.add(lblNewLabel);
        
        //closing button
        JLabel close = new JLabel("x");
        close.setHorizontalAlignment(SwingConstants.CENTER);
        close.setBounds(972, 0, 28, 28);
        panel_3.add(close);
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
        
        
        JPanel btn_panel = new JPanel();
        btn_panel.setBounds(271, 46, 109, 26);
        btn_panel.setForeground(new Color(255, 255, 255));
        btn_panel.setBackground(new Color(0, 0, 0,100));
        contentPane.add(btn_panel);
        btn_panel.setLayout(null);
        
        meter_no = new JComboBox();
        meter_no.setModel(new DefaultComboBoxModel(new String[] {"Meter_Number"}));
        meter_no.setEnabled(true);
        fillComboBox();
        meter_no.setBounds(20, 46, 231, 26);
        contentPane.add(meter_no);
        
        JLabel search = new JLabel("Search");
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try{
                    connect c1  = new connect();
                    String s2 = "select fname as Name, lname as Surname,address as Address,city as City,pin as Pin,state as State,"
                    		+ "user_class as Class,phase as Phase,meter_no as MeternNumber,adhar as AdharNumber,email as Email,"
                    		+ "deposit as Deposit,phone as Contact ,date1 as Date from customer where meter_no = "+meter_no.getSelectedItem().toString();
                    ResultSet rs1  = c1.s.executeQuery(s2);
                    table.setModel(DbUtils.resultSetToTableModel(rs1));
                    
                }
                catch(Exception e1){
                    e1.printStackTrace();
                }
        		
        	}
        });
        search.setBounds(0, 0, 109, 25);
        search.setFont(new Font("Calibri", Font.PLAIN, 16));
        search.setForeground(new Color(255, 255, 255));
        btn_panel.add(search);
        
        
        
        JPanel btn2_panel = new JPanel();
        btn2_panel.setLayout(null);
        btn2_panel.setForeground(Color.WHITE);
        btn2_panel.setBackground(new Color(0, 0, 0, 100));
        btn2_panel.setBounds(871, 46, 109, 26);
        contentPane.add(btn2_panel);
        
        JLabel remove_user = new JLabel("Remove");
        remove_user.setHorizontalAlignment(SwingConstants.CENTER);
        remove_user.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try{
        			if(meter_no.getSelectedItem().toString().equals("Meter_Number"))
                    {
                	   JOptionPane.showMessageDialog(null, "Enter The Value to be Removed");
                    }
        			else if(JOptionPane.showConfirmDialog(null, "Do you really want to remove","confirmation",JOptionPane.YES_NO_OPTION) == 0)
            		{
            			connect c1  = new connect();
                        String s1 = "delete from customer where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'";
                        String s2 = "delete from payment where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'";
                        c1.s.executeUpdate(s1);
                        c1.s.executeUpdate(s2);
                        JOptionPane.showMessageDialog(null, "Removed Sucessfully");
            		}
   
                    
                }
                catch(Exception e2){
                    e2.printStackTrace();
                }
        		
        	}
        });
        remove_user.setForeground(Color.WHITE);
        remove_user.setFont(new Font("Calibri", Font.PLAIN, 16));
        remove_user.setBounds(0, 0, 109, 26);
        btn2_panel.add(remove_user);       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(new Dimension(12, 4));
        scrollPane.setBounds(20, 89, 960, 491);
        contentPane.add(scrollPane);
        
        
        JPanel btn3_panel = new JPanel();
        btn3_panel.setLayout(null);
        btn3_panel.setForeground(Color.WHITE);
        btn3_panel.setBackground(new Color(0, 0, 0, 100));
        btn3_panel.setBounds(741, 46, 109, 26);
        contentPane.add(btn3_panel);
        
        JLabel refresh = new JLabel("Refresh");
        refresh.setHorizontalAlignment(SwingConstants.CENTER);
        refresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try{
                    connect c1  = new connect();
                    String s1 = "select fname as Name, lname as Surname,address as Address,city as City,pin as Pin,state as State,"
                    		+ "user_class as Class,phase as Phase,meter_no as MeternNumber,adhar as AdharNumber,email as Email,"
                    		+ "deposit as Deposit,phone as Contact ,date1 as Date from customer";
                    ResultSet rs  = c1.s.executeQuery(s1);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    new CustomerDetails().setVisible(true);
                    dispose();
                }
                catch(Exception e1){
                    e1.printStackTrace();
                }
        		
        	}
        });
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font("Calibri", Font.PLAIN, 16));
        refresh.setBounds(0, 0, 109, 26);
        btn3_panel.add(refresh);
        
        table = new JTable();
        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, Color.LIGHT_GRAY, null));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        table.setRowHeight(30);
        table.setForeground(new Color(0,0,0));
        table.setBackground(new Color(220, 220, 220));
        scrollPane.setViewportView(table);
        try{
            connect c1  = new connect();
            String s1 = "select fname as Name, lname as Surname,address as Address,city as City,pin as Pin,state as State,"
            		+ "user_class as Class,phase as Phase,meter_no as MeternNumber,adhar as AdharNumber,email as Email,"
            		+ "deposit as Deposit,phone as Contact ,date1 as Date from customer";
            ResultSet rs  = c1.s.executeQuery(s1);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
       
        
      //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
       
	}
}
