import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionPage {

    public InstructionPage() {
        JFrame frame = new JFrame("Instructions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); 

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

               
                Image backgroundImage = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\125.PNG").getImage();

              
                g2d.drawImage(backgroundImage, 0, 0, width, height, null);
            }
        };
        backgroundPanel.setBounds(0, 0, 900, 600);
        frame.add(backgroundPanel);
        backgroundPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Instructions");
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 50, 900, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(titleLabel);

        JTextArea instructionText = new JTextArea();
        instructionText.setText("IN GUEST MODE YOU ARE ABLE TO PLAY AN EASYLIST GAME ONLY!");
        instructionText.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        instructionText.setWrapStyleWord(true);
        instructionText.setLineWrap(true);
        instructionText.setOpaque(false);
        instructionText.setEditable(false);
        instructionText.setForeground(Color.WHITE);
        instructionText.setBounds(100, 150, 700, 200);
        backgroundPanel.add(instructionText);

        JButton backButton = new JButton("OK");
        backButton.setBounds(375, 400, 150, 50);
        backButton.setBackground(new Color(170, 219, 30));
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	   frame.dispose();
                new EasyList(null);
             
            }
        });

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(242, 243, 244));
                frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(170, 219, 30));
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        backgroundPanel.add(backButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InstructionPage());
    }
}
