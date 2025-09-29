/*
* Used_Letters Class
*
* Description:
*      This class manages the letters that have been guessed
*      in the Hangman game. It tracks used letters, prevents
*      duplicates, and allows checking and clearing of stored letters.
*
* Public Methods:
*            - Used_Letters()
*  void      - addLetter(char letter)
*  boolean   - isUsed(char letter)
*  String    - returnLetters()
*  void      - clear()
*
* Private Methods:
*      None
*
* Usage:
*
*       - Create an instance of Used_Letters to track letters guessed
*         by a player. Use addLetter() to add a guessed letter, isUsed()
*         to check if a letter has already been guessed, returnLetters()
*         to get a string of all guessed letters, and clear() to reset.
*/

import java.util.ArrayList;

class Used_Letters
{
    private ArrayList<Character> usedL;

/*
    * Public : Used_Letters
    *
    * Description:
    *      Default constructor. Initializes the ArrayList to store letters.
    *
    * Params:
    *     None
    *
    * Returns:
    *     None
*/
    public Used_Letters()
    {
        usedL = new ArrayList<Character>();
    }   

/*
    * Public : addLetter
    *
    * Description:
    *      Adds a letter to the list of used letters if it has not already
    *      been guessed.
    *
    * Params:
    *     char letter - The letter to add
    *
    * Returns:
    *     None
*/
    public void addLetter (char letter)
    {
        boolean alreadyUsed = false; //assume letter has not been used yet
        for (int i =0; i <usedL.size(); i++) //loops through every letter
        {
            char temp = usedL.get(i);

            if(temp == letter) //if the letter stored in temp is = to the letter passed in
            {
                alreadyUsed = true; //then that letter has been used 
            }
        }

        if (!alreadyUsed)
        {
            usedL.add(letter); //Add letter to the array list because it's been used
        }
            
    }

/*
    * Public : isUsed
    *
    * Description:
    *      Checks if a letter has already been guessed.
    *
    * Params:
    *     char letter - The letter to check
    *
    * Returns:
    *     boolean - true if the letter has been guessed, false otherwise
*/
    public boolean isUsed (char letter)
    {
        boolean found = false; //assume we have not found the letter yet

        for (int i = 0; i < usedL.size(); i++) 
        {
            char temp = usedL.get(i); 
            if (temp == letter) //if any letter stored equals to the input letter
                found = true; //then we found the letter and has been used
        }

        if (found == true) 
            return true;
        else 
            return false;
    }

/*
    * Public : returnLetters
    *
    * Description:
    *      Returns a string of all letters that have been guessed
    *      separated by spaces.
    *
    * Params:
    *     None
    *
    * Returns:
    *     String - All guessed letters as a string
*/
    public String returnLetters()
    {
        String letters = "";
        for (int i = 0; i < usedL.size(); i++) 
        {
            letters = letters +  usedL.get(i) + " "; // add each letter to the empty string
        }

        return letters;
    }

/*
    * Public : clear
    *
    * Description:
    *      Clears the list of used letters to start fresh.
    *
    * Params:
    *     None
    *
    * Returns:
    *     None
*/
    public void clear()
    {
        this.usedL.clear();
    }


    ////////
    //Test//
    ////////
    
    // public static void main(String[] args)
    // {
    //     Used_Letters whatever = new Used_Letters();
    //     whatever.addLetter('a');
    //     whatever.addLetter('b');
    //     whatever.addLetter('c');

    //     System.out.println("Searching for 'a'");

    //     System.out.println(whatever.isUsed('a'));

    //     System.out.println("Searching for 'd'");

    //     System.out.println(whatever.isUsed('d'));
    //     System.out.println(whatever.returnLetters());



    // }
}