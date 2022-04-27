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

//継承
public class Data extends JFrame {

	// 文の変更不可のものの定義
	static final String BOOK_URL = "jdbc:mysql://root@localhost/Book_Data";
	static final String IT_URL = "jdbc:mysql://root@localhost/IT_Data";
	static final String OTHER_URL = "jdbc:mysql://root@localhost/Other_Data";
	static final String USERNAME = "root@localhost";
	static final String PASSWORD = "Riru_1056";

	public static void main(String[] args) {

		// String型データ thingStringsの定義
		String[] thingStrings = { "本", "IT資産", "その他" };

		// インスタンスの作成（実体を持たせる）
		JFrame frame = new JFrame("Input Dialog Example");

		// String型 returnValueの作成及び表示する質問、画面のタイトル、選択肢を設定
		String returnValue = (String) JOptionPane.showInputDialog(frame, "見たいジャンルを選択して下さい", "在庫管理システム",
				JOptionPane.QUESTION_MESSAGE, null, thingStrings, thingStrings[0]);

		// String型 thingの作成 thingにreturnValueの値を代入
		String selectThing = returnValue;

		// ジャンルIDの生成と代入
		int genreId = 10;

		if (selectThing == "本") {
			genreId = 1;
		}

		else if (selectThing == "IT資産") {
			genreId = 2;
		}

		else if (selectThing == "その他") {
			genreId = 3;
		}

		else {
			genreId = 0;
		}

		Data f = new Data();
		// data fのインスタンス作成

		f.setTitle("在庫管理システム");
		// ウィンドウ自体の題名を設定

		Container c = f.getContentPane();
		// ウィンドウの表示領域の取得

		JPanel p = new JPanel();
		// パネルのインスタンスの作成

		JLabel commonLabel = new JLabel("Welcome to our System!!");
		// ウィンドウに出力させる文章を設定

		commonLabel.setFont(new Font("Arial", Font.BOLD, 40));

		p.add(commonLabel);
		// ラベルをパネルに配置

		switch (genreId) {
		case 0:// 取り消しを押した場合

			JLabel cancelLabel = new JLabel("You canceled");

			cancelLabel.setFont(new Font("Arial", Font.PLAIN, 30));

			p.add(cancelLabel);

			break;

		case 1: // ジャンルIDが1だった場合

			JLabel bookSelectLabel = new JLabel("Book Data");

			bookSelectLabel.setFont(new Font("Arial", Font.PLAIN, 35));

			p.add(bookSelectLabel);

			// String型 bookSqlの作成 MySQLのテーブルごと読み込む
			String bookSql = "SELECT * from book;";

			// URL,USERNAME,PASSWORDを読み込もうとする
			try (Connection bookConnection = DriverManager.getConnection(BOOK_URL, USERNAME, PASSWORD);
					PreparedStatement bookStatement = bookConnection.prepareStatement(bookSql);) {

				// 指定されたSQL文を実行し、得た値をbookResultに代入
				ResultSet bookResult = bookStatement.executeQuery();

				// SQLの実行結果からデータを取得
				while (bookResult.next()) {
					String bookId = bookResult.getString(1);
					String bookName = bookResult.getString(2);
					String bookStock = bookResult.getString(3);

					JLabel bookDataLabel = new JLabel("  ID:" +  bookId  + "  NAME:" +  bookName  + "  STOCK:" +  bookStock);

					bookDataLabel.setFont(new Font("Arial", Font.PLAIN, 30));

					p.add(bookDataLabel);
				}

				bookResult.close();
				bookStatement.close();
				bookConnection.close();

			} catch (SQLException bookE) {

				// 実行したメソッドの時系列一覧を出力
				bookE.printStackTrace();
			}

			break;

		case 2:
			JLabel itLabel = new JLabel("IT assets Data");
			itLabel.setFont(new Font("Arial", Font.PLAIN, 35));
			p.add(itLabel);

			String itSql = "SELECT * from IT;";

			try (Connection itConnection = DriverManager.getConnection(IT_URL, USERNAME, PASSWORD);
					PreparedStatement itStatement = itConnection.prepareStatement(itSql);) {

				ResultSet itResult = itStatement.executeQuery();

				while (itResult.next()) {
					String itId = itResult.getString(1);
					String itName = itResult.getString(2);
					String itStock = itResult.getString(3);

					JLabel itDataLabel = new JLabel("  ID:" +  itId + "  NAME:" +  itName + "  STOCK:" +  itStock);

					itDataLabel.setFont(new Font("Arial", Font.PLAIN, 30));

					p.add(itDataLabel);
				}

				itResult.close();
				itStatement.close();
				itConnection.close();

			} catch (SQLException itE) {

				itE.printStackTrace();
			}

			break;

		case 3:
			JLabel otherLabel = new JLabel("Other Data");
			otherLabel.setFont(new Font("Arial", Font.PLAIN, 35));
			p.add(otherLabel);

			String otherSql = "SELECT * from other;";

			try (Connection otherConnection = DriverManager.getConnection(OTHER_URL, USERNAME, PASSWORD);
					PreparedStatement otherStatement = otherConnection.prepareStatement(otherSql);) {

				ResultSet otherResult = otherStatement.executeQuery();

				while (otherResult.next()) {
					String otherId = otherResult.getString(1);
					String otherName = otherResult.getString(2);
					String otherStock = otherResult.getString(3);

					JLabel otherDataLabel = new JLabel(
							"  ID:" +  otherId + "  NAME:" +  otherName + "  STOCK:" +  otherStock);

					otherDataLabel.setFont(new Font("Arial", Font.PLAIN, 30));

					p.add(otherDataLabel);
				}

				otherResult.close();
				otherStatement.close();
				otherConnection.close();

			} catch (SQLException otherE) {

				otherE.printStackTrace();
			}

			break;
		}

		c.add(p, BorderLayout.CENTER);
		// パネルをウィンドウの表示領域に配置

		f.setVisible(true);
		// ウィンドウを表示

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ウィンドウの「閉じる」ボタンを押した時の動作

		f.setSize(600, 800);
		// ウィンドウのサイズを設定

	}

}
