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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;

public class employeeList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField SarchField;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeList frame = new employeeList();
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
	public employeeList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(139, 52, 805, 318);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 55, 781, 248);
		panel.add(scrollPane_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		JLabel label = new JLabel("従業員一覧");
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
		deleteBtn.setBounds(12, 150, 102, 40);
		contentPane.add(deleteBtn);
		
		JButton changeBtn = new JButton("情報の変更");
		changeBtn.setBounds(12, 200, 102, 40);
		contentPane.add(changeBtn);
		
		JButton logoutBtn = new JButton("ログアウト");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login loginView = new login();
				loginView.setVisible(true);
				dispose();
			}
		});
		logoutBtn.setBounds(12, 273, 102, 40);
		contentPane.add(logoutBtn);
		
		Connection conn = null;
		PreparedStatement myPS = null;
		

		table = new JTable();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
			String sql ="SELECT e.id, e.name, e.gender, p.phone, p.email, p.address, p.school FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id";
			myPS = conn.prepareStatement(sql);
			ResultSet myRS = myPS.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(myRS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		SarchField = new JTextField();
		SarchField.setBounds(345, 26, 27, 19);
		panel.add(SarchField);
		SarchField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(216, 26, 96, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JButton sarchBtn = new JButton("検索");
		sarchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement myPS2 = null;
				PreparedStatement myPS3 = null;
				PreparedStatement myPS4 = null;
				String SarchNumber = SarchField.getText();
				String SarchName = nameField.getText();
				boolean textBox =false;
				boolean idName = false;
				
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					String sql ="SELECT e.id, e.name, e.gender, p.phone, p.email, p.address, p.school "
							+ "FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id where e.id=?";
					
					String sql2 ="SELECT e.id, e.name, e.gender, p.phone, p.email, p.address, p.school "
							+ "FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id where e.name LIKE ?";
					
					String sql3 = "SELECT e.id, e.name, e.gender, p.phone, p.email, p.address, p.school "
							+ "FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id where e.id=? and e.name LIKE ?";
					
					myPS2 = conn.prepareStatement(sql);
					myPS3 = conn.prepareStatement(sql2);
					myPS4 = conn.prepareStatement(sql3);
					
					if(!(SarchNumber.isEmpty())||!(SarchName.isEmpty())){
						textBox = true;
						idName=false;
						myPS4.setString(1,SarchNumber);
						myPS4.setString(2,"%"+SarchName+"%");
						ResultSet myRS4 = myPS4.executeQuery();
						if(myRS4!=null) {
							idName=true;
							table.setModel(DbUtils.resultSetToTableModel(myRS4));
							if(!(SarchName.isEmpty())) {
								if(SarchNumber.isEmpty()) {
									myPS3.setString(1,"%"+SarchName+"%");
									ResultSet myRS3 = myPS3.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(myRS3));
								}
							}
						}if(idName==false) {
								myPS3.setString(1,"%"+SarchName+"%");
								ResultSet myRS3 = myPS3.executeQuery();
								table.setModel(DbUtils.resultSetToTableModel(myRS3));
							}
					}
					if(textBox==false){
						JOptionPane.showMessageDialog(sarchBtn, "名前又はIdを入力してください");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		sarchBtn.setBounds(384, 25, 79, 21);
		panel.add(sarchBtn);
		
		JLabel lblId = new JLabel("id:");
		lblId.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		lblId.setBounds(324, 26, 14, 16);
		panel.add(lblId);
		
		JLabel nameLabel = new JLabel("名前:");
		nameLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		nameLabel.setBounds(182, 27, 32, 16);
		panel.add(nameLabel);


		
	}
}
