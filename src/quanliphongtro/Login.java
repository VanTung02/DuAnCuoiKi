package quanliphongtro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JLabel userLabel, passwordLabel;
    JTextField userField;
    JPasswordField passwordField;
    JButton loginButton;

    public Login() {
        setTitle("Đăng nhập");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        userLabel = new JLabel("Tài khoản:");
        passwordLabel = new JLabel("Mật khẩu:");
        userField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        JPanel jpn = new JPanel();
        jpn.add(loginButton);
        add(jpn, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        loginButton.addActionListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
    }
		

    public void actionPerformed(ActionEvent ae) {
        String username = userField.getText();
        String password = new String(passwordField.getPassword());
   

        if (username.equals("Van Tung") && password.equals("vantung123")) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            QuanLiPhongTro qlpt = new QuanLiPhongTro();
            qlpt.setVisible(true);
            dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
			
			
	


