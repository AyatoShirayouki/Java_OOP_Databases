import java.awt.Dimension;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	 JTabbedPane tab = new JTabbedPane();
	 
	 Connection conn = null;
	 PreparedStatement state = null;
	 PreparedStatement product_state = null;
	 PreparedStatement sales_state = null;
	 PreparedStatement query1_state = null;
	 
	 int userId = -1;
	 int productId = -1;
	 int saleId = -1;
	 
	 JTable table = new JTable();
	 JTable product_table = new JTable();
	 JTable sales_table = new JTable();
	 JTable query1_table = new JTable();
	 
	 JScrollPane scroller = new JScrollPane(table);
	 JScrollPane product_scroller = new JScrollPane(product_table);
	 JScrollPane sales_scroller = new JScrollPane(sales_table);
	 JScrollPane query1_scroller = new JScrollPane(query1_table);
	 
	 JComboBox<String> user_search_combo = new JComboBox<String>();
	 JComboBox<String> product_search_combo = new JComboBox<String>();
	 JComboBox<String> sales_search_combo = new JComboBox<String>();
	 
	 JComboBox<String> user_seller_combo = new JComboBox<String>();
	 JComboBox<String> user_buyer_combo = new JComboBox<String>();
	 JComboBox<String> product_sales_combo = new JComboBox<String>();
	 
	 JComboBox<String> query1_user_combo = new JComboBox<String>();
	 JComboBox<String> query1_product_combo = new JComboBox<String>();
	 
	 ResultSet user_result = null;
	 ResultSet product_result = null;
	 ResultSet sale_result = null;
	 
	 JPanel users = new JPanel();
	 JPanel users_up = new JPanel();
	 JPanel users_mid = new JPanel();
	 JPanel users_down = new JPanel();
	 
	 JPanel products = new JPanel();
	 JPanel products_top = new JPanel();
	 JPanel products_mid = new JPanel();
	 JPanel products_down = new JPanel();
	 
	 JPanel sales = new JPanel();
	 JPanel sales_top = new JPanel();
	 JPanel sales_mid = new JPanel();
	 JPanel sales_down = new JPanel();
	 
	 JPanel query1 = new JPanel();
	 JPanel query1_top = new JPanel();
	 JPanel query1_mid = new JPanel();
	 JPanel query1_down = new JPanel();
	 
	 //user fields
	 JLabel user_first_name = new JLabel("First Name:");
	 JLabel user_last_name = new JLabel("Last Name:");
	 JLabel user_age = new JLabel("Age:");
	 JLabel user_city = new JLabel("City");
	 JLabel user_street = new JLabel("Street");
	 
	 JTextField user_first_name_textF = new JTextField(15);
	 JTextField user_last_name_textF = new JTextField();
	 JTextField user_age_textF = new JTextField();
	 JTextField user_city_textF = new JTextField();
	 JTextField user_street_textF = new JTextField();
	 
	 JButton create_user = new JButton("Create");
	 JButton edit_user = new JButton("Edit");
	 JButton delete_user = new JButton("Delete");
	 JButton search_user = new JButton("Search");
	 JButton search_user_reset = new JButton("Reset");
	 // end of user fields
	 
	 //product fields
	 JLabel product_name = new JLabel("Product Name:");
	 JLabel product_quantity = new JLabel("Quantity:");
	 JLabel product_price_delivery = new JLabel("Price(delivery):");
	 JLabel product_price_sale = new JLabel("Price(sale):");
	 
	 JTextField product_name_textF = new JTextField(15);
	 JTextField product_quantity_textF = new JTextField();
	 JTextField product_price_delivery_textF = new JTextField();
	 JTextField product_price_sale_textF = new JTextField();
	 
	 JButton create_product = new JButton("Create");
	 JButton edit_product = new JButton("Edit");
	 JButton delete_product = new JButton("Delete");
	 JButton search_product = new JButton("Search");
	 JButton search_product_reset = new JButton("Reset");
	 //end of product fields
	 
	 //sales fields
	 JLabel sales_product = new JLabel("Product:");
	 JLabel sales_product_quantity = new JLabel("Product quantity:");
	 JLabel sales_profit = new JLabel("Profit:");
	 JLabel sales_buyer = new JLabel("Buyer:");
	 JLabel sales_seller = new JLabel("Seller:");
	 
	 JTextField sales_product_textF = new JTextField();
	 JTextField sales_product_quantity_textF = new JTextField();
	 JTextField sales_profit_textF = new JTextField();
	 JTextField sales_buyer_textF = new JTextField();
	 JTextField sales_seller_textF = new JTextField();
	 
	 JButton create_sale = new JButton("Create");
	 JButton edit_sale = new JButton("Edit");
	 JButton delete_sale = new JButton("Delete");
	 JButton search_sale = new JButton("Search");
	 JButton search_sale_reset = new JButton("Reset");
	 //end of sales fields
	 
	 //query1 fields
	 JLabel query1_product_name = new JLabel("Product Name:");
	 JLabel query1_user_name = new JLabel("User Name:");
	 JButton buyer_of_this_product = new JButton("Sales where user bought this Product");
	 JButton seller_of_this_product = new JButton("Sales where user sold this Product");
	 //end of query1 fields
	 
	 public MainFrame() {
		 this.setSize(500, 600);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 //this.setLayout(new GridLayout(3, 1));
		 
		 //Users Panel and shit 
		 tab.add(users, "Users");
		 users_up.setLayout(new GridLayout(5, 2));
		 
		 users_up.add(user_first_name);
		 users_up.add(user_first_name_textF);
		 
		 users_up.add(user_last_name);
		 users_up.add(user_last_name_textF);
		 
		 users_up.add(user_age);
		 users_up.add(user_age_textF);
		 
		 users_up.add(user_city);
		 users_up.add(user_city_textF);
		 
		 users_up.add(user_street);
		 users_up.add(user_street_textF);
		 
		 users_mid.add(create_user);
		 users_mid.add(edit_user);
		 users_mid.add(delete_user);
		 users_mid.add(user_search_combo);
		 users_mid.add(search_user);
		 users_mid.add(search_user_reset);
		 
		 users_down.add(scroller);
		 scroller.setPreferredSize(new Dimension(450,160));
		 table.setModel(DBHelper.getAllData());
		 DBHelper.fillUserCombo(user_search_combo);
		 
		 users.add(users_up);
		 users.add(users_mid);
		 users.add(users_down);
		 
		 create_user.addActionListener(new AddUserAction());
		 edit_user.addActionListener(new EditUserAction());
		 delete_user.addActionListener(new DeleteUserAction());
		 search_user.addActionListener(new SearchUserAction());
		 search_user_reset.addActionListener(new ResetUserSearch());
		 //End of users panel and shit
		 
		 //Products and shit
		 tab.add(products, "Products");
		 products_top.setLayout(new GridLayout(5, 2));
		 
		 products_top.add(product_name);
		 products_top.add(product_name_textF);
		 
		 products_top.add(product_quantity);
		 products_top.add(product_quantity_textF);
		 
		 products_top.add(product_price_delivery);
		 products_top.add(product_price_delivery_textF);
		 
		 products_top.add(product_price_sale);
		 products_top.add(product_price_sale_textF);
		 
		 products_mid.add(create_product);
		 products_mid.add(edit_product);
		 products_mid.add(delete_product);
		 products_mid.add(product_search_combo);
		 products_mid.add(search_product);
		 products_mid.add(search_product_reset);
		 
		 products_down.add(product_scroller);
		 product_scroller.setPreferredSize(new Dimension(450,160));
		 product_table.setModel(DBHelper.getAllDataProducts());
		 DBHelper.fillProductCombo(product_search_combo);
		 
		 products.add(products_top);
		 products.add(products_mid);
		 products.add(products_down);
		 
		 create_product.addActionListener(new AddProductAction());
		 edit_product.addActionListener(new EditProductAction());
		 delete_product.addActionListener(new DeleteProductAction());
		 search_product.addActionListener(new SearchProductAction());
		 search_product_reset.addActionListener(new ResetProductSearch());
		 //End of products and shit
		 
		 //Sales and shit
		 tab.add(sales, "Sales");
		 sales_top.setLayout(new GridLayout(5, 2));
		 
		 sales_top.add(sales_product);
		 sales_top.add(product_sales_combo);
		 
		 sales_top.add(sales_product_quantity);
		 sales_top.add(sales_product_quantity_textF);
		 
		 sales_top.add(sales_product_quantity);
		 sales_top.add(sales_product_quantity_textF);
		 
		 sales_top.add(sales_seller);
		 sales_top.add(user_seller_combo);
		 
		 sales_top.add(sales_buyer);
		 sales_top.add(user_buyer_combo);
		 
		 sales_mid.add(create_sale);
		 sales_mid.add(edit_sale);
		 sales_mid.add(delete_sale);
		 sales_mid.add(sales_search_combo);
		 sales_mid.add(search_sale);
		 sales_mid.add(search_sale_reset);
		 
		 sales_down.add(sales_scroller);
		 sales_scroller.setPreferredSize(new Dimension(450,160));
		 sales_table.setModel(DBHelper.getAllDataSales());
		 DBHelper.fillProductCombo(product_sales_combo);
		 DBHelper.fillUserCombo(user_buyer_combo);
		 DBHelper.fillUserCombo(user_seller_combo);
		 DBHelper.fillSalesCombo(sales_search_combo);
		 
		 sales.add(sales_top);
		 sales.add(sales_mid);
		 sales.add(sales_down);
		 
		 create_sale.addActionListener(new AddSaleAction());
		 edit_sale.addActionListener(new EditSaleAction());
		 delete_sale.addActionListener(new DeleteSaleAction());
		 search_sale.addActionListener(new SearchSalesAction());
		 search_sale_reset.addActionListener(new ResetSalesSearch());
		 //End of sales and shit
		 
		 //Query1 and shit
		 tab.add(query1, "Query");
		 query1_top.setLayout(new GridLayout(5, 2));
		 
		 query1_top.add(query1_product_name);
		 query1_top.add(query1_product_combo);
		 
		 query1_top.add(query1_user_name);
		 query1_top.add(query1_user_combo);
		 
		 query1_mid.add(buyer_of_this_product);
		 query1_mid.add(seller_of_this_product);
		 
		 query1_down.add(query1_scroller);
		 query1_scroller.setPreferredSize(new Dimension(450,160));
		 DBHelper.fillProductCombo(query1_product_combo);
		 DBHelper.fillUserCombo(query1_user_combo);
		 
		 seller_of_this_product.addActionListener(new FirstQueryAction1());
		 buyer_of_this_product.addActionListener(new FirstQueryAction2());
		 
		 query1.add(query1_top);
		 query1.add(query1_mid);
		 query1.add(query1_down);
		 //End ofQuery1 and shit
		 
		 table.addMouseListener(new UserTableListener());
		 product_table.addMouseListener(new ProductTableListener());
		 sales_table.addMouseListener(new SaleTableListener());
		 
		 this.add(tab);
		 
		 this.setVisible(true);
	 }
	 
	 class FirstQueryAction1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedProductItem = query1_product_combo.getSelectedItem().toString();
			String[] productItems = selectedProductItem.split(" ");
			int productItemID = Integer.parseInt(productItems[0]);
			
			String selectedSellerItem = query1_user_combo.getSelectedItem().toString();
			String[] sellerItems = selectedSellerItem.split(" ");
			int sellerItemID = Integer.parseInt(sellerItems[0]);
			
			query1_table.setModel(DBHelper.getAllDataQuery1(productItemID, sellerItemID));
		}
		 
	 }
	 
	 class FirstQueryAction2 implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedProductItem = query1_product_combo.getSelectedItem().toString();
				String[] productItems = selectedProductItem.split(" ");
				int productItemID = Integer.parseInt(productItems[0]);
				
				String selectedSellerItem = query1_user_combo.getSelectedItem().toString();
				String[] sellerItems = selectedSellerItem.split(" ");
				int sellerItemID = Integer.parseInt(sellerItems[0]);
				
				query1_table.setModel(DBHelper.getAllDataQuery2(productItemID, sellerItemID));
			}
			 
		 }
	 
	 public void clearUserForm() {
		 user_first_name_textF.setText("");
		 user_last_name_textF.setText("");
		 user_age_textF.setText("");
		 user_city_textF.setText("");
		 user_street_textF.setText("");
	}
	 
	 public void clearUProductsForm() {
		 product_name_textF.setText("");
		 product_quantity_textF.setText("");
		 product_price_delivery_textF.setText("");
		 product_price_sale_textF.setText("");
	}
	 
	 public void clearSalesForm() {
		 sales_product_quantity_textF.setText("");
	}
	 
	 class AddSaleAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedProductItem = product_sales_combo.getSelectedItem().toString();
			String[] productItems = selectedProductItem.split(" ");
			int productItemID = Integer.parseInt(productItems[0]);
			
			String selectedSellerItem = user_seller_combo.getSelectedItem().toString();
			String[] sellerItems = selectedSellerItem.split(" ");
			int sellerItemID = Integer.parseInt(sellerItems[0]);
			
			String selectedBuyerItem = user_buyer_combo.getSelectedItem().toString();
			String[] buyerItems = selectedBuyerItem.split(" ");
			int buyerItemID = Integer.parseInt(buyerItems[0]);
			
			conn = DBHelper.getConnection();
			String sql="insert into sales values(null,?,?,?,?,?)";
			
			if(sellerItemID != buyerItemID)
			{
				try {
					sales_state = conn.prepareStatement(sql);
					int profit = (DBHelper.GetPriceSale(productItemID) * Integer.parseInt(sales_product_quantity_textF.getText()))
							- (DBHelper.GetPriceDelivery(productItemID)* Integer.parseInt(sales_product_quantity_textF.getText()));
					
					sales_state.setInt(1, productItemID);
					sales_state.setInt(2, Integer.parseInt(sales_product_quantity_textF.getText()));
					sales_state.setInt(3, profit);
					sales_state.setInt(4, sellerItemID);
					sales_state.setInt(5, buyerItemID);
					
					sales_state.execute();
					sales_table.setModel(DBHelper.getAllDataSales());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			clearSalesForm();
		}
		 
	 }
	 
	 class AddProductAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql="insert into products values(null,?,?,?,?)";
			
			try {
				product_state = conn.prepareStatement(sql);
				
				product_state.setString(1, product_quantity_textF.getText());
				product_state.setString(2, product_price_delivery_textF.getText());
				product_state.setString(3, product_price_sale_textF.getText());
				product_state.setString(4, product_name_textF.getText());
				
				product_state.execute();
				product_table.setModel(DBHelper.getAllDataProducts());
				sales_table.setModel(DBHelper.getAllDataSales());////
				
				DBHelper.fillProductCombo(product_search_combo);
				DBHelper.fillProductCombo(query1_product_combo);///
				DBHelper.fillProductCombo(product_sales_combo);///
				DBHelper.fillUserCombo(query1_user_combo);////
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					product_state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			clearUProductsForm();
		} 
	 }
	 
	 class AddUserAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql="insert into users values(null,?,?,?,?,?)";
			
			try {
				state = conn.prepareStatement(sql);
				
				state.setString(1, user_first_name_textF.getText());
				state.setString(2, user_last_name_textF.getText());
				state.setString(3, user_age_textF.getText());
				state.setString(4, user_city_textF.getText());
				state.setString(5, user_street_textF.getText());
				
				state.execute();
				table.setModel(DBHelper.getAllData());
				sales_table.setModel(DBHelper.getAllDataSales());/////
				DBHelper.fillProductCombo(query1_product_combo);//
				DBHelper.fillUserCombo(query1_user_combo);//
				DBHelper.fillUserCombo(user_buyer_combo);//
				 DBHelper.fillUserCombo(user_seller_combo);//
				
				DBHelper.fillUserCombo(user_search_combo);
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
			
			clearUserForm();
		}
	 }
	 
	 class EditSaleAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedProductItem = product_sales_combo.getSelectedItem().toString();
				String[] productItems = selectedProductItem.split(" ");
				int productItemID = Integer.parseInt(productItems[0]);
				
				String selectedSellerItem = user_seller_combo.getSelectedItem().toString();
				String[] sellerItems = selectedSellerItem.split(" ");
				int sellerItemID = Integer.parseInt(sellerItems[0]);
				
				String selectedBuyerItem = user_buyer_combo.getSelectedItem().toString();
				String[] buyerItems = selectedBuyerItem.split(" ");
				int buyerItemID = Integer.parseInt(buyerItems[0]);
				
				conn = DBHelper.getConnection();
				String sql="update sales set sold_product_id=?, quantity_sold=?,"
						+ "profit=?, seller_id=?, buyer_id=? where sale_id=?";
				
				if(sellerItemID != buyerItemID)
				{
					try {
						sales_state = conn.prepareStatement(sql);
						int profit = (DBHelper.GetPriceSale(productItemID) * Integer.parseInt(sales_product_quantity_textF.getText()))
								- (DBHelper.GetPriceDelivery(productItemID)* Integer.parseInt(sales_product_quantity_textF.getText()));
						
						sales_state.setInt(1, productItemID);
						sales_state.setInt(2, Integer.parseInt(sales_product_quantity_textF.getText()));
						sales_state.setInt(3, profit);
						sales_state.setInt(4, sellerItemID);
						sales_state.setInt(5, buyerItemID);
						sales_state.setInt(6, saleId);
						
						sales_state.execute();
						sales_table.setModel(DBHelper.getAllDataSales());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				clearSalesForm();
			}
			 
		 }
	 
	 class EditUserAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = DBHelper.getConnection();
				String sql="update users set first_name=?, last_name=?, "
						+ "age=?, city=?, street=? where user_id=?";
				
				try {
					state = conn.prepareStatement(sql);
					
					state.setString(1, user_first_name_textF.getText());
					state.setString(2, user_last_name_textF.getText());
					state.setString(3, user_age_textF.getText());
					state.setString(4, user_city_textF.getText());
					state.setString(5, user_street_textF.getText());
					state.setInt(6, userId);
					
					state.execute();
					table.setModel(DBHelper.getAllData());
					sales_table.setModel(DBHelper.getAllDataSales());////
					DBHelper.fillProductCombo(query1_product_combo);///
					DBHelper.fillUserCombo(query1_user_combo);///
					DBHelper.fillUserCombo(user_buyer_combo);//
					 DBHelper.fillUserCombo(user_seller_combo);//
					
					DBHelper.fillUserCombo(user_search_combo);
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
				
				clearUserForm();
			}
		 }
	 
	 class EditProductAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = DBHelper.getConnection();
				String sql="update products set quantity=?, price_for_delivery=?, "
						+ "price_for_sale=?, product_name=? where product_id=?";
				
				try {
					product_state = conn.prepareStatement(sql);
					
					product_state.setString(1, product_quantity_textF.getText());
					product_state.setString(2, product_price_delivery_textF.getText());
					product_state.setString(3, product_price_sale_textF.getText());
					product_state.setString(4, product_name_textF.getText());
					product_state.setInt(5, productId);
					
					product_state.execute();
					product_table.setModel(DBHelper.getAllDataProducts());
					sales_table.setModel(DBHelper.getAllDataSales());/////
					DBHelper.fillProductCombo(query1_product_combo);///
					 DBHelper.fillUserCombo(query1_user_combo);///
					
					DBHelper.fillProductCombo(product_search_combo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						product_state.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				clearUProductsForm();
			}
		 }
	 
	 class DeleteUserAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from sales where seller_id = ? or buyer_id = ?; " + 
			" delete from users where user_id=?";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, userId);
				state.setInt(2, userId);
				state.setInt(3, userId);
				state.execute();
				userId = -1;
				table.setModel(DBHelper.getAllData());
				sales_table.setModel(DBHelper.getAllDataSales());////
				DBHelper.fillProductCombo(query1_product_combo);//
				 DBHelper.fillUserCombo(query1_user_combo);//
				 DBHelper.fillUserCombo(user_buyer_combo);//
				 DBHelper.fillUserCombo(user_seller_combo);//
				System.out.println(userId);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			clearUserForm();
			DBHelper.fillUserCombo(user_search_combo);
			
		}
	 }
	 
	 class DeleteProductAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = DBHelper.getConnection();
				String sql = "delete from sales where sold_product_id = ?;" + " delete from products where product_id=?";
				
				try {
					product_state = conn.prepareStatement(sql);
					product_state.setInt(1, productId);
					product_state.setInt(2, productId);
					product_state.execute();
					productId = -1;
					product_table.setModel(DBHelper.getAllDataProducts());
					sales_table.setModel(DBHelper.getAllDataSales());
					DBHelper.fillProductCombo(query1_product_combo);//
					 DBHelper.fillUserCombo(query1_user_combo);///
					System.out.println(productId);
					
					DBHelper.fillProductCombo(product_search_combo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearUProductsForm();
			}
		 }
	 
	 class DeleteSaleAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = DBHelper.getConnection();
				String sql = "delete from sales where sale_id=?";
				
				try {
					product_state = conn.prepareStatement(sql);
					product_state.setInt(1, saleId);
					product_state.execute();
					saleId = -1;
					sales_table.setModel(DBHelper.getAllDataSales());
					sales_table.setModel(DBHelper.getAllDataSales());////
					//DBHelper.fillProductCombo(product_search_combo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearSalesForm();
			}
		 }
	 
	 class SearchUserAction implements ActionListener{
		 
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem = user_search_combo.getSelectedItem().toString();
			String[] items = selectedItem.split(" ");
			int itemID = Integer.parseInt(items[0]);
			
			conn = DBHelper.getConnection();
			String sql = "select * from users where user_id=?";
			
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, itemID);
				
				user_result = state.executeQuery();
				table.setModel(new MyModel(user_result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	 }
	 
	 class SearchProductAction implements ActionListener{
		 
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = product_search_combo.getSelectedItem().toString();
				String[] items = selectedItem.split(" ");
				int itemID = Integer.parseInt(items[0]);
				
				conn = DBHelper.getConnection();
				String sql = "select * from products where product_id=?";
				
				try {
					product_state = conn.prepareStatement(sql);
					product_state.setInt(1, itemID);
					
					product_result = product_state.executeQuery();
					product_table.setModel(new MyModel(product_result));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 }
	 
	 class SearchSalesAction implements ActionListener{
		 
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = sales_search_combo.getSelectedItem().toString();
				String[] items = selectedItem.split(" ");
				int itemID = Integer.parseInt(items[0]);
				
				conn = DBHelper.getConnection();
				String sql = "select * from sales where sale_id=?";
				
				try {
					sales_state = conn.prepareStatement(sql);
					sales_state.setInt(1, itemID);
					
					sale_result = sales_state.executeQuery();
					sales_table.setModel(new MyModel(sale_result));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 }
	 
	 class ResetUserSearch implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			table.setModel(DBHelper.getAllData());
			
		}
		 
	 }
	 
	 class ResetProductSearch implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				product_table.setModel(DBHelper.getAllDataProducts());
				
			}
			 
		 }
	 
	 class ResetSalesSearch implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				sales_table.setModel(DBHelper.getAllDataSales());
				
			}
			 
		 }
	 
	 class SaleTableListener implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = sales_table.getSelectedRow();
				saleId = Integer.parseInt(sales_table.getValueAt(row, 0).toString());
				sales_product_quantity_textF.setText(sales_table.getValueAt(row, 2).toString());
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
	 
	class UserTableListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			userId = Integer.parseInt(table.getValueAt(row, 0).toString());
			user_first_name_textF.setText(table.getValueAt(row, 1).toString());
			user_last_name_textF.setText(table.getValueAt(row, 2).toString());
			user_age_textF.setText(table.getValueAt(row, 3).toString());
			user_city_textF.setText(table.getValueAt(row, 4).toString());
			user_street_textF.setText(table.getValueAt(row, 5).toString());
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
	
	class ProductTableListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = product_table.getSelectedRow();
			productId = Integer.parseInt(product_table.getValueAt(row, 0).toString());
			product_quantity_textF.setText(product_table.getValueAt(row, 1).toString());
			product_price_delivery_textF.setText(product_table.getValueAt(row, 2).toString());
			product_price_sale_textF.setText(product_table.getValueAt(row, 3).toString());
			product_name_textF.setText(product_table.getValueAt(row, 4).toString());
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
}
