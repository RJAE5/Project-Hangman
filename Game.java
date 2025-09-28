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

}