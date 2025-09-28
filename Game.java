import java.util.Random;
import java.util.ArrayList;


class Game
{
    // Member variables
    private int categoryNum;
    private int wordNum;
    private Random rand;
    private boolean letterInWord;
    public ArrayList<Integer> currGuessIndicies;
    public int badGuesses;
    
    // 2D Array of all words stored in the game
    private static String[][] words = 
    {
        {
            // Animals
            "Dog", "Cat", "Cow", "Parrot", "Turtle", "Bunny", "Jellyfish", "Squirrel", "Horse", "Koala",
            "Panda", "Jiraffe", "Spider", "Macaw", "Camel", "Firefly", "Pig", "Ostrich", "Lion", "Jaguar"
        },
        {
            // MSU
            "Maverick", "Hardin", "Bolin", "Dillard", "Legacy", "Killingsworth", "Trigg", "Leadership", "Community", "Sunwatcher",
            "Sundance", "GoMustangs", "Moffett", "Maroon", "Gold", "Centennial", "Friendship", "McCoy", "Brick", "Homecoming"
        },
        {
            // Careers
            "Policeman", "Fireman", "Doctor", "Nurse", "Teacher", "Athlete", "Architect", "Actor", "Programmer", "Politician",
            "Judge", "Chef", "Lawyer", "Veterinarian", "Singer", "Engineer", "Scientist", "Journalist", "Accountant", "Magician"
        },
        {
            // Computer_Science
            "Binary", "CPU", "Byte", "Bit", "Software", "Hardware", "Assembly", "Class", "Encapsulation", "Polymorphism",
            "Inheritance", "OS", "Bug", "Database", "Boolean", "String", "Algorithm", "Java", "Python", "Graphics"
        },
        {
            // Sports
            "Soccer", "Football", "Basketball", "Tennis", "Golf", "Padel", "Softball", "Volleyball", "Baseball", "Bowling",
            "Running", "Boxing", "Gymnastics", "Hockey", "Skating", "Swimming", "Taekwondo", "Wrestling", "ESports", "Fencing"
        }
    };

    // Default constructor
    public Game()
    {
        this.categoryNum = 0;
        this.wordNum = 0;
        this.rand = new Random();
        this.letterInWord = false;
        this.currGuessIndicies = new ArrayList<Integer>();
        this.badGuesses = 0; 

    }

    
    // Abstracted function for simple initialization
    public void init()
    {
        generateCategory();
        generateWord(this.categoryNum);
    }

    public boolean inWord(char guess)
    {
        this.currGuessIndicies.clear();
        this.letterInWord = false;

        for(int i = 0; i < words[this.categoryNum][this.wordNum].length(); i++)
        {
            int commonLetter = words[this.categoryNum][this.wordNum].charAt(i);
            // ASCII Conversion so common letter is uppercase
            if(commonLetter > 96)
                commonLetter -= 32;

            // If the guessed character is in the word, keep track of the index where it exists
            if(guess == commonLetter)
            {
                this.currGuessIndicies.add(i);
                this.letterInWord = true;
            }
        }

        // Return bool value
        return letterInWord;   
    }

    // Random category number from 0-4
    private void generateCategory()
    {
        this.categoryNum = this.rand.nextInt(5);
    }

    // Random word index from 0-20
    private void generateWord(int cat)
    {
        this.wordNum = this.rand.nextInt(20);
    }

    //method to increment the # of bad guesses
    public void incBadGuesses()
    {
        // increase bad guesses by 1
        this.badGuesses++;
    }

    //method to get the current # of bad guesses
    public int getNumBadGuesses()
    {
        return this.badGuesses;
    }

    public String getCategory()
    {
        // This switch statement was adapted from an AI (ChatGPT) generated version
        // It stores the names of each category respective to their index
        switch (this.categoryNum) 
        {
            case 0:
                return "Animals";
            case 1:
                return "MSU";
            case 2:
                return "Careers";
            case 3:
                return "Computer_Science";
            case 4:
                return "Sports";
            default:
                return "Error: categoryNum out of bounds";
        }
    }

    // Accesses the 2D array at the random indicies returning a string.
    public String getWord()
    {
        return words[this.categoryNum][this.wordNum];
    }

    public boolean isWordComplete(String guess) 
    {
        String word = getWord();
        //converts both words to Uppercase ltters
        String wordU = word.toUpperCase();
        String guessU= guess.toUpperCase();

        // Checks if lengths are different, meaning the word is missing or needs more letters
        if (guessU.length() != wordU.length()) 
        {
            return false;
        }

        for (int i = 0; i < wordU.length(); i++) 
        {
            if (guessU.charAt(i) != wordU.charAt(i)) //charAt() is used to access a char in an index
            {
                return false; // find the letters that don't match
            }
        }

        return true; // all letters matched
    }


//Used ChatGPT to test it out
//      public static void main(String[] args)
//     {
//         Game myGame = new Game();
//         // String word1 = obj.getWord();

//         // String guess = "Parrot";
//         // String guess1 = "Dog";

//         // System.out.print(obj.isWordComplete(guess));
//         // System.out.print(obj.isWordComplete(guess1));
    

//         //ChatGPT to test 
//        myGame.init();

//     System.out.println("Category: " + myGame.getCategory());
//     String word = myGame.getWord();
//     System.out.println("Word to guess has " + word.length() + " letters.");

//     // Example letters to test
//     char[] testGuesses = {'A', 'E', 'I', 'O', 'U', 'Z'};

//     for (char guess : testGuesses) {
//         System.out.println("\nGuessing letter: " + guess);

//         // Check if the letter is in the word
//         if (myGame.inWord(Character.toUpperCase(guess))) {
//             System.out.println("Letter is in the word!");
//         } else {
//             System.out.println("Letter is NOT in the word.");
//             myGame.incBadGuesses(); // increment bad guesses only for wrong letters
//         }

//         System.out.println("Current bad guesses: " + myGame.getNumBadGuesses());
//     }

//     // Test isWordComplete
//     System.out.println("\nTesting isWordComplete with correct guess: " + word);
//     System.out.println("Result: " + myGame.isWordComplete(word));

//     System.out.println("Testing isWordComplete with wrong guess: WRONGWORD");
//     System.out.println("Result: " + myGame.isWordComplete("WRONGWORD"));
//     }
}


    


