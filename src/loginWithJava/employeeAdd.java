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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

public class employeeAdd extends JFrame {

	private JPanel contentPane;
	private JTextField passField;
	private JTextField MyNumberField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField schoolField;
	private JTextField nameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeAdd frame = new employeeAdd();
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
	public employeeAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(139, 52, 754, 405);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("追加する情報を入力してください");
		label_1.setBounds(58, 35, 233, 13);
		panel.add(label_1);
		
		String[] gender = { "male", "female" };
		JComboBox genderCombo = new JComboBox(gender);
		genderCombo.setBounds(102, 145, 96, 19);
		panel.add(genderCombo);

		String[] role = { "normal", "admin" };
		JComboBox roleCombo = new JComboBox(role);
		roleCombo.setBounds(102, 174, 96, 19);
		panel.add(roleCombo);

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDate(java.sql.Date.valueOf(java.time.LocalDate.now())); 
		dateChooser.setBounds(102, 203, 96, 19);
		panel.add(dateChooser);

		passField = new JTextField();
		passField.setBounds(102, 232, 96, 19);
		panel.add(passField);
		passField.setColumns(10);

		MyNumberField = new JTextField();
		MyNumberField.setBounds(354, 87, 200, 19);
		panel.add(MyNumberField);
		MyNumberField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(354, 116, 200, 19);
		panel.add(phoneField);
		phoneField.setColumns(10);

		emailField = new JTextField();
		emailField.setBounds(354, 145, 265, 19);
		panel.add(emailField);
		emailField.setColumns(10);

		addressField = new JTextField();
		addressField.setBounds(354, 174, 388, 19);
		panel.add(addressField);
		addressField.setColumns(10);

		schoolField = new JTextField();
		schoolField.setBounds(354, 206, 133, 19);
		panel.add(schoolField);
		schoolField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(102, 116, 96, 19);
		panel.add(nameField);
		nameField.setColumns(10);

		JButton AddBtn = new JButton("追加");
		AddBtn.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement myPS = null;
				PreparedStatement myPS2 = null;
				PreparedStatement myPS3 = null;

				String myNumber = MyNumberField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				String school = schoolField.getText();
				String name = nameField.getText();
				String pass = passField.getText();
				int genderIndex = genderCombo.getSelectedIndex();
				int roleIndex = roleCombo.getSelectedIndex();
				String gender = genderCombo.getItemAt(genderIndex).toString();
				String role = roleCombo.getItemAt(roleIndex).toString();
				String inDate = df.format(dateChooser.getDate());
				boolean employee=false;
				
				try {

					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management", "root", "yukkuri");

					String sql = "INSERT INTO personalinfo ( `MyNumber`, `phone`, `email`, `address`, `school`)"
							+ " VALUES ( ?, ?, ?, ?, ?)";

					String sql2 = "INSERT INTO `employee` ( `name`, `gender`, `role`, `inDate`, `pass`, `personalInfo_id`) "
							+ " VALUES ( ?, ?, ?, ?, ?, ?)";
					
					String sql3 = "Select max(id) as pId from personalinfo";

					myPS = conn.prepareStatement(sql);
					myPS2 = conn.prepareStatement(sql2);
					myPS3 = conn.prepareStatement(sql3);
					
					if(!(myNumber.isEmpty())&&!(phone.isEmpty())&&!(email.isEmpty())&&!(address.isEmpty())&&!(school.isEmpty())) {
						myPS.setString(1, myNumber);
						myPS.setString(2, phone);
						myPS.setString(3, email);
						myPS.setString(4, address);
						myPS.setString(5, school);
						myPS.execute();
						
						ResultSet myRS = myPS3.executeQuery();
						while(myRS.next()) {
							if(!(name.isEmpty())||!(pass.isEmpty())) {
								employee=true;
						String pId = myRS.getString("pId");
						myPS2.setString(1, name);
						myPS2.setString(2, gender);
						myPS2.setString(3, role);
						myPS2.setString(4, inDate);
						myPS2.setString(5, pass);
						myPS2.setString(6,pId);
						myPS2.execute();
						JOptionPane.showMessageDialog(AddBtn, "新しい社員を追加しました");
							}if(employee==false) {
								JOptionPane.showMessageDialog(AddBtn,"入力していない項目が存在します");
							}
						}
					}else {
						JOptionPane.showMessageDialog(AddBtn,"入力していない項目が存在します");
					}		

				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		});
		AddBtn.setBounds(600, 335, 91, 21);
		panel.add(AddBtn);


		JLabel lblSeibet = new JLabel("性別：");
		lblSeibet.setBounds(42, 148, 48, 13);
		lblSeibet.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(lblSeibet);

		JLabel label_2 = new JLabel("役職：");
		label_2.setBounds(42, 177, 48, 13);
		label_2.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_2);

		JLabel label_3 = new JLabel("入社日：");
		label_3.setBounds(31, 209, 59, 13);
		label_3.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_3);

		JLabel label_4 = new JLabel("パスワード：");
		label_4.setBounds(12, 235, 78, 13);
		label_4.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_4);

		JLabel label_5 = new JLabel("マイナンバー：");
		label_5.setBounds(246, 90, 96, 13);
		label_5.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_5);

		JLabel label_6 = new JLabel("電話番号：");
		label_6.setBounds(274, 119, 68, 13);
		label_6.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_6);

		JLabel emailLabel = new JLabel("e-mailアドレス：");
		emailLabel.setBounds(230, 148, 112, 13);
		emailLabel.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(emailLabel);

		JLabel label_7 = new JLabel("住所：");
		label_7.setBounds(286, 177, 56, 13);
		label_7.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_7);

		JLabel label_8 = new JLabel("学校名：");
		label_8.setBounds(274, 209, 69, 13);
		label_8.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("名前：");
		label_9.setBounds(40, 119, 50, 13);
		label_9.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_9);
		
		JButton backBtn = new JButton("戻る");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin adminView =new admin();
				adminView.setVisible(true);
				dispose();
			}
		});
		backBtn.setBounds(38, 335, 68, 21);
		panel.add(backBtn);

		JLabel label = new JLabel("従業員追加ページ");
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
				employeeAdd employeeAddView = new employeeAdd();
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
