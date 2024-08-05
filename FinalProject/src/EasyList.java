import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EasyList {
    JFrame frame;
    JTextField inputField;
    JLabel wordLabel;
    JLabel attemptsLabel;
    JLabel resultLabel;
    JLabel scoreLabel;
    JButton guessButton = new JButton("GUESS");
    JLabel hintLabel;

    String[] wordlist = {
        "APPLE", "HOUSE", "TRAIN", "CLOUD", "RIVER", "BREAD", "GARDEN", "MUSIC", "SMILE", "TABLE",
        "DOG", "CAT", "CHAIR", "BOOK", "SCHOOL", "TREE", "WATER", "LIGHT", "BIRD", "FISH"
    };
    String[] COMBINATION = {
        "P,P,A,L,E", "O,U,S,E,H", "T,R,N,A,I", "O,L,D,U,C", "V,I,R,E,R", "B,R,E,A,D", "G,N,A,R,D,E", "M,U,S,I,C", "S,L,I,E,M", "B,T,E,A,L",
        "G,O,D", "A,C,T", "R,H,C,I,A", "O,O,K,B", "S,O,H,C,O,L", "T,E,E,R", "R,A,W,T,E", "G,I,H,L,T", "B,I,R,D", "S,I,H,F"
    };
    String[] hints = { "Fruit", "Building", "Transport", "Weather", "Water body", "Food", "Outdoor space", "Sound",
            "Facial expression", "Furniture", "Pet", "Pet", "Furniture", "Reading material", "Educational institution",
            "Plant", "Liquid", "Source of illumination", "Animal", "Animal" };

    int[] attempts = new int[20];
    int currentLevel = 0;
    int score = 0;
    String username;

    public EasyList(String username) {
        this.username = username;
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\1222.jpg");
        Image backgroundImage = imageIcon.getImage();
        
        

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

        JLabel label = new JLabel("The Battle Begins");
        label.setFont(new Font("Stencil", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setBounds(0, 180, 1400, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150));

        wordLabel = new JLabel("", JLabel.CENTER);
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        wordLabel.setBounds(500, 300, 400, 30);
        frame.add(wordLabel);
//        wordLabel.setOpaque(true);
//        wordLabel.setBackground(new Color(0, 0, 0, 150));

        inputField = new JTextField(10);
        inputField.setBounds(600, 350, 200, 30);
        frame.add(inputField);

     
        inputField.addActionListener(e -> checkGuess());

        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setBounds(760, 350, 200, 30);
        frame.add(attemptsLabel);
        

        guessButton.setBounds(500, 425, 150, 50);
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
        backButton.setBounds(750, 425, 150, 50);
        backButton.setBackground(new Color(20, 10, 27, 255));
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
                backButton.setBackground(new Color(20, 10, 27, 255));
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

        if (attempts[currentLevel] <= 6) {
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
            inputField.setEnabled(false); 
            guessButton.setEnabled(false); 
            playSound("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\lose.wav");

           
            JFrame dialogFrame = new JFrame("Game Over");
            dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dialogFrame.setSize(400, 200);
            dialogFrame.setLocationRelativeTo(null); 

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel messageLabel = new JLabel("Game Over! Your final score is " + score);
            messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(messageLabel, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
                dialogFrame.dispose();
              
            });
            panel.add(closeButton, BorderLayout.SOUTH);

            dialogFrame.setContentPane(panel);
            dialogFrame.setVisible(true);
        }
    }

    public void endGame() {
        playSound("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\win.wav"); 

       
        JFrame dialogFrame = new JFrame("Game Over");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(400, 200);
        dialogFrame.setLocationRelativeTo(null); 

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

    private void playSound(String soundFilePath) {
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
    	
    	 
    	     
    	        if (LoginPage.currentUser == null) {
    	            JOptionPane.showMessageDialog(null, "Please login first.");
    	            return;
    	        }

        SwingUtilities.invokeLater(() -> new EasyList("username"));
    }
}