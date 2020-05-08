package loginWithJava;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginWithJava.login.User;

public class start extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start frame = new start();
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
	public start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("出勤ページ");
		label.setBounds(12, 10, 123, 37);
		contentPane.add(label);
		
		JButton startBtn = new JButton("出勤");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start startView = new start();
				startView.setVisible(true);
				dispose();
			}
		});
		startBtn.setBounds(12, 57, 91, 37);
		contentPane.add(startBtn);
		
		JButton endBtn = new JButton("退勤");
		endBtn.setBounds(12, 104, 91, 37);
		contentPane.add(endBtn);
		
		JButton restStartBtn = new JButton("休憩開始");
		restStartBtn.setBounds(12, 151, 91, 37);
		contentPane.add(restStartBtn);
		
		JButton restEndBtn = new JButton("休憩終了");
		restEndBtn.setBounds(12, 198, 91, 37);
		contentPane.add(restEndBtn);
		
		JButton infobtn = new JButton("勤怠情報");
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
		panel.setBounds(184, 57, 320, 308);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton yesBtn = new JButton("はい");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null;
				PreparedStatement myPS = null;
				PreparedStatement myPS2 = null;

				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					
					String sql = "insert into workschedule (workDate,beginTime,employee_id) value(CURDATE(),CURTIME(),?)";
					String sql2 ="select * from workschedule where workDate=CURDATE() and employee_id=?";
					
					myPS = conn.prepareStatement(sql);
					myPS2= conn.prepareStatement(sql2);
					
					myPS.setString(1,User.inputId);
					myPS2.setString(1,User.inputId);
					
					ResultSet myRS = myPS2.executeQuery();
					boolean User =false;
					
					while( myRS.next()) {
						if(myRS!=null) {
							User = true;
							JOptionPane.showMessageDialog(yesBtn, "出勤済みです");
						}
					}if(User==false){
						myPS.execute();
						JOptionPane.showMessageDialog(yesBtn, "出勤しました");
					}
					
					//System.out.println(myPS);
			}catch (SQLException e) {
				e.printStackTrace();
				}
			}
		});
		yesBtn.setBounds(38, 206, 91, 36);
		panel.add(yesBtn);
		
		JButton noBtn = new JButton("いいえ");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ippann ippannView = new ippann();
				ippannView.setVisible(true);
				dispose();
			}
		});
		noBtn.setBounds(186, 206, 91, 36);
		panel.add(noBtn);
		
		JLabel label_1 = new JLabel("出勤しますか？");
		label_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		label_1.setBounds(101, 70, 117, 26);
		panel.add(label_1);
		
	}
}
