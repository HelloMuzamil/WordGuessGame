import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserSelectionPage {

    public UserSelectionPage() {
        JFrame jframe = new JFrame();
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);

        
        ImagePanel backgroundPanel = new ImagePanel("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\123.png");
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());

      
        JLabel label = new JLabel("Choose! What Do You Want");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);


        label.setBorder(BorderFactory.createEmptyBorder(20,150, 0, 0)); 
        label.setHorizontalAlignment(SwingConstants.LEFT); 
        label.setBounds(20, 250, jframe.getWidth() - 40, 50); 
        backgroundPanel.add(label);

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // First button
        JButton button1 = new JButton("LoginPage");
        button1.setBounds(350, 320, 220, 50); 
        backgroundPanel.add(button1);
        button1.setBackground(new Color(170, 219, 30));
        button1.setForeground(Color.black);
        button1.setFocusPainted(false);
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
             
                
            }
        });

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

        // Second Button
        JButton button2 = new JButton("SignupPage");
        button2.setBounds(350, 390, 220, 50); 
        backgroundPanel.add(button2);
        button2.setBackground(new Color(255, 226, 124));
        button2.setForeground(Color.black);
        button2.setFocusPainted(false);
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setFont(new Font("Times New Roman", Font.BOLD, 18));

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignupPage();
               jframe.dispose();
            }
        });

        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button2.setBackground(new Color(242, 243, 244));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button2.setBackground(new Color(255, 226, 124));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Third Button
        JButton button3 = new JButton("GuestUser");
        button3.setBounds(350, 460, 220, 50); 
        backgroundPanel.add(button3);
        button3.setBackground(new Color(255, 0, 0));
        button3.setForeground(Color.black);
        button3.setFocusPainted(false);
        button3.setBorderPainted(false);
        button3.setOpaque(true);
        button3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button3.addActionListener(e -> {
            new InstructionPage();
        });
        button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button3.setBackground(new Color(242, 243, 244));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button3.setBackground(new Color(255, 0, 0));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        
        jframe.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                label.setBounds(20, 250, jframe.getWidth() - 40, 50);
                backgroundPanel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(350, 530, 220, 50); 
        backgroundPanel.add(backButton);
        backButton.setBackground(new Color(165, 72, 104, 255));

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
                backButton.setBackground(new Color(165, 72, 104, 255));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartUpPage();
                jframe.dispose();
            }
        });

        jframe.add(backgroundPanel); 
        jframe.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserSelectionPage());
    }
}

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
