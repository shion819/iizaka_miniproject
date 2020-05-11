package loginWithJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ippann extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ippann frame = new ippann();
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
	public ippann() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JLabel clockLabel = new JLabel("New label");
		clockLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				java.util.Date date = new java.util.Date();
				DateFormat timeFormat = new SimpleDateFormat("ログイン時間"+"HH:mm:ss");
				String time = timeFormat.format(date);
				clockLabel.setText(time);
			}
		});
		
		clockLabel.setBounds(368, 22, 182, 13);
		contentPane.add(clockLabel);*/
		
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
	}
}

