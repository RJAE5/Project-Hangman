import java.util.Random;


class Game
{
    private int categoryNum;
    private int wordNum;
    private Random rand;


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

    public Game()
    {
        this.categoryNum = 0;
        this.wordNum = 0;
        this.rand = new Random();

    }

    public void init()
    {
        generateCategory();
        generateWord(this.categoryNum);
    }

    private void generateCategory()
    {
        this.categoryNum = this.rand.nextInt(5);
    }
    private void generateWord(int cat)
    {
        this.wordNum = this.rand.nextInt(20);
    }

    public String getCategory()
    {
        // This switch statement was adapted from an AI (ChatGPT) generated version
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

    public String getWord()
    {
        return words[this.categoryNum][this.wordNum];
    }

}