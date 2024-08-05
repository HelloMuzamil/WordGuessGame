import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MediumList {
    JFrame frame;
    JLabel hintLabel;
    JTextField inputField;
    JLabel wordLabel;
    JLabel attemptsLabel;
    JLabel resultLabel;
    JLabel scoreLabel;
    JButton guessButton = new JButton("GUESS");
    String[] hints = {
    	    "Expression", "Celestial body (in space)", "Furniture",
    	    "Weather phenomenon", "Natural feature", "Food",
    	    "Art form", "Outdoor space", "Meteorological feature (in the sky)",
    	    "Furniture"
    	};

    String[] wordlist = {"Smile", "Moon", "Chair", "Rain", "River", "Bread", "Music", "Garden", "Cloud", "Table"};
    String[] COMBINATION = {
    	    "e,i,l,m,S", "n,o,o,M", "r,i,h,C,a", "n,i,a,R", "r,v,i,R,e", "d,r,e,a,B", "c,u,i,s,M", "n,a,G,d,e,r", "d,u,l,C,o", "e,l,b,T,a"
    	};

//    String[] COMBINATION = {"Y,N,T,S,X,A", "L,A,O,G,I,T,R,I,T", "R,P,G,O,A,R,M,G,N,I,G", "E,U,D,B,N,G,I,G,N", "P,O,M,C,L,I,C,A,I,T,O,N", "F,F,E,I,I,C,C,N,E,Y", "N,N,I,O,V,A,I,T,O,N", "P,O,M,I,T,I,Z,A,T,N,O,I", "R,E,P,F,O,A,M,R,N,C,E", "C,R,S,U,E,Y,T,I"};
    int currentLevel = 0;
    int score = 0;
    int[] attempts = new int[10];  
    String username;

    public MediumList(String username) {
        this.username = username;
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Muzammil Ali\\Desktop\\MidTermJava\\FinalProject\\bin\\1222.jpg");
        Image backgroundImage = imageIcon.getImage();

        
        
        
        
        
        
        
        if (LoginPage.currentUser == null) {
            
            JOptionPane.showMessageDialog(null, "Please login first.");
           
         
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
        label.setBounds(0, 180, 1400, 40);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150));
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
       
        inputField.addActionListener(e -> checkGuess());
        
        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        attemptsLabel.setForeground(Color.WHITE);
        attemptsLabel.setBounds(760, 350, 200, 30);
        frame.add(attemptsLabel);
        
        hintLabel = new JLabel("", JLabel.CENTER);
        hintLabel.setForeground(Color.WHITE);
        hintLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hintLabel.setBounds(500, 270, 400, 30);
        frame.add(hintLabel);
   
        guessButton.setBounds(500, 450, 150, 50);
        guessButton.setBackground(new Color(165,72,104,255));
        guessButton.setForeground(Color.white);
        guessButton.setFocusPainted(false);
        guessButton.setBorderPainted(false);
        guessButton.setOpaque(true);
        guessButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        guessButton.addActionListener(e -> checkGuess());
        frame.add(guessButton);

        guessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guessButton.setBackground(new Color(20,10,27,255));
                frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                guessButton.setBackground(new Color(165,72,104,255));
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(750, 450, 150, 50);
        backButton.setBackground(new Color(165,72,104,255));
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
                backButton.setBackground(new Color(20,10,27,255));
                frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(165,72,104,255));
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
        scoreLabel.setBackground(new Color(20,10,27,255));
        scoreLabel.setOpaque(true);
        scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        scoreLabel.setBounds(600, 550, 200, 30);
        frame.add(scoreLabel);

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

        if (attempts[currentLevel] <= 5) {
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
          //  playSound("C:\\Users\\DELL\\eclipse-workspace\\GUI2\\src\\G.wav"); 

            
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
    	// playSound("C:\\Users\\DELL\\eclipse-workspace\\FinalPro\\src\\lose.wav"); 

         // Customizing the "Game Over" dialog box
         JFrame dialogFrame = new JFrame("Congratulations");
         dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         dialogFrame.setSize(400, 200);
         dialogFrame.setLocationRelativeTo(null); 

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
   

//    public void playSound(String soundFile) {
//        try {
//            File soundFilePath = new File(soundFile);
//            if (soundFilePath.exists()) {
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFilePath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.start();
//            } else {
//                System.err.println("Sound file not found: " + soundFile);
//            }
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        new MediumList("John Doe");
    }
}
