import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Project Hangman");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new GridBagLayout());
        // frame.getContentPane().setBackground(Color.white);

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Font instrFont = new Font("Arial", Font.PLAIN, 20);
        Font bigFont = new Font("Arial", Font.BOLD, 48);

        Dimension wordPanDim = new Dimension(1200, 200);
        Dimension catPanDim = new Dimension(1200, 200);
        Dimension instrDim = new Dimension(300, 300);

        

        


        // JTextArea instructions = new JTextArea("Blah blah blah");

        

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Main Divs
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel();

        // Sub Divs for CenterPanel
        JPanel wordPanel = new JPanel(new BorderLayout());
        JPanel catPanel = new JPanel(new BorderLayout());

        centerPanel.add(wordPanel, BorderLayout.CENTER);
        centerPanel.add(catPanel, BorderLayout.SOUTH);

      
        
        JTextArea instructions = new JTextArea
        (
            "Welcome to Project Hangman, created by Rykir Evans and Victoria Heredia. " +
            "The goal of this game is to guess a word based on the category and number" +
            " of possible spaces, one letter at a time. You may request a hint which "  +
            "will reveal a letter, change cateory, or start a new game."
        );

        // The methods for this TextArea were learned from AI (ChatGPT)
        instructions.setFont(instrFont);
        instructions.setLineWrap(true);             // Enables wrapping
        instructions.setWrapStyleWord(true);        // Wrap on word boundaries (not mid-word)
        instructions.setEditable(false);            // Make it read-only like a label
        instructions.setOpaque(false);              
        instructions.setFocusable(false);           
        instructions.setBorder(null);               
        instructions.setPreferredSize(instrDim);

        ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-0.png");
        Image img = hangStand.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        hangStand = new ImageIcon(img);
        JLabel imgLabel = new JLabel(hangStand);
        

        topPanel.add(instructions, BorderLayout.WEST);
        topPanel.add(imgLabel, BorderLayout.CENTER);
        topPanel.add(new JLabel("Test 12345"), BorderLayout.EAST);

        

        

        // Center section

        // Word Panel

        JLabel wordLabel = new JLabel("_ _ _ _ _ _ _");
        wordLabel.setFont(bigFont);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        wordPanel.setPreferredSize(wordPanDim);
        wordPanel.add(wordLabel, BorderLayout.CENTER);
        

        // Category Panel:
        JLabel catLabel = new JLabel("Category: X");
        catLabel.setFont(buttonFont);
        catLabel.setHorizontalAlignment(SwingConstants.CENTER);

        catPanel.setPreferredSize(catPanDim);
        catPanel.add(catLabel, BorderLayout.NORTH);
        

        // Bottom section

        JButton hintButton = new JButton("Hint");
        JButton chngCatButton = new JButton("Change Category");
        JButton newGameButton = new JButton("New Game");
        Dimension buttonSize = new Dimension(300, 75);

        hintButton.setFont(buttonFont);
        chngCatButton.setFont(buttonFont);
        newGameButton.setFont(buttonFont);

        

        hintButton.setPreferredSize(buttonSize);
        chngCatButton.setPreferredSize(buttonSize);
        newGameButton.setPreferredSize(buttonSize);

        bottomPanel.add(hintButton);
        bottomPanel.add(chngCatButton);
        bottomPanel.add(newGameButton);
        

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        
        frame.setVisible(true);
    }
}