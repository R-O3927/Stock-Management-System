package system;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Data extends JFrame{
	//継承
	
	static final String URL = "jdbc:mysql://root@localhost/Book_Data";
	static final String URL2 ="jdbc:mysql://root@localhost/IT_Data";
	static final String URL3 ="jdbc:mysql://root@localhost/Other_Data";
	static final String USERNAME = "root@localhost";
	static final String PASSWORD = "Riru_1056";
	

	public static void main(String[] args) {
		
		String[] thingStrings = { "本", "IT資産", "その他" };
		JFrame frame = new JFrame("Input Dialog Example");
		String returnValue = (String) JOptionPane.showInputDialog(frame, "見たいジャンルを選択して下さい", "在庫管理システム",
				JOptionPane.QUESTION_MESSAGE, null, thingStrings, thingStrings[0]);
		String thing = returnValue;

		int id = 0;

		if (thing == "本") {
			id = 1;
		}

		else if (thing == "IT資産") {
			id = 2;
		}

		else if(thing =="その他"){
			id = 3;
		}
		
		else {
			
		}

		Data f = new Data();
		// data fのインスタンス作成

		f.setTitle("在庫管理システム");
		// ウィンドウ自体の題名を設定

		Container c = f.getContentPane();
		// ウィンドウの表示領域の取得

		JPanel p = new JPanel();
		// パネルのインスタンスの作成

		JLabel l = new JLabel("Welcome to our System!!");
		// ウィンドウに出力させる文章を設定

		l.setFont(new Font("Arial", Font.PLAIN, 40));

		p.add(l);
		// ラベルをパネルに配置

		if (id == 1) {
			JLabel l2 = new JLabel("Book Data");
			l2.setFont(new Font("Arial", Font.PLAIN, 35));
			p.add(l2);
		}

		else if (id == 2) {
			JLabel l3 = new JLabel("IT Data");
			l3.setFont(new Font("Arial", Font.PLAIN, 35));
			p.add(l3);
		}

		else if(id == 3){
			JLabel l4 = new JLabel("Other Data");
			l4.setFont(new Font("Arial", Font.PLAIN, 35));
			p.add(l4);
		}
		
		else {
			
		}

		c.add(p, BorderLayout.CENTER);
		// パネルをウィンドウの表示領域に配置

		f.setVisible(true);
		// ウィンドウを表示

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ウィンドウの「閉じる」ボタンを押した時の動作

		f.setSize(600, 800);
		// ウィンドウのサイズを設定
		
		if(id == 1) {
		String sql = "SELECT * from book;";
		
		try( Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql); ){
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
			String book_id = result.getString(1);
			String book_name = result.getString(2);
			String book_stock = result.getString(3);
			
			JLabel l5 = new JLabel("ID:"+book_id +"  NAME:"+book_name +"  STOCK:"+book_stock);
			
			l5.setFont(new Font("Arial", Font.PLAIN, 30));
			
			p.add(l5);
			}
			
			result.close();
			statement.close();
			connection.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		}
		
		else if(id ==2) {
			String sql2 = "SELECT * from IT;";
			
			try( Connection connection = DriverManager.getConnection(URL2,USERNAME,PASSWORD);
					PreparedStatement statement = connection.prepareStatement(sql2); ){
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
				String IT_id = result.getString(1);
				String IT_name = result.getString(2);
				String IT_stock = result.getString(3);
				
				JLabel l5 = new JLabel("ID:"+IT_id+"  NAME:"+IT_name+"  STOCK:"+IT_stock);
				
				l5.setFont(new Font("Arial", Font.PLAIN, 30));
				
				p.add(l5);
				
				}
				
				result.close();
				statement.close();
				connection.close();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		else if(id ==3) {
			String sql3 = "SELECT * from Other;";
			try( Connection connection = DriverManager.getConnection(URL3,USERNAME,PASSWORD);
					PreparedStatement statement = connection.prepareStatement(sql3); ){
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
				String Other_id = result.getString(1);
				String Other_name = result.getString(2);
				String Other_stock = result.getString(3);
				
				JLabel l5 = new JLabel("ID:"+Other_id+"  NAME:"+Other_name+"  STOCK:"+Other_stock);
				
				l5.setFont(new Font("Arial", Font.PLAIN, 30));
				
				p.add(l5);
				
				}
				
				result.close();
				statement.close();
				connection.close();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		else {
			
		}
		
		
				
		
		
	}
	
}


		


