package loginWithJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class employeeChange extends JFrame {

	private JPanel contentPane;
	private JTextField SarchField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField schoolField;
	private JTable table;
	private JTextField nameSarchField;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeChange frame = new employeeChange();
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
	public employeeChange() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(184, 57, 712, 399);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel shoolField = new JLabel("変更したい従業員のIdを入力してください");
		shoolField.setBounds(54, 30, 247, 13);
		panel.add(shoolField);
		
		SarchField = new JTextField();
		SarchField.setBounds(135, 53, 96, 19);
		panel.add(SarchField);
		SarchField.setColumns(10);
		
		nameSarchField = new JTextField();
		nameSarchField.setBounds(246, 53, 96, 19);
		panel.add(nameSarchField);
		nameSarchField.setColumns(10);
		
		JLabel lblId = new JLabel("Id：");
		lblId.setFont(new Font("MS UI Gothic", Font.PLAIN, 16));
		lblId.setBounds(109, 55, 25, 13);
		panel.add(lblId);
		
		nameField = new JTextField();
		nameField.setBounds(146, 285, 96, 19);
		panel.add(nameField);
		nameField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(146, 314, 96, 19);
		panel.add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(368, 314, 246, 19);
		panel.add(emailField);
		emailField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(145, 343, 417, 19);
		panel.add(addressField);
		addressField.setColumns(10);
		
		schoolField = new JTextField();
		schoolField.setBounds(368, 285, 176, 19);
		panel.add(schoolField);
		schoolField.setColumns(10);		
		
		idField = new JTextField();
		idField.setBounds(646, 285, 54, 19);
		panel.add(idField);
		idField.setColumns(10);
		idField.setEditable(false);

		JLabel nameLabel = new JLabel("名前：");
		nameLabel.setBounds(94, 288, 50, 13);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(nameLabel);
		
		JLabel label_1 = new JLabel("電話番号：");
		label_1.setBounds(48, 317, 96, 13);
		label_1.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_1);
		
		JLabel lblEmail = new JLabel("e-mailアドレス：");
		lblEmail.setBounds(264, 317, 104, 13);
		lblEmail.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(lblEmail);
		
		JLabel label_2 = new JLabel("学校：");
		label_2.setBounds(298, 288, 70, 13);
		label_2.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("住所：");
		label_3.setBounds(94, 346, 50, 13);
		label_3.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(label_3);
		
		JButton sarchBtn = new JButton("検索");
		sarchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement myPS2 = null;
				PreparedStatement myPS3 = null;
				PreparedStatement myPS4 = null;
				String SarchNumber = SarchField.getText();
				String SarchName = nameSarchField.getText();
				boolean textBox =false;
				boolean idName = false;
				
				try {
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					String sql ="SELECT e.id, e.name, p.phone, p.email, p.address, p.school "
							+ "FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id where e.id=?";
					
					String sql2 ="SELECT e.id, e.name, p.phone, p.email, p.address, p.school "
							+ "FROM employee AS e inner join personalInfo AS p ON e.personalInfo_id = p.id where e.name LIKE ?";
					
					String sql3 = "SELECT e.id, e.name,p.phone, p.email, p.address, p.school "
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
		sarchBtn.setBounds(354, 52, 79, 21);
		panel.add(sarchBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				
				idField.setText(model.getValueAt(i,0).toString());
				nameField.setText(model.getValueAt(i,1).toString());
				phoneField.setText(model.getValueAt(i,2).toString());
				emailField.setText(model.getValueAt(i,3).toString());
				addressField.setText(model.getValueAt(i,4).toString());
				schoolField.setText(model.getValueAt(i,5).toString());
			}
		});
		scrollPane.setBounds(94, 83, 566, 192);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		
		
		JButton ChangeBtn = new JButton("変更");
		ChangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				PreparedStatement myPS = null;
				PreparedStatement myPS2 = null;
				String id = idField.getText();
				String name = nameField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				String school = schoolField.getText();
				
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					String sql ="UPDATE personalinfo SET phone=?, email=?, address=?, school=? WHERE id=?";
					String sql2 ="UPDATE employee SET name=? WHERE id=?";
						
					myPS = conn.prepareStatement(sql);
					myPS2 = conn.prepareStatement(sql2);
					
					myPS.setString(1,phone);
					myPS.setString(2,email);
					myPS.setString(3,address);
					myPS.setString(4,school);
					myPS.setString(5,id);

					myPS2.setString(1, name);
					myPS2.setString(2, id);
							
					int myRS = myPS.executeUpdate();
					int myRS2 =myPS2.executeUpdate();
					JOptionPane.showMessageDialog(ChangeBtn, "従業員情報を変更しました");
					
					employeeChange employeeChangeView = new employeeChange();
					employeeChangeView.setVisible(true);
					dispose();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		ChangeBtn.setBounds(609, 368, 91, 21);
		panel.add(ChangeBtn);
		
		JLabel lblId_1 = new JLabel("Id:");
		lblId_1.setBounds(596, 288, 50, 13);
		lblId_1.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(lblId_1);

		
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
