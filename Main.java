import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main
{

    public static void main(String[] args)
    {
        ////////////////////////////
        /// Frame Initialization ///
        /// and constants        ///
        ////////////////////////////
        JFrame frame = new JFrame("Project Hangman");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        Font instrFont = new Font("Arial", Font.PLAIN, 20);
        Font bigFont = new Font("Arial", Font.BOLD, 48);

        Dimension wordPanDim = new Dimension(1200, 200);
        Dimension catPanDim = new Dimension(1200, 200);
        Dimension instrDim = new Dimension(300, 300);
        Dimension buttonSize = new Dimension(300, 75);


        ///////////////////////////
        /// Game Initialization ///
        ///////////////////////////
        Game game = new Game();
        game.init();

        Used_Letters usedLetters = new Used_Letters();

        String gameWord = game.getWord();
        System.out.println(gameWord);
        

        ///////////////////////////////
        /// Interior Panel Creation ///
        ///////////////////////////////
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

        ///////////////////////
        /// Screen Elements ///
        ///////////////////////
        
        // General instructions text
        JTextArea instructions = new JTextArea
        (
            "Welcome to Project Hangman, created by Rykir Evans and Victoria Heredia. " +
            "The goal of this game is to guess a word based on the category and number" +
            " of possible spaces, one letter at a time. You may request a hint which "  +
            "will reveal a letter, change cateory, or start a new game."
        );
        // Image display
        ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-0.png");
        Image img = hangStand.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        hangStand = new ImageIcon(img);
        JLabel imgLabel = new JLabel(hangStand);
        
        //Test
        // usedLetters.addLetter('a');
        // Section for the used letters
        // MIGHT NEED TO CONVERT TO JTextArea
        JLabel usedLettLabel = new JLabel(usedLetters.returnLetters());
        usedLettLabel.setFont(buttonFont);

        // The methods for this TextArea were learned from AI (ChatGPT)
        // Instructions text block tweaking
        instructions.setFont(instrFont);
        instructions.setLineWrap(true);             // Enables text wrapping
        instructions.setWrapStyleWord(true);        // Wrap on word boundaries (not mid-word)
        instructions.setEditable(false);            // Make it read-only like a label
        instructions.setOpaque(false);              
        instructions.setFocusable(false);           
        instructions.setBorder(null);               
        instructions.setPreferredSize(instrDim);

        // Top div assembly
        topPanel.add(instructions, BorderLayout.WEST);
        topPanel.add(imgLabel, BorderLayout.CENTER);
        topPanel.add(usedLettLabel, BorderLayout.EAST);

        // Word placeholder label
        JLabel wordLabel = new JLabel("");
        wordLabel.setFont(bigFont);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordPanel.setPreferredSize(wordPanDim);

        // Category display label
        JLabel catLabel = new JLabel("");
        catLabel.setFont(buttonFont);
        catLabel.setHorizontalAlignment(SwingConstants.CENTER);
        catPanel.setPreferredSize(catPanDim);

        // Button declarations
        JButton hintButton = new JButton("Hint");
        JButton chngCatButton = new JButton("Change Category");
        JButton newGameButton = new JButton("New Game");

        // Button tweaking
        hintButton.setFont(buttonFont);
        chngCatButton.setFont(buttonFont);
        newGameButton.setFont(buttonFont);

        hintButton.setPreferredSize(buttonSize);
        chngCatButton.setPreferredSize(buttonSize);
        newGameButton.setPreferredSize(buttonSize);

        // Start placeholder spots for word
        StringBuilder wordSpaces = new StringBuilder("");
        for(int i = 0; i < gameWord.length() * 2; i++)
        {
            if(i % 2 == 0)
                wordSpaces.append('_');
            else
                wordSpaces.append(' ');
        }

        // Word Panel:
        wordLabel.setText(wordSpaces.toString());
        wordPanel.add(wordLabel, BorderLayout.CENTER);

        // Category Panel:
        catLabel.setText("Category: " + game.getCategory());
        catPanel.add(catLabel, BorderLayout.NORTH);
        

        // Bottom section
        bottomPanel.add(hintButton);
        bottomPanel.add(chngCatButton);
        bottomPanel.add(newGameButton);
        

        // Main conglomeration of all panels
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Actual display
        frame.setContentPane(mainPanel);

                frame.addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent e) 
            {
                char key = e.getKeyChar();
                if(key > 96)
                {key -= 32;}
                if(Character.isLetter(key) && !usedLetters.isUsed(key))
                {
                    usedLetters.addLetter(key);
                    if(game.inWord(key))
                    {
                        for(int i = 0; i < game.currGuessIndicies.size(); i++)
                        {
                            wordSpaces.setCharAt(game.currGuessIndicies.get(i) * 2, key);    
                        }
                        wordLabel.setText(wordSpaces.toString());
                        

                    }
                    
                }
                else
                System.out.println(usedLetters.returnLetters());
            }
        });

        frame.setVisible(true);

        frame.setFocusable(true);
        frame.requestFocusInWindow();


        
        /////////////////
        /// Game Loop ///
        /////////////////
        // Timer timer = new Timer(16, 
        // new ActionListener() 
        //     {
        //         public void actionPerformed(ActionEvent e) 
        //         {
        //             // update();         // Step 1: update game state
                      
        //         }
        //     });

        // timer.start(); // Start the loop



    }
}