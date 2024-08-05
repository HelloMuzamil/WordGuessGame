import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HardList {
    JFrame frame;
    JTextField inputField;
    JLabel hintLabel;
    JLabel wordLabel;
    JLabel attemptsLabel;
    JLabel resultLabel;
    JLabel scoreLabel;
    JButton guessButton = new JButton("GUESS");
    String[] wordlist = {
            "ABSTRACT", "EFFICIENT", "CONSTANT", "RECURSION", "INFINITE",
            "EFFICACY", "ALGORITHM", "FUNCTION", "VARIABLE", "DECISION"
    };

    String[] COMBINATION = {
            "B,T,A,R,S,C,A", "F,F,E,I,I,N,T,C", "T,S,A,N,O,C,N", "C,R,U,R,I,E,S,O,N", "I,E,F,N,I,T,N",
            "C,I,F,A,E,C,Y", "L,A,O,G,I,T,R,I,T", "N,O,I,T,C,N,U,F", "E,C,N,I,T,I,F,O", "O,I,S,N,D,E,C"
    };

    String[] hints = {
            "Conceptual", "Productive", "Unchanging", "Iterative", "Endless",
            "Effectiveness", "Procedure", "Purpose", "Changeable", "Choice"
    };

    int currentLevel = 0;
    int score = 0;
    int[] attempts = new int[10];  // Initialize attempts array
    String username;

    public HardList(String username) {
        this.username = username;
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\1222.jpg");
        Image backgroundImage = imageIcon.getImage();
        
        
        if (LoginPage.currentUser == null) {
            // Show a message that the user needs to login first
            JOptionPane.showMessageDialog(null, "Please login first.");
            // You can choose to navigate back to login or handle it as per your application flow
            // For simplicity, you can dispose the frame and return
            frame.dispose();
            return;
        }

        frame = new JFrame("Word Guess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        JLabel label = new JLabel("The Battle Begins!");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150));
        label.setBounds(0, 180, 1400, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label);

        wordLabel = new JLabel("", JLabel.CENTER);
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wordLabel.setBounds(500, 300, 400, 30);
        frame.add(wordLabel);

        inputField = new JTextField(10);
        inputField.setBounds(600, 350, 200, 30);
        frame.add(inputField);

        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setBounds(760, 350, 200, 30);
        frame.add(attemptsLabel);
        // Add key listener to inputField to handle Enter key press
        inputField.addActionListener(e -> checkGuess());

        guessButton.setBounds(500, 450, 150, 50);
        guessButton.setBackground(new Color(165, 72, 104, 255));
        guessButton.setForeground(Color.white);
        guessButton.setFocusPainted(false);
        guessButton.setBorderPainted(false);
        guessButton.setOpaque(true);
        guessButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        guessButton.addActionListener(e -> checkGuess());
        frame.add(guessButton);

        guessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guessButton.setBackground(new Color(20, 10, 27, 255));
                frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                guessButton.setBackground(new Color(165, 72, 104, 255));
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(750, 450, 150, 50);
        backButton.setBackground(new Color(165, 72, 104, 255));
        backButton.setForeground(Color.white);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        backButton.addActionListener(e -> {
          new DIfficultyLevelPage();
           frame.dispose();
        });
        frame.add(backButton);

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(20, 10, 27, 255));
                frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(165, 72, 104, 255));
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(500, 500, 400, 50);
        frame.add(resultLabel);

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(new Color(20, 10, 27, 255));
        scoreLabel.setOpaque(true);
        scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        scoreLabel.setBounds(600, 550, 200, 30);
        frame.add(scoreLabel);
        hintLabel = new JLabel("", JLabel.CENTER);
        hintLabel.setForeground(Color.WHITE);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hintLabel.setBounds(500, 270, 400, 30);
        frame.add(hintLabel);

        frame.setVisible(true);

        showLevel();
    }
    public void showLevel() {
        if (currentLevel < wordlist.length) {
            wordLabel.setText("FIND THE WORD  = " + COMBINATION[currentLevel]);
            hintLabel.setText("Hint: " + hints[currentLevel]);
        }
    }
    public void checkGuess() {
        String targetWord = wordlist[currentLevel];
        String combination = COMBINATION[currentLevel];
        String playerGuess = inputField.getText();
        attempts[currentLevel]++;

        if (attempts[currentLevel] <= 3) {
            if (playerGuess.equalsIgnoreCase(targetWord)) {
                int pointsEarned = Math.max(10 - attempts[currentLevel] + 1, 1);
                score += pointsEarned;
                resultLabel.setText("You found the word in " + attempts[currentLevel] + " attempts.");
                currentLevel++;
                if (currentLevel < wordlist.length) {
                    showLevel();
                } else {
                    endGame();
                }
            } else {
                resultLabel.setText("Wrong answer, try again");
            }

            attemptsLabel.setText("Attempts: " + attempts[currentLevel]);
            inputField.setText("");
            scoreLabel.setText("Score: " + score);
        } else {
            resultLabel.setText("You have reached the maximum attempts for this word.");
            inputField.setEnabled(false); // Disable input field
            guessButton.setEnabled(false); // Disable guess button
            playSound("C:\\Users\\DELL\\eclipse-workspace\\GUI2\\src\\G.wav"); // Play sound

            // Customizing the "Game Over" dialog box
            JFrame dialogFrame = new JFrame("Game Over");
            dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialogFrame.setSize(400, 200);
            dialogFrame.setLocationRelativeTo(null); // Center the dialog on the screen

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel messageLabel = new JLabel("Game Over! Your final score is " + score);
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(messageLabel, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
                dialogFrame.dispose();
                // Close the main frame
            });
            panel.add(closeButton, BorderLayout.SOUTH);

            dialogFrame.setContentPane(panel);
            dialogFrame.setVisible(true);
        }
    }

    public void endGame() {
        playSound("C:\\Users\\DELL\\eclipse-workspace\\GUI2\\src\\G.wav"); // Play win sound

        // Customizing the "Game Over" dialog box
        JFrame dialogFrame = new JFrame("Congratulations");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(400, 200);
        dialogFrame.setLocationRelativeTo(null); // Center the dialog on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Congratulations! You completed all levels. Your final score is " + score);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialogFrame.dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        dialogFrame.setContentPane(panel);
        dialogFrame.setVisible(true);
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HardList("User");
    }
}
