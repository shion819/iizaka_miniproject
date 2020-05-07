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

public class admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("従業員管理ページ");
		label.setBounds(12, 10, 165, 13);
		contentPane.add(label);
		
		JButton button = new JButton("従業員一覧");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(12, 50, 102, 40);
		contentPane.add(button);
		
		JButton button_1 = new JButton("従業員追加");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(12, 100, 102, 40);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("従業員削除");
		button_2.setBounds(12, 150, 102, 40);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("情報の変更");
		button_3.setBounds(12, 200, 102, 40);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("ログアウト");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login loginView = new login();
				loginView.setVisible(true);
				dispose();
			}
		});
		button_4.setBounds(12, 273, 102, 40);
		contentPane.add(button_4);
	}

}
