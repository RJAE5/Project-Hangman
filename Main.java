/*****************************************************************************\
*
*  Author:           Rykir Evans and Victoria Heredia
*  Email:            rjevans0408@my.msutexas.edu | vdheredia1128@my.msutexas.edu
*  Title:            Program 2 - Hangman Game
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
*            - main(String[] args)
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
                wordSpaces.append('_');
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
        JTextArea instructions = new JTextArea
        (instrText);

        // Initial variables for image, to be later overrode
        ImageIcon initHangStand = new ImageIcon("./Media/pixil-frame-0.png");
        Image initImg = initHangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        initHangStand = new ImageIcon(initImg);
        
        JLabel imgLabel = new JLabel(initHangStand);
        imgLabel.setIcon(initHangStand);
        
        
        //Test
        // usedLetters.addLetter('a');
        // Section for the used letters
        // MIGHT NEED TO CONVERT TO JTextArea
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

        createWordStrings(gameWord, wordSpaces, guessedWord);

        // Word Panel:
        wordLabel.setText(wordSpaces.toString());
        wordPanel.add(wordLabel, BorderLayout.CENTER);

        // Category Panel:
        catLabel.setText("Category: " + game.getCategory());
        catPanel.add(catLabel, BorderLayout.NORTH);
        

        // Bottom section
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

        // if(!roundOver)
        // {
            frame.addKeyListener(new KeyAdapter() 
            {
                // Listening for key presses
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
                                    // Set the stringBuilder with underscores with the guessed character
                                    wordSpaces.setCharAt(game.currGuessIndicies.get(i) * 2, key);    

                                    /////////////////
                                    // THIS WILL BREAK THE VERIFICATION  METHOD THAT VICKY WROTE because it's making a string all capitals
                                    ////////////////
                                    guessedWord.setCharAt(game.currGuessIndicies.get(i), key);
                                    System.out.println(guessedWord.toString());
                                    System.out.println("Is word complete? " + game.isWordComplete(guessedWord.toString()));

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
                                game.incBadGuesses();
                                
                                // Image display
                                ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-" + game.getNumBadGuesses() + ".png");
                                Image img = hangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                                hangStand = new ImageIcon(img);
                                imgLabel.setIcon(hangStand);

                                if(game.getNumBadGuesses() >= 14)
                                {
                                    game.isRoundOver = true;
                                    catLabel.setText("ROUND FAILED");
                                    catLabel.setForeground(Color.RED);;
                                }
                            }
                        }
                        
                    }
                    else
                    {
                        catLabel.setText("Press 'New Game' to begin again");
                        catLabel.setForeground(Color.BLACK);
                    }
                }
            });

            hintButton.addActionListener(e -> 
            {
                if(!game.isRoundOver)
                {
                    String currWord = game.getWord().toUpperCase();
                    for(int i = 0; i < currWord.length(); i++)
                    {
                        if(currWord.charAt(i) != guessedWord.charAt(i))
                        {

                            guessedWord.setCharAt(i, currWord.charAt(i));
                            wordSpaces.setCharAt(i * 2, currWord.charAt(i));
                            usedLetters.addLetter(currWord.charAt(i));
                            
                            for(int j = i + 1; j < currWord.length(); j++)
                            {
                                if(currWord.charAt(j) == currWord.charAt(i))
                                {
                                    guessedWord.setCharAt(j, currWord.charAt(i));
                                    wordSpaces.setCharAt(j * 2, currWord.charAt(i));
                                    
                                }
                            }

                            usedLettTextArea.setText(usedLetters.returnLetters());
                            wordLabel.setText(wordSpaces.toString());
                            frame.requestFocusInWindow();

                            if(game.isWordComplete(guessedWord.toString()))
                            {
                                catLabel.setText("ROUND COMPLETE");
                                catLabel.setForeground(Color.GREEN);;
                                game.isRoundOver = true;

                            }
                            break;
                        }
                    }
                }

            });

            showCredsButton.addActionListener(e -> 
            {
                if(game.instrShown)
                {
                    instructions.setText("");
                    game.instrShown = !game.instrShown;
                }
                else
                {
                    instructions.setText(instrText);
                    game.instrShown = !game.instrShown;
                }
                frame.requestFocusInWindow();
                
            });

            newGameButton.addActionListener(e ->
            {
                game.init();
                game.isRoundOver = false;
                
                wordSpaces.setLength(0);
                guessedWord.setLength(0);
                usedLetters.clear();
                createWordStrings(game.getWord(), wordSpaces, guessedWord);

                wordLabel.setText(wordSpaces.toString());
                catLabel.setForeground(Color.BLACK);
                catLabel.setText("Category: " + game.getCategory());

                usedLettTextArea.setText("");
                frame.requestFocusInWindow(); // Realized this line was necessary  thanks to AI (ChatGPT) because the frame would lose focus once the button is clicked

                ImageIcon hangStand = new ImageIcon("./Media/pixil-frame-" + game.getNumBadGuesses() + ".png");
                Image img = hangStand.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                hangStand = new ImageIcon(img);
                imgLabel.setIcon(hangStand);

            });
        
        frame.setVisible(true);

        frame.setFocusable(true);
        frame.requestFocusInWindow();

    }
}