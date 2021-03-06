import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class DBHelper {
	static Connection conn = null;
	static PreparedStatement state = null;
	static MyModel model = null;
	static ResultSet result = null;
	
	static int GetPriceDelivery(int id) {
		conn = getConnection();
		String sql = "select price_for_delivery from products where product_id=" + id;
		int delivery_id = 0;
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			
			result.next();
			
			delivery_id = result.getInt("price_for_delivery");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return delivery_id;
	}
	
	static int GetPriceSale(int id) {
		conn = getConnection();
		String sql = "select price_for_sale from products where product_id=" + id;
		int sale_id = 0;
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			
			result.next();
			
			sale_id = result.getInt("price_for_sale");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sale_id;
	}
	
	static void fillUserCombo(JComboBox<String> combo) {
		
		conn = getConnection();
		String sql = "select user_id,first_name from users";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			
			combo.removeAllItems();
			
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2);
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end fillCombo
	
	static void fillProductCombo(JComboBox<String> combo) {
		
		conn = getConnection();
		String sql = "select product_id,product_name from products";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			
			combo.removeAllItems();
			
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2);
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end fillCombo
	
	static void fillSalesCombo(JComboBox<String> combo) {
		
		conn = getConnection();
		String sql = "select sale_id, product_name from sales join"
				+ " products on sold_product_id = products.product_id";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			
			combo.removeAllItems();
			
			while(result.next()) {
				String item = result.getObject(1).toString() + " " + result.getObject(2);
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end fillCombo
	
	static MyModel getAllData() {
		conn = getConnection();
		String sql = "select * from users";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end get all data from users
	
	static MyModel getAllDataProducts() {
		conn = getConnection();
		String sql = "select * from products";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end get all data from products
	
	static MyModel getAllDataSales() {
		conn = getConnection();
		String sql = "SELECT SALE_ID,PRODUCTS.PRODUCT_NAME AS`Product name`,PRODUCTS.QUANTITY AS`Quantity`,s1.PROFIT,u1.FIRST_NAME AS`Seller` ,u2.FIRST_NAME AS`Buyer`,  FROM SALES as s1\r\n" + 
				"Join PRODUCTS ON s1.SOLD_PRODUCT_ID = PRODUCTS.PRODUCT_ID\r\n" + 
				"JOIN USERS as u1 ON s1.SELLER_ID=u1.USER_ID \r\n" + 
				"JOIN USERS as u2 ON s1.BUYER_ID=u2.USER_ID";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end get all data from sales
	
	static MyModel getAllDataQuery1(int pId, int uId) {
		conn = getConnection();
		String sql = "select product_name, first_name, quantity_sold, profit from sales"
				+ " join products on sold_product_id = products.product_id"
				+ " and products.product_id =" + pId + " "
				+ "join users on seller_id = users.user_id and users.user_id =" + uId;
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end get all data from products
	
	static MyModel getAllDataQuery2(int pId, int uId) {
		conn = getConnection();
		String sql = "select product_name, first_name, quantity_sold, profit from sales"
				+ " join products on sold_product_id = products.product_id"
				+ " and products.product_id =" + pId + " "
				+ "join users on buyer_id = users.user_id and users.user_id =" + uId;
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end get all data from products
	
	static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Ayato Shirayuki\\OneDrive\\Desktop\\database\\PersonDB", "sa", "12");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}//end get connection
	
	
}
