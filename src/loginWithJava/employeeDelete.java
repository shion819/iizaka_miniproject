package loginWithJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class employeeDelete extends JFrame {

	private JPanel contentPane;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeDelete frame = new employeeDelete();
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
	public employeeDelete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(184, 57, 438, 308);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblid = new JLabel("削除したい従業員のIdを入力してください");
		lblid.setBounds(45, 43, 299, 13);
		panel.add(lblid);
		
		idField = new JTextField();
		idField.setBounds(92, 142, 96, 19);
		panel.add(idField);
		idField.setColumns(10);
		
		JButton deletebtn = new JButton("削除");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement myPS = null;
				PreparedStatement myPS2 = null;
				PreparedStatement myPS3 = null;
				PreparedStatement myPS4 = null;
				PreparedStatement myPS5 = null;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					String sql ="DELETE FROM workschedule WHERE employee_id=?";
					String sql2 = "SELECT personalInfo_id FROM employee WHERE id=?";
					String sql3 ="DELETE FROM employee WHERE id = ?";
					String sql4 ="DELETE FROM personalinfo WHERE id = ?";
					String sql5 ="SELECT * FROM employee WHERE id = ?";
					String id = idField.getText();
					boolean idAttended = false;
					
					myPS = conn.prepareStatement(sql);
					myPS2 = conn.prepareStatement(sql2);
					myPS3 = conn.prepareStatement(sql3);
					myPS4 = conn.prepareStatement(sql4);
					myPS5 = conn.prepareStatement(sql5);

					myPS5.setString(1,id);
					
					ResultSet myRS2 = myPS5.executeQuery();
					
					while(myRS2.next()) {
						
						myPS2.setString(1,id);
						ResultSet myRS = myPS2.executeQuery();
						
						if(myRS2!=null) {
							idAttended =true;
							myPS.setString(1,id);
							myPS.execute();
							while(myRS.next()) {
								String pId = myRS.getString("personalInfo_id");
								myPS3.setString(1, id);
								myPS4.setString(1, pId);
								
								myPS3.execute();
								myPS4.execute();
								
								JOptionPane.showMessageDialog(deletebtn, "従業員を削除しました");
								}
						}
					}if(idAttended==false) {
							JOptionPane.showMessageDialog(deletebtn, "存在しないIdです");
						}
					
					
				} catch (Exception e) {
					e.printStackTrace();
			}
			}
		});
		deletebtn.setBounds(200, 141, 91, 21);
		panel.add(deletebtn);
		
		JLabel label = new JLabel("従業員削除ページ");
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
