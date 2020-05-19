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
		
		JButton listBtn = new JButton("従業員一覧");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeList employeeListView = new employeeList();
				employeeListView.setVisible(true);
				dispose();
			}
		});
		listBtn.setBounds(12, 50, 102, 40);
		contentPane.add(listBtn);
		
		JButton addBtn = new JButton("従業員追加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				employeeAdd employeeAddView =new employeeAdd();
				employeeAddView.setVisible(true);
				dispose();
			}
		});
		addBtn.setBounds(12, 100, 102, 40);
		contentPane.add(addBtn);
		
		JButton deleteBtn = new JButton("従業員削除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeDelete employeeDeleteView =new employeeDelete();
				employeeDeleteView.setVisible(true);
				dispose();
			}
		});
		deleteBtn.setBounds(12, 150, 102, 40);
		contentPane.add(deleteBtn);
		
		JButton changeBtn = new JButton("情報の変更");
		changeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeChange employeeChangeView = new employeeChange();
				employeeChangeView.setVisible(true);
				dispose();
			}
		});
		changeBtn.setBounds(12, 200, 102, 40);
		contentPane.add(changeBtn);
		
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
