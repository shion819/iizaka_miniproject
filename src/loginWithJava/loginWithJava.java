package loginWithJava;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class loginWithJava extends JFrame {

	private JPanel contentPane;
	private JTextField idfield;
	private JTextField passfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginWithJava frame = new loginWithJava();
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
	public loginWithJava() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdpassword = new JLabel("Id又はPasswordを入力してください。\r\n");
		lblIdpassword.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblIdpassword.setBounds(39, 32, 291, 74);
		contentPane.add(lblIdpassword);
		
		JLabel lblId = new JLabel("Id：");
		lblId.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblId.setBounds(49, 116, 50, 13);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password：");
		lblPassword.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblPassword.setBounds(49, 150, 100, 27);
		contentPane.add(lblPassword);
		
		idfield = new JTextField();
		idfield.setBounds(147, 116, 91, 18);
		contentPane.add(idfield);
		idfield.setColumns(10);
		
		passfield = new JTextField();
		passfield.setColumns(10);
		passfield.setBounds(147, 156, 91, 18);
		contentPane.add(passfield);
		
		JButton button = new JButton("ログイン");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String inputId = idfield.getText();
				String inputPass = passfield.getText();
				
				String iddb;
				String passdb;
				
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","yukkuri");
					
			
					Statement myst = conn.createStatement();
					
					ResultSet myRS =myst.executeQuery("Select id, pass from employee");
					
					boolean isUser =false;
					
					while(myRS.next()){
						iddb = myRS.getString("id");
						passdb = myRS.getString("pass");
					
						if(inputId.equals(iddb) && inputPass.equals(passdb)) {
							isUser = true;
							if(inputId.equals("1")) {
								admin a = new admin();
								a.setVisible(true);
							}else {
								ippann v = new ippann();
								v.setVisible(true);
							}
						}
					}
					if (isUser == false) {
						JOptionPane.showMessageDialog(button, "Id又はPasswordが間違っています。");
					}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		button.setBounds(147, 205, 91, 21);
		contentPane.add(button);
		
		
	}
	}

