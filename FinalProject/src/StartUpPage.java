import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPage {
    private JButton beautifulButton;

    StartUpPage() {
        JFrame jframe = new JFrame();
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setLayout(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
        jframe.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeImage(jframe);
                resizeButton(jframe);
            }
        });

        ImageIcon image = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\StartupPagePic.jpeg");
        JLabel jlabel = new JLabel("", image, JLabel.CENTER);
        jlabel.setBounds(0, 0, jframe.getWidth(), jframe.getHeight());
        jframe.add(jlabel);

        beautifulButton = new JButton("START!");
        beautifulButton.setBackground(new Color(92, 103, 182));
        beautifulButton.setForeground(Color.WHITE);
        beautifulButton.setFocusPainted(false);
        beautifulButton.setBorderPainted(false);
        beautifulButton.setOpaque(true);

        beautifulButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                beautifulButton.setBackground(new Color(255, 226, 124));
                jframe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                beautifulButton.setBackground(new Color(92, 103, 182));
                jframe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        beautifulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserSelectionPage(); 
                jframe.dispose();
            }
        });

        jframe.add(beautifulButton);

        jframe.setVisible(true);

      
        resizeImage(jframe);
        resizeButton(jframe);
    }

    private void resizeImage(JFrame jframe) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\StartupPagePic.jpeg");
        int width = jframe.getWidth();
        int height = jframe.getHeight();

        double aspectRatio = (double) imageIcon.getIconWidth() / imageIcon.getIconHeight();
        int scaledWidth = (int) (height * aspectRatio);

        Image image = imageIcon.getImage().getScaledInstance(scaledWidth, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);

        for (Component component : jframe.getContentPane().getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setIcon(imageIcon);
                label.setBounds(0, 0, width, height);
            }
        }
    }

    private void resizeButton(JFrame jframe) {
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();

        int buttonWidth = 150;  
        int buttonHeight = 50;  
        int buttonX = (frameWidth - buttonWidth) / 2;
        int buttonY = frameHeight - buttonHeight - 100; 

        beautifulButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
    }

    public static void main(String[] args) {
        new StartUpPage();
    }
}
