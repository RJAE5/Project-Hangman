/*****************************************************************************\
*
*  Author:           Rykir Evans and Victoria Heredia
*  Email:            rjevans0408@my.msutexas.edu | vdheredia1128@my.msutexas.edu
*  Title:            Program 2 - Project Hangman
*  Course:           CMPS 4143 Java and Python
*  Professor:        Dr. Tina Johnson
*  Semester:         Fall 2025
*
*  Description:
*         This class contains the main execution for the Hangman game
*         GUI using Java Swing. It initializes the game state, manages
*         user input for letter guesses, updates the game display, and
*         handles the end-of-round logic.
*         
*  Public Methods:
*  void      - main(String[] args)
*  void      - createWordStrings(String gameWord, StringBuilder wordSpaces, StringBuilder guessedWord)
*
*  Private Methods:
*      None
*
*  Usage:
*         Compile and run this class using a standard Java compiler.
*         The GUI will open, displaying the game category, placeholder
*         spaces for the word, and buttons for hints, instructions, and
*         starting a new game.
* 
*  Files:
*         Main.java
*         Game.java         - Class for tracking game elements
*         Used_Letters.java - Class for tracking used letters
\******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{

/*
    * Public : createWordStrings
    *
    * Description:
    *      Initializes two StringBuilder objects to represent the hidden
    *      word and the displayed word spaces for the Hangman GUI. Inserts
    *      underscores for each letter in the word and spaces for readability.
    *
    * Params:
    *     String gameWord - The word to be guessed
    *     StringBuilder wordSpaces - StringBuilder to store underscores for display
    *     StringBuilder guessedWord - StringBuilder to store the guessed letters
    *
    * Returns:
    *     None
*/
    public static void createWordStrings(String gameWord, StringBuilder wordSpaces, StringBuilder guessedWord)
    {
        for(int i = 0; i < gameWord.length() * 2; i++)
        {
            // Aesthetic for label
            if(i % 2 == 0)
            {
                // Display StringBuilder gets an underscore followed by a space
                // Ex: _ _ _ _ _ 
                wordSpaces.append('_');

                // Since the length is doubled, a space to the main guessedWord tracker
                // is added only every other time.
                guessedWord.append(' ');
            }
            else
                wordSpaces.append(' ');
        }
    }

/*
    * Public : main
    *
    * Description:
    *      Entry point of the Hangman game. Initializes the GUI components,
    *      the game state, and the event listeners for keyboard input and
    *      buttons (Hint, Toggle Instructions, New Game). Updates the game
    *      display in real-time based on user input and tracks used letters.
    *
    * Params:
    *     String[] args - Command line arguments
    *
    * Returns:
    *     None
*/
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
        Font instrFont = new Font("Arial", Font.PLAIN, 18);
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

        String gameWord = game.getWord().toUpperCase();
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
        
        String instrText = "Welcome to Project Hangman!\n" + "\n" +
                           "The goal of this game is to guess a word based on the category and number" +
                           " of possible spaces, one letter at a time. You may request a hint which "  +
                           "will reveal a letter. Click the right button to start a new game." + "\n" +
                           "\nCreated by Rykir Evans and Victoria Heredia.";
        // General instructions text
        JTextArea instructions = new JTextArea(instrText);

        // Initial variables for image, to be later overrode
        ImageIcon initHangStand = new ImageIcon("./Media/pixil-frame-0.png");
        Image initImg = initHangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        initHangStand = new ImageIcon(initImg);
        
        // Image holder
        JLabel imgLabel = new JLabel(initHangStand);
        imgLabel.setIcon(initHangStand);
        
        // Section displaying used letters
        JTextArea usedLettTextArea = new JTextArea();
        usedLettTextArea.setFont(buttonFont);
        usedLettTextArea.setLineWrap(true);
        usedLettTextArea.setWrapStyleWord(true);
        usedLettTextArea.setEditable(false);
        usedLettTextArea.setOpaque(false);
        usedLettTextArea.setFocusable(false);
        usedLettTextArea.setBorder(null);
        usedLettTextArea.setPreferredSize(instrDim);

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
        topPanel.add(usedLettTextArea, BorderLayout.EAST);

        // Placeholder for the main word label
        JLabel wordLabel = new JLabel("");
        wordLabel.setFont(bigFont);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordPanel.setPreferredSize(wordPanDim);

        // Category/Misc Instructions display label
        JLabel catLabel = new JLabel("");
        catLabel.setFont(buttonFont);
        catLabel.setHorizontalAlignment(SwingConstants.CENTER);
        catPanel.setPreferredSize(catPanDim);

        // Button declarations
        JButton hintButton = new JButton("Hint");
        JButton showCredsButton = new JButton("Toggle Instructions");
        JButton newGameButton = new JButton("New Game");

        // Button tweaking
        hintButton.setFont(buttonFont);
        showCredsButton.setFont(buttonFont);
        newGameButton.setFont(buttonFont);

        hintButton.setPreferredSize(buttonSize);
        showCredsButton.setPreferredSize(buttonSize);
        newGameButton.setPreferredSize(buttonSize);

        // Start placeholder spots for word
        StringBuilder wordSpaces = new StringBuilder("");
        StringBuilder guessedWord = new StringBuilder("");

        // Create String and StringBuilder objects keeping track of the word at hand, 
        // how many spaces it will display, and what has been guessed so far
        createWordStrings(gameWord, wordSpaces, guessedWord);

        // Main word display label receiving text and being added to the display panel
        wordLabel.setText(wordSpaces.toString());
        wordPanel.add(wordLabel, BorderLayout.CENTER);

        // Category display label receiving text and being added to the display panel
        catLabel.setText("Category: " + game.getCategory());
        catPanel.add(catLabel, BorderLayout.NORTH);
        

        // Bottom div assembly
        bottomPanel.add(hintButton);
        bottomPanel.add(showCredsButton);
        bottomPanel.add(newGameButton);

        // Main conglomeration of all panels
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Actual display
        frame.setContentPane(mainPanel);

        ///////////////////////
        /// Main Game Logic ///
        ///////////////////////

        // Key Press Listener
        frame.addKeyListener(new KeyAdapter() 
        {
            // Main method listening for key presses
            public void keyPressed(KeyEvent e) 
            {
                if(!game.isRoundOver)
                {
                    // ASCII conversion to work only with uppercase letters
                    char key = e.getKeyChar();
                    if(key > 96)
                    {key -= 32;}

                    // Check that key pressed is a letter, and if it's already used
                    if(Character.isLetter(key) && !usedLetters.isUsed(key))
                    {
                        // Add to usedLetters ArrayList
                        usedLetters.addLetter(key);
                        usedLettTextArea.setText(usedLetters.returnLetters());

                        // If the guess was correct
                        if(game.inWord(key))
                        {
                            // Replace the displayed label with correct spaces
                            for(int i = 0; i < game.currGuessIndicies.size(); i++)
                            {
                                // Set the stringBuilders with underscores with the guessed character
                                wordSpaces.setCharAt(game.currGuessIndicies.get(i) * 2, key);    

                                // Set the stringbuilder keeping track of the guessed word so far
                                guessedWord.setCharAt(game.currGuessIndicies.get(i), key);

                                // Round completion steps
                                if(game.isWordComplete(guessedWord.toString()))
                                {
                                    catLabel.setText("ROUND COMPLETE");
                                    catLabel.setForeground(Color.GREEN);;
                                    game.isRoundOver = true;

                                }
                            }

                            // Change displayed label
                            wordLabel.setText(wordSpaces.toString());
                        }
                        else // The guess is not in the word
                        {
                            // Increment Bad Guess counter
                            game.incBadGuesses(); 
                            
                            // Image display contingent on number of bad guesses
                            ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-" + game.getNumBadGuesses() + ".png");
                            Image img = hangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                            hangStand = new ImageIcon(img);
                            imgLabel.setIcon(hangStand);

                            // Game over conditions
                            if(game.getNumBadGuesses() >= 14)
                            {
                                game.isRoundOver = true;
                                catLabel.setText("ROUND FAILED");
                                catLabel.setForeground(Color.RED);;
                            }
                        }
                    }
                    
                }
                else // Total number of bad guesses exceeded, prompt user to begin a new game
                {
                    catLabel.setText("Press 'New Game' to begin again");
                    catLabel.setForeground(Color.BLACK);
                }
            }
        });

        // Hint Button Action
        hintButton.addActionListener(e -> 
        {
            if(!game.isRoundOver)
            {
                // Get a common word for comparison
                String currWord = game.getWord().toUpperCase();

                // Loop for finding indicies of what letters have been revealed
                for(int i = 0; i < currWord.length(); i++)
                {
                    // Left-most letter that has not been guessed
                    if(currWord.charAt(i) != guessedWord.charAt(i))
                    {
                        // Update all tracking elements to include the revealed letter
                        guessedWord.setCharAt(i, currWord.charAt(i));
                        wordSpaces.setCharAt(i * 2, currWord.charAt(i));
                        usedLetters.addLetter(currWord.charAt(i));
                        
                        // For repeated letters, the rest are revealed
                        for(int j = i + 1; j < currWord.length(); j++)
                        {
                            if(currWord.charAt(j) == currWord.charAt(i))
                            {
                                // Update tracking elements
                                guessedWord.setCharAt(j, currWord.charAt(i));
                                wordSpaces.setCharAt(j * 2, currWord.charAt(i));
                                
                            }
                        }

                        // Update display elements
                        usedLettTextArea.setText(usedLetters.returnLetters());
                        wordLabel.setText(wordSpaces.toString());
                        frame.requestFocusInWindow();

                        // If the revealed letter completes the game, enter roundOver conditions.
                        if(game.isWordComplete(guessedWord.toString()))
                        {
                            catLabel.setText("ROUND COMPLETE");
                            catLabel.setForeground(Color.GREEN);;
                            game.isRoundOver = true;

                        }
                        break; // Break outer for-loop
                    }
                }
            }

        });

        // Toggle instructions button action
        showCredsButton.addActionListener(e -> 
        {
            // If the instructions are already shown, delete them
            if(game.instrShown)
            {
                instructions.setText("");
                game.instrShown = !game.instrShown;
            }
            else // Show the instructions
            {
                instructions.setText(instrText);
                game.instrShown = !game.instrShown;
            }
            frame.requestFocusInWindow();
            
        });

        // New Game Action
        newGameButton.addActionListener(e ->
        {
            // Obtain a new word
            game.init();
            game.isRoundOver = false;
            
            // Reset tracking elements
            wordSpaces.setLength(0);
            guessedWord.setLength(0);
            usedLetters.clear();

            // Setup tracking string and StringBuilders
            createWordStrings(game.getWord(), wordSpaces, guessedWord);


            // Set Display Elements
            wordLabel.setText(wordSpaces.toString());
            catLabel.setForeground(Color.BLACK);
            catLabel.setText("Category: " + game.getCategory());
            usedLettTextArea.setText("");
            frame.requestFocusInWindow(); // Realized this line was necessary  thanks to AI (ChatGPT) because the frame would lose focus once the button is clicked

            // Reset image
            ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-" + game.getNumBadGuesses() + ".png");
            Image img = hangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            hangStand = new ImageIcon(img);
            imgLabel.setIcon(hangStand);

        });
        
        // Main display commands  
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

    }
}