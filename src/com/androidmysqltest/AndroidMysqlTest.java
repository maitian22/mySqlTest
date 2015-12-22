package com.androidmysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidmysqltest1.R;

public class AndroidMysqlTest extends Activity implements View.OnClickListener {
	// Button btButton;
	Connection con = null;
	private String TAG = "AndroidMysqlTest";
	private Button mSearchAllTables, mInsertOneData, mDelOneData,
			mQuoteOneData;
	private TextView mResult;

	private void searchTable() {
		Thread myThread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// java.sql.DriverManager.registerDriver((Driver)ob ) ;

					con = (Connection) DriverManager.getConnection(
							"jdbc:mysql://182.92.7.154:3306/jinrong", "dubin",
							"fykcpassword");

					Log.i(TAG, "Success connect Mysql server!");

					Statement st = con.createStatement();
					// 执行语句，返回结果
					ResultSet rt = st.executeQuery("show tables");

					// ResultSet rt = st
					// .executeQuery("select * from fy_agent where mobile=13101887572");

					// ResultSet rt = st
					// .executeQuery("select * from fy_agent where mobile=13101887572");
					// 循环取出结果
					int i = 0;
					while (rt.next()) {
						// 获取字段
						Log.i(TAG, "i:" + i + "," + rt.getString(1));
						i++;
					}
					// mResult.setText(text);
					// 关闭资源，最先打开的最后关
					rt.close();
					st.close();
					con.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				} catch (Exception e) {
					System.out.println("Error loading Mysql Driver!");
					e.printStackTrace();
				}

			}

		};
		myThread.start();
	}

	private void InsertData() {
		Thread mInsertThread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// java.sql.DriverManager.registerDriver((Driver)ob ) ;

					con = (Connection) DriverManager.getConnection(
							"jdbc:mysql://182.92.7.154:3306/jinrong", "dubin",
							"fykcpassword");

					Log.i(TAG, "Success connect Mysql server!");

					Statement st = con.createStatement();
					// 执行语句，返回结果
					// ResultSet rt = st.executeQuery("show tables");

					int result = st
							.executeUpdate("insert into usr(name,password) value('hehe','13333333333')");

					Log.i(TAG, "insert result:" + result);
					if (result == 1) {
						Log.i(TAG, "insert success ....");
					} else {
						Log.i(TAG, "inset fail ....");
					}
					// mResult.setText(text);
					// 关闭资源，最先打开的最后关

					st.close();
					con.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				} catch (Exception e) {
					System.out.println("Error loading Mysql Driver!");
					e.printStackTrace();
				}
			}

		};
		mInsertThread.start();
	}

	private void DelTread() {
		Thread mDelThread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// java.sql.DriverManager.registerDriver((Driver)ob ) ;

					con = (Connection) DriverManager.getConnection(
							"jdbc:mysql://182.92.7.154:3306/jinrong", "dubin",
							"fykcpassword");

					Log.i(TAG, "Success connect Mysql server!");

					Statement st = con.createStatement();
					// 执行语句，返回结果
					// ResultSet rt = st.executeQuery("show tables");

					int result = st
							.executeUpdate("delete from usr where name='hehe'");

					Log.i(TAG, "del result:" + result);
					if (result == 1) {
						Log.i(TAG, "del success ....");
					} else {
						Log.i(TAG, "del fail ....");
					}
					// mResult.setText(text);
					// 关闭资源，最先打开的最后关

					st.close();
					con.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				} catch (Exception e) {
					System.out.println("Error loading Mysql Driver!");
					e.printStackTrace();
				}
			}

		};
		mDelThread.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// btButton = (Button) findViewById(R.id.button1);
		//
		// btButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// myThread.start();
		// }
		// });

		mSearchAllTables = (Button) findViewById(R.id.button1);
		mSearchAllTables.setOnClickListener(this);

		mInsertOneData = (Button) findViewById(R.id.button2);
		mInsertOneData.setOnClickListener(this);

		mDelOneData = (Button) findViewById(R.id.button3);
		mDelOneData.setOnClickListener(this);

		mQuoteOneData = (Button) findViewById(R.id.button4);
		mQuoteOneData.setOnClickListener(this);

		mResult = (TextView) findViewById(R.id.result);
	}

	private void quoteData() {
		Thread mQuoteTrhead = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// java.sql.DriverManager.registerDriver((Driver)ob ) ;

					con = (Connection) DriverManager.getConnection(
							"jdbc:mysql://182.92.7.154:3306/jinrong", "dubin",
							"fykcpassword");

					Log.i(TAG, "Success connect Mysql server!");

					Statement st = con.createStatement();
					// 执行语句，返回结果
					// ResultSet rt = st.executeQuery("show tables");

					ResultSet rt = st
							.executeQuery("select * from usr where name='hehe'");

					int i = 0;
					while (rt.next()) {
						// 获取字段
						Log.i(TAG, "i:" + i + ",name:" + rt.getString("name")
								+ ",password:" + rt.getString("password"));
						i++;
					}
					rt.close();
					// mResult.setText(text);
					// 关闭资源，最先打开的最后关

					st.close();
					con.close();
				} catch (SQLException ee) {
					ee.printStackTrace();
				} catch (Exception e) {
					System.out.println("Error loading Mysql Driver!");
					e.printStackTrace();
				}
			}

		};
		mQuoteTrhead.start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			searchTable();
			break;
		case R.id.button2:
			InsertData();
			break;
		case R.id.button3:
			DelTread();
			break;
		case R.id.button4:
			quoteData();
			break;
		}
	}
}
