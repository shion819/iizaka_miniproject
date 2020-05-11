package loginWithJava;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginWithJava.login.User;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class dutyInfo2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFormattedTextField dateField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dutyInfo2 frame = new dutyInfo2();
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
	public dutyInfo2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("一般ページ");
		label.setBounds(12, 10, 123, 37);
		contentPane.add(label);
		
		JButton startBtn = new JButton("出勤");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start startView = new start();
				startView.setVisible(true);
				dispose();
			}
		});
		startBtn.setBounds(12, 57, 91, 37);
		contentPane.add(startBtn);
		
		JButton endBtn = new JButton("退勤");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				end2 endView = new end2();
				endView.setVisible(true);
				dispose();
			}
		});
		endBtn.setBounds(12, 104, 91, 37);
		contentPane.add(endBtn);
		
		JButton restStartBtn = new JButton("休憩開始");
		restStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restStart restStartView = new restStart();
				restStartView.setVisible(true);
				dispose();
			}
		});
		restStartBtn.setBounds(12, 151, 91, 37);
		contentPane.add(restStartBtn);
		
		JButton restEndBtn = new JButton("休憩終了");
		restEndBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restEnd restEndView = new restEnd();
				restEndView.setVisible(true);
				dispose();
			}
		});
		restEndBtn.setBounds(12, 198, 91, 37);
		contentPane.add(restEndBtn);
		
		JButton infobtn = new JButton("勤怠情報");
		infobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dutyInfo2 dutyInfoView =new dutyInfo2();
				dutyInfoView.setVisible(true);
				dispose();
			}
		});
		infobtn.setBounds(12, 245, 91, 37);
		contentPane.add(infobtn);
		
		JButton logOutBtn = new JButton("ログアウト");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login loginView = new login();
				loginView.setVisible(true);
				dispose();
			}
		});
		logOutBtn.setBounds(12, 328, 104, 37);
		contentPane.add(logOutBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(166, 57, 625, 327);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("閲覧したい年月を入力してください");
		label_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		label_1.setBounds(12, 10, 169, 37);
		panel.add(label_1);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
		dateField = new JFormattedTextField(dateFormat);
		dateField.setBounds(22, 44, 112, 25);
		dateField.setValue(new Date());
		panel.add(dateField);
		dateField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 601, 240);
		panel.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("閲覧");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dateSarch = dateField.getText();
				
				Connection conn = null;
				PreparedStatement myPS = null;
				
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					String sql ="SELECT workDate, beginTime, restBegin, restEnd, endTime FROM workschedule WHERE (DATE_FORMAT(workDate, '%Y/%m') = ?) AND employee_id=?";
					myPS = conn.prepareStatement(sql);
					
					myPS.setString(1,dateSarch);
					myPS.setString(2,User.inputId);
					
					ResultSet myRS = myPS.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(myRS));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(146, 46, 62, 21);
		panel.add(button);
		
		JButton button_1 = new JButton("戻る");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ippann ippannView = new ippann();
				ippannView.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(522, 18, 91, 21);
		panel.add(button_1);
		
		
		
		
	}
}

