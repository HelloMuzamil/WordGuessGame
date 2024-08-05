import javax.swing.*;
import java.awt.*;

public class gameover extends JFrame {
    public gameover() {
        super("Game Over");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Game Over", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton button = new JButton("Restart");
        button.addActionListener(e -> {
            // Add code here to restart the game
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(gameover::new);
    }
}
