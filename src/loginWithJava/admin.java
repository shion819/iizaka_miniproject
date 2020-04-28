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
		setBounds(100, 100, 548, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("従業員管理ページ");
		label.setBounds(12, 10, 145, 45);
		contentPane.add(label);
		
		JButton listBtn = new JButton("従業員一覧");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		listBtn.setBounds(12, 65, 102, 39);
		contentPane.add(listBtn);
		
		JButton addBtn = new JButton("従業員の追加");
		addBtn.setBounds(12, 114, 102, 39);
		contentPane.add(addBtn);
		
		JButton deleteBtn = new JButton("従業員の削除\r\n");
		deleteBtn.setBounds(12, 163, 102, 39);
		contentPane.add(deleteBtn);
		
		JButton changeBtn = new JButton("情報の変更");
		changeBtn.setBounds(12, 212, 102, 39);
		contentPane.add(changeBtn);
		
		JButton logOutBtn = new JButton("ログアウト");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWithJava l = new loginWithJava();
				l.setVisible(true);
				dispose();
			}
		});
		logOutBtn.setBounds(12, 306, 102, 39);
		contentPane.add(logOutBtn);
	}

}
