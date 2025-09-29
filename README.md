# Project-Hangman

## Authored by Rykir Evans & Victoria Heredia
## CMPS 4143 Java Program 2

### Instructions

**Setup**
- Make sure you install the general java package by using apt: 
     - `sudo apt install openjdk-17-jdk`(for Linux/WSL)
- Compile the program using the javac compiler
```
javac Game.java
javac Used_Letters.java
javac Main.java
```

- Run the compiled program: `java Main`

**Gameplay**
- The game automatically generates a word and category. Minor instructions will already be displayed.
- To interact with the game, you will need to have a keyboard and mouse. 
- You may enter a "guess" one letter at a time by typing a letter.
- You may click the `Hint` button to receive a hint, which will reveal the left-most letter and all copies of it as it appears throughout the word.
- The instructions are toggleable by clicking the `Toggle Instructions` button, which will hide the block of text.
- the `New Game` button will reset the game and generate a new word and category. 
- Letters that have already been guessed will appear in the top-right corner.
- Incorrect guesses will draw a part of the hangman figure, up to 15 incorrect guesses before "Game Over".
- Once all letters have been correctly guessed, the round will end, and the user will be prompted to click the `New Game` button if they attempt to enter another letter.