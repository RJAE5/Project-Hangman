import java.util.ArrayList;

class Used_Letters
{
    private ArrayList<Character> usedL;

    public Used_Letters()
    {
        usedL = new ArrayList<Character>();
    }   

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

    public boolean isUsed (char letter)
    {
        boolean found = false; //assume we have not found the letter yet

        for (int i = 0; i < usedL.size(); i++) 
        {
            char temp = usedL.get(i); 
            if (temp == letter) //if any letter stored equals to the input letter
            {
                found = true; //then we found the letter and has been used
            }
        }

        if (found == true) 
        {
            return true;
    
        }
        else 
        {
            return false;
        }

    }
    public String returnLetters()
    {
        String letters = "";
        for (int i = 0; i < usedL.size(); i++) 
        {
            letters = letters +  usedL.get(i) + " "; // add each letter to the empty string
        }

        return letters;
    }

    public void clear()
    {
        this.usedL.clear();
    }
    
    public static void main(String[] args)
    {
        Used_Letters whatever = new Used_Letters();
        whatever.addLetter('a');
        whatever.addLetter('b');
        whatever.addLetter('c');

        System.out.println("Searching for 'a'");

        System.out.println(whatever.isUsed('a'));

        System.out.println("Searching for 'd'");

        System.out.println(whatever.isUsed('d'));
        System.out.println(whatever.returnLetters());



    }
}