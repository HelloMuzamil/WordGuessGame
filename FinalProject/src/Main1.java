import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main1 {

    public static void main(String[] args) {
       
        SplashScreen splash = new SplashScreen();
        splash.showSplashScreen();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        splash.dispose();

      
        new StartUpPage();
       
    }
}

class SplashScreen extends JWindow {
    public SplashScreen() {
       
        setBackground(new Color(0, 0, 0, 0));
        
       
        JPanel content = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        content.setOpaque(false);

      
        ImageIcon imageIcon = new ImageIcon("C:\\\\Users\\\\Muzammil Ali\\\\Desktop\\\\MidTermJava\\\\FinalProject\\\\bin\\\\StartupPagePic.jpeg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        
     
        JLabel imageLabel = new JLabel(scaledImageIcon);

        content.add(imageLabel, BorderLayout.CENTER);
        setContentPane(content);
        pack();

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 600;
        int height = 400;
        setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
    }

    public void showSplashScreen() {
        setVisible(true);
        
    }
}
