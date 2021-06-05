import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyProject extends JFrame {
	
	Connection conn = null;
	PreparedStatement state = null;
	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	int id = -1;
	
	ResultSet result = null;
	
	JPanel upPanel = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel fnameL = new JLabel("First Name: ");
	JLabel lnameL = new JLabel("Last Name: ");
	JLabel sexL = new JLabel("Gender: ");
	JLabel ageL = new JLabel("Age: ");
	JLabel salaryL = new JLabel("Salary: ");
	
	JTextField fnameTF = new JTextField();
	JTextField lnameTF = new JTextField();
	JTextField ageTF = new JTextField();
	JTextField salaryTF = new JTextField();
	
	String[] item = {"Male", "Female"};
	
	JComboBox<String> sexCombo = new JComboBox<String>(item);
	 
	JButton addBtn = new JButton("Add");
	JButton deleteBtn = new JButton("Delete");
	JButton editBtn = new JButton("Edit");
	JButton searchBtn = new JButton("Search");
	
	JComboBox<String> searchCombo =  new JComboBox<String>();
	
	public MyProject() {
		this.setSize(500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));
		
		//up panel----------------------------------------------------------
		upPanel.setLayout(new GridLayout(5, 2));
		
		upPanel.add(fnameL);
		upPanel.add(fnameTF);
		
		upPanel.add(lnameL);
		upPanel.add(lnameTF);
		
		upPanel.add(sexL);
		upPanel.add(sexCombo);
		
		upPanel.add(ageL);
		upPanel.add(ageTF);
		
		upPanel.add(salaryL);
		upPanel.add(salaryTF);
		
		this.add(upPanel);
		
		//mid panel----------------------------------------------------------
		
		midPanel.add(addBtn);
		midPanel.add(editBtn);
		midPanel.add(deleteBtn);
		midPanel.add(searchCombo);
		midPanel.add(searchBtn);
		
		this.add(midPanel);
		deleteBtn.addActionListener(new DeleteAction());
		
		DBHelper.fillCombo(searchCombo);
		
		searchBtn.addActionListener(new SearchAction());
		
		//----------------------------------------------------------
		
		addBtn.addActionListener(new AddAction());
		
		this.add(downPanel);
		downPanel.add(scroller);
		scroller.setPreferredSize(new Dimension(450,160));
		table.setModel(DBHelper.getAllData());
		table.addMouseListener(new TableListener());
		
		this.setVisible(true);
		
	}
	
	class SearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem = searchCombo.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			
			conn = DBHelper.getConnection();
			String sql = "select * from person where id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				
				result = state.executeQuery();
				table.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}//end ActionPerformed
		
	}//end searchAction
	
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from person where id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				table.setModel(DBHelper.getAllData());
				DBHelper.fillCombo(searchCombo);
			}
			catch(SQLException e1){
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int row = table.getSelectedRow();
			id = Integer.parseInt(table.getValueAt(row, 0).toString());
			System.out.println(id);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void clearForm() {
		fnameTF.setText("");
		lnameTF.setText("");
		ageTF.setText("");
		salaryTF.setText("");
	}
	
	class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql="insert into person values(null,?,?,?,?,?)";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setString(3, sexCombo.getSelectedItem().toString());
				state.setByte(4, Byte.parseByte(ageTF.getText()));
				state.setFloat(5, Float.parseFloat(salaryTF.getText()));
				
				state.execute();
				table.setModel(DBHelper.getAllData());
				
				DBHelper.fillCombo(searchCombo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			clearForm();
		}
		
	}
	
}
