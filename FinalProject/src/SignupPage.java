import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupPage {

    SignupPage() {
        JFrame jframe = new JFrame("Registration Page");
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen

        // Background image
        ImageIcon backgroundImg = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\121.jpg");
        // Replace path_to_your_image.jpg with the actual path to your image file
        JLabel background = new JLabel(backgroundImg);
        background.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
        jframe.setContentPane(background);

        // Centered label on the panel
        JLabel label = new JLabel("Registration form");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(250, 180, 900, 50);
        background.add(label);

        // Set background color with alpha value to make the text clear
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150)); // Black with 150 alpha value

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setOpaque(true);
        usernameLabel.setBackground(new Color(0, 0, 0, 150));
        usernameLabel.setBounds(500, 250, 100, 30);
        background.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(600, 250, 300, 30);
        background.add(usernameField);


        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.white);
        emailLabel.setBounds(500, 300, 100, 30);
        background.add(emailLabel);
        emailLabel.setOpaque(true);
        emailLabel.setBackground(new Color(0, 0, 0, 150));
        
        JTextField emailField = new JTextField();
        emailField.setBounds(600, 300, 300, 30);
        background.add(emailField);
     ;
        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(500, 350, 100, 30);
        background.add(passwordLabel);
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(new Color(0, 0, 0, 150));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600, 350, 300, 30);
        background.add(passwordField);

        // Terms and conditions checkbox
        JCheckBox termsCheckBox = new JCheckBox("I accept the terms and conditions");
        termsCheckBox.setForeground(Color.white);
        termsCheckBox.setBackground(new Color(25, 42, 86));
        termsCheckBox.setBounds(600, 400, 300, 30);
        termsCheckBox.setFocusPainted(false);
        background.add(termsCheckBox);

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(761, 450, 140, 50);
        signupButton.setBackground(new Color(255, 226, 124));
        signupButton.setForeground(Color.black);
        signupButton.setFocusPainted(false);
        signupButton.setBorderPainted(false);
        signupButton.setOpaque(true);
        background.add(signupButton);
        signupButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(242, 243, 244));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(255, 226, 124));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || !termsCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields and accept the terms and conditions.");
                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordGuessGameDB", "root", "Mian@home802977");
                        String query = "INSERT INTO GuessTheWord (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, username);
                        ps.setString(2, email);
                        ps.setString(3, password);

                        int result = ps.executeUpdate();
                        if (result > 0) {
                            JOptionPane.showMessageDialog(null, "Successfully signed up!");
                            // Navigate to the game page
                            new UserSelectionPage();
                            jframe.dispose(); // Close the signup page
                        } else {
                            JOptionPane.showMessageDialog(null, "Signup failed, please try again.");
                        }

                        con.close();
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 450, 140, 50);
        background.add(backButton);
        backButton.setBackground(new Color(170, 219, 30));
        backButton.setForeground(Color.black);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(242, 243, 244));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(170, 219, 30));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserSelectionPage();
                jframe.dispose();
            }
        });

        jframe.setLayout(null); // Use null layout to set bounds manually

        // Frame settings
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        new SignupPage();
    }
}
