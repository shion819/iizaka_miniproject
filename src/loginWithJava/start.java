package loginWithJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Button;

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
		
		JLabel label = new JLabel("出勤画面");
		label.setBounds(12, 10, 123, 37);
		contentPane.add(label);
		
		JButton startBtn = new JButton("出勤");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		infobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		infobtn.setBounds(12, 245, 91, 37);
		contentPane.add(infobtn);
		
		JButton logOutBtn = new JButton("ログアウト");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWithJava l = new loginWithJava();
				l.setVisible(true);
				dispose();
			}
		});
		logOutBtn.setBounds(12, 328, 104, 37);
		contentPane.add(logOutBtn);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(181, 57, 306, 297);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("出勤しますか？");
		label_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		label_1.setBounds(78, 69, 165, 42);
		panel.add(label_1);
		
		Button yesBtn = new Button("はい");
		yesBtn.setBounds(35, 198, 89, 34);
		panel.add(yesBtn);
		
		Button noBtn = new Button("いいえ");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ippann i = new ippann();
				i.setVisible(true);
				dispose();
			}
		});
		noBtn.setBounds(180, 198, 89, 34);
		panel.add(noBtn);
	}
}