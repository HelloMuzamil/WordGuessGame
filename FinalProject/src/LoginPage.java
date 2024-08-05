import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage {
    public static String currentUser; 

    public LoginPage() {
        JFrame jframe = new JFrame("Login Page");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setSize(1400, 800);
        jframe.setLayout(null);

        // Add background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\1212.jpg");
        Image img = backgroundImage.getImage();
        Image newImg = img.getScaledInstance(jframe.getWidth(), jframe.getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(newImg);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
        jframe.add(backgroundLabel);

        JLabel label = new JLabel("Login Page");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 180, 1400, 100);
        backgroundLabel.add(label);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150));

        JLabel jlabel = new JLabel("Enter Username");
        jlabel.setBounds(520, 300, 150, 30);
        jlabel.setForeground(Color.white);
        backgroundLabel.add(jlabel);
        jlabel.setOpaque(true);
        jlabel.setBackground(new Color(0, 0, 0, 150));

        JLabel jlabel2 = new JLabel("Enter Password");
        jlabel2.setBounds(520, 350, 150, 30);
        jlabel2.setForeground(Color.white);
        backgroundLabel.add(jlabel2);
        jlabel2.setOpaque(true);
        jlabel2.setBackground(new Color(0, 0, 0, 150));

        JTextField text2 = new JTextField();
        text2.setBounds(670, 300, 200, 30);
        backgroundLabel.add(text2);

        JPasswordField jpassword = new JPasswordField();
        jpassword.setBounds(670, 350, 200, 30);
        backgroundLabel.add(jpassword);

        JButton button1 = new JButton("Login");
        button1.setBounds(520, 420, 350, 50);
        backgroundLabel.add(button1);
        button1.setBackground(new Color(170, 219, 30));
        button1.setForeground(Color.black);
        button1.setFocusPainted(false);
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1.setBackground(new Color(242, 243, 244));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button1.setBackground(new Color(170, 219, 30));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = text2.getText();
                String password = new String(jpassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordGuessGameDB", "root", "Mian@home802977");
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM GuessTheWord WHERE USERNAME = ? AND PASSWORD = ?");
                        ps.setString(1, username);
                        ps.setString(2, password);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            currentUser = username; 
                            JOptionPane.showMessageDialog(null, "Successfully Logged in!");
                            new DIfficultyLevelPage();
                            jframe.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password.");
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
        backButton.setBounds(520, 480, 350, 50);
        backgroundLabel.add(backButton);
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

        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
