import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DIfficultyLevelPage {

    public DIfficultyLevelPage() {
        JFrame jframe = new JFrame();
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jframe.setLayout(new BorderLayout());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\77.PNG").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        jframe.add(backgroundPanel);

        JLabel label = new JLabel("Choose a difficulty level");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setBorder(BorderFactory.createEmptyBorder(200, 50, 0, 0));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        backgroundPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        backgroundPanel.add(buttonPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 240, 20, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JButton button1 = new JButton("Easy");
        button1.setPreferredSize(new Dimension(180, 60));
        button1.setBackground(new Color(60, 156, 116));
        button1.setForeground(Color.black);
        button1.setFocusPainted(false);
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addHoverEffect(button1, new Color(60, 156, 116), new Color(80, 176, 136));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EasyList E = new EasyList(null);
                jframe.dispose();
            }
        });
        buttonPanel.add(button1, gbc);

        JButton button2 = new JButton("Medium");
        button2.setPreferredSize(new Dimension(180, 60));
        button2.setBackground(new Color(250, 217, 120));
        button2.setForeground(Color.black);
        button2.setFocusPainted(false);
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addHoverEffect(button2, new Color(250, 217, 120), new Color(255, 237, 140));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MediumList M = new MediumList(null);
                jframe.dispose();
            }
        });
        gbc.gridy = 1;
        buttonPanel.add(button2, gbc);

        JButton button3 = new JButton("Hard");
        button3.setPreferredSize(new Dimension(180, 60));
        button3.setBackground(new Color(36, 36, 36));
        button3.setForeground(Color.white);
        button3.setFocusPainted(false);
        button3.setBorderPainted(false);
        button3.setOpaque(true);
        button3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addHoverEffect(button3, new Color(36, 36, 36), new Color(56, 56, 56));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HardList hard = new HardList(null);
                jframe.dispose();
            }
        });
        gbc.gridy = 2;
        buttonPanel.add(button3, gbc);

        JButton backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(180, 60));
        backButton.setBackground(new Color(165, 72, 104, 255));
        backButton.setForeground(Color.white);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addHoverEffect(backButton, new Color(165, 72, 104, 255), new Color(185, 92, 124));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserSelectionPage();
                jframe.dispose();
            }
        });
        gbc.gridy = 3;
        buttonPanel.add(backButton, gbc);

        jframe.setVisible(true);
    }

    private void addHoverEffect(JButton button, Color originalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }

    public static void main(String[] args) {
        new DIfficultyLevelPage();
    }
}
