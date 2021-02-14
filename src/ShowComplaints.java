

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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ShowComplaints extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField noOfComp;
	private JComboBox meter_no;
	private JComboBox location;
	connect c = new connect();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowComplaints frame = new ShowComplaints();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillMeterNo() {
		
		try {
			
			String q  = "select * from customer order by meter_no desc";
			ResultSet rs = c.s.executeQuery(q);
			
			while(rs.next())
				meter_no.addItem(rs.getString("meter_no"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void fillLocation() {
		
		try {
			
			String q = "select * from city";
			ResultSet rs = c.s.executeQuery(q);
			
			rs = c.s.executeQuery(q);
			while(rs.next())
				location.addItem(rs.getString("city"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ShowComplaints() {
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
        
        
        
        
        meter_no = new JComboBox();
        meter_no.setModel(new DefaultComboBoxModel(new String[] {"Meter_Number"}));
        meter_no.setEnabled(true);
        fillMeterNo();
        meter_no.setBounds(760, 277, 230, 32);
        contentPane.add(meter_no);
        
        
        
        noOfComp = new JTextField();
        noOfComp.setEditable(false);
        noOfComp.setColumns(10);
        noOfComp.setBounds(792, 202, 165, 32);
        contentPane.add(noOfComp);
        
        JLabel comptxt = new JLabel("Number Of Complaints");
        comptxt.setForeground(Color.WHITE);
        comptxt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comptxt.setBounds(792, 160, 165, 32);
        contentPane.add(comptxt);
        
        location = new JComboBox();
        location.setModel(new DefaultComboBoxModel(new String[] {"Location"}));
        location.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		try {
					ResultSet rs1 = c.s.executeQuery("select count(*) from complaint where city = "+"'"+location.getSelectedItem().toString()+"'");
					if(rs1.next())
					{
						noOfComp.setText(rs1.getString("count(*)"));
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

        	}
        	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	}
        });
        location.setEnabled(true);
        fillLocation();
        location.setBounds(792, 118, 165, 32);
        contentPane.add(location);
        
        JLabel locatxt = new JLabel("Select Location");
        locatxt.setForeground(Color.WHITE);
        locatxt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        locatxt.setBounds(792, 76, 127, 32);
        contentPane.add(locatxt);
        
       
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(new Dimension(12, 4));
        scrollPane.setBounds(20, 54, 730, 526);
        contentPane.add(scrollPane);
        
       
        
        
        table = new JTable();
        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, null, Color.LIGHT_GRAY, null));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        table.setRowHeight(30);
        table.setForeground(new Color(0,0,0));
        table.setBackground(new Color(220, 220, 220));
        scrollPane.setViewportView(table);
        try{
            
            String s1 = "select id ,meter_no as MeterNumber, complaints as Complaints,city as Location from complaint";
            ResultSet rs  = c.s.executeQuery(s1);
            table.setModel(new DefaultTableModel(
            	new Object[][] {
            	},
            	new String[] {
            		"id", "MeterNumber", "Complaints", "Location"
            	}
            ));
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.getColumnModel().getColumn(0).setPreferredWidth(32);
            table.getColumnModel().getColumn(2).setPreferredWidth(207);
            table.getColumnModel().getColumn(3).setPreferredWidth(58);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        JPanel btn_panel = new JPanel();
        btn_panel.setLayout(null);
        btn_panel.setForeground(Color.WHITE);
        btn_panel.setBackground(new Color(0, 0, 0));
        btn_panel.setBounds(760, 319, 109, 32);
        contentPane.add(btn_panel);
        
        JLabel search = new JLabel("Search");
        search.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try{
                    
                    String s1 = "select id ,meter_no as MeterNumber, complaints as Complaints,city as Location from complaint where meter_no = "+meter_no.getSelectedItem().toString();
                    ResultSet rs  = c.s.executeQuery(s1);
                    table.setModel(new DefaultTableModel(
                    	new Object[][] {
                    	},
                    	new String[] {
                    		"id", "MeterNumber", "Complaints", "Location"
                    	}
                    ));
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    table.getColumnModel().getColumn(0).setPreferredWidth(32);
                    table.getColumnModel().getColumn(2).setPreferredWidth(207);
                    table.getColumnModel().getColumn(3).setPreferredWidth(58);
                }
                catch(Exception e1){
                    e1.printStackTrace();
                }
        		
        	}
        });
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Calibri", Font.PLAIN, 16));
        search.setBounds(0, 0, 109, 32);
        btn_panel.add(search);
        
     
        
        JPanel btn_panel_1 = new JPanel();
        btn_panel_1.setLayout(null);
        btn_panel_1.setForeground(Color.WHITE);
        btn_panel_1.setBackground(Color.BLACK);
        btn_panel_1.setBounds(879, 319, 109, 32);
        contentPane.add(btn_panel_1);
        
        JLabel solveComp = new JLabel("Complaint Solved");
        solveComp.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try{
        			if(meter_no.getSelectedItem().toString().equals("Meter_Number"))
                    {
                	   JOptionPane.showMessageDialog(null, "Enter Meter Number To Search Complaint");
                    }
        			
                    String s1 = "delete from complaint where meter_no = "+"'"+meter_no.getSelectedItem().toString()+"'";
                    c.s.executeUpdate(s1);
                    JOptionPane.showMessageDialog(null, "Complaint Resolve");
   
                    
                }
                catch(Exception e1){
                    e1.printStackTrace();
                }
        	}
        });
        solveComp.setHorizontalAlignment(SwingConstants.CENTER);
        solveComp.setForeground(Color.WHITE);
        solveComp.setFont(new Font("Calibri", Font.PLAIN, 14));
        solveComp.setBounds(0, 0, 109, 32);
        btn_panel_1.add(solveComp);
        
        JPanel btn_panel_2 = new JPanel();
        btn_panel_2.setLayout(null);
        btn_panel_2.setForeground(Color.WHITE);
        btn_panel_2.setBackground(Color.BLACK);
        btn_panel_2.setBounds(824, 542, 109, 32);
        contentPane.add(btn_panel_2);
        
        JLabel refresh = new JLabel("Refresh");
        refresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
        		new ShowComplaints().setVisible(true);
        	}
        });
        refresh.setHorizontalAlignment(SwingConstants.CENTER);
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font("Calibri", Font.PLAIN, 16));
        refresh.setBounds(0, 0, 109, 32);
        btn_panel_2.add(refresh);

        
        //background
      	ImageIcon bg =  new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
      	Image i3 = bg.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel bgic = new JLabel(icc3);
        bgic.setBounds(0,0,1000,600);
        contentPane.add(bgic);
        
       
	}
}
