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
		
		JLabel label = new JLabel("一般ページ");
		label.setBounds(12, 10, 123, 37);
		contentPane.add(label);
		
		JButton startBtn = new JButton("出勤");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				starts startView = new starts();
				startView.setVisible(true);
				dispose();
			}
		});
		startBtn.setBounds(12, 57, 91, 37);
		contentPane.add(startBtn);
		
		JButton endBtn = new JButton("退勤");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				end endView = new end();
				endView.setVisible(true);
				dispose();
			}
		});
		endBtn.setBounds(12, 104, 91, 37);
		contentPane.add(endBtn);
		
		JButton restStartBtn = new JButton("休憩開始");
		restStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restStart reStartView = new restStart();
				reStartView.setVisible(true);
				dispose();
			}
		});
		restStartBtn.setBounds(12, 151, 91, 37);
		contentPane.add(restStartBtn);
		
		JButton restEndBtn = new JButton("休憩終了");
		restEndBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restEnd reEnd = new restEnd();
				reEnd.setVisible(true);
				dispose();
			}
		});
		restEndBtn.setBounds(12, 198, 91, 37);
		contentPane.add(restEndBtn);
		
		JButton infobtn = new JButton("勤怠情報");
		infobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dutyList dtListView = new dutyList();
				dtListView.setVisible(true);
				dispose();
			}
		});
		infobtn.setBounds(12, 245, 91, 37);
		contentPane.add(infobtn);
		
		JButton logOutBtn = new JButton("ログアウト");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWithJava loginView = new loginWithJava();
				loginView.setVisible(true);
				dispose();
			}
		});
		logOutBtn.setBounds(12, 328, 104, 37);
		contentPane.add(logOutBtn);
	}
}
