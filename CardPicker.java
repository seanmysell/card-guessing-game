/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame2;

import static cardgame2.CardGenerator.allCards;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author minimarcell
 */
public class CardPicker 
{
    private int score = 0;
    Scanner in = new Scanner(System.in); 
    int numCards;
    int numGuesses;
    ArrayList<String> guesses = new ArrayList<String>();
    String[] theCards;
    
   
    /**
     * Gets a user input for the number of cards they can guess
     */
    public void setNumCards()
    {
        boolean correctInput = false;
               
        do 
        {
            System.out.print("Choose number of cards (between 1 and 52): ");
                      
            try 
            {
                numCards = in.nextInt();
                correctInput = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Oops! Please enter only integers between 1 and 52");
                in.nextLine();
            }
            if (correctInput && (numCards < 1 || numCards > 52))
            {
                System.out.println("Oops! Please enter only integers between 1 and 52");
                
                correctInput = false;
            }
        }
        while (!correctInput);
        in.nextLine();
    }
    
    
    public int getNumCards()
    {
        return numCards;
    }
    
    /**
     * gets user to input the number of guesses they want
     */
    public void setNumGuesses()
    {
        boolean correctInput = false;
               
        do 
        {
            System.out.print("Choose number of guesses: ");
                      
            try 
            {
                numGuesses = in.nextInt();
                correctInput = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Oops! Please enter only positive integers.");
                in.nextLine();
            }
            if (correctInput && (numGuesses < 1))
            {
                System.out.println("Oops! Please enter only positive integers.");
                correctInput = false;
            }
        }
        while (!correctInput);
        
    }
    
    public int getNumGuesses()
    {
        return numGuesses;
    }
    
    /**
     * begins the loop of guessing cards out of the generated cards
     * @param cards generated cards array
     */
    public void guessCards(String[] cards)
    {
        //CardGenerator cG = new CardGenerator();
        //String[] cards = cG.getCards(numGuesses);
        int count = 0;
        theCards = cards;
        
        in.nextLine(); //acounts for skipping line bug
        while (count < numGuesses) //for morning: need to account for duplicate answers, and also add score at end.
        {
            boolean outcome = false;
            System.out.print("Pick a card!: ");
            String choice = in.nextLine();
            boolean isACard = false;
            boolean dupGuess = false;
            
            for (String c : allCards) //checks whether input is a card
            {
                if (choice.toLowerCase().equals(c.toLowerCase()))
                {
                    isACard = true;
                }
            }
            if (!isACard) //breaks current iteration of loop if not card
            {
                System.out.println("Not a card/Wrong Syntax! Try Again.");
                continue;
            }
            
            for (String g : guesses) //checks and sets whether duplicate
            {
                if (choice.toLowerCase().equals(g.toLowerCase()))
                {
                    dupGuess = true;
                }
            }
            
            if (dupGuess)
            {
                System.out.println("Already guessed this card! Try Again.");
                continue;
            }
            
            guesses.add(choice);
            
            
            for (String card : cards)  //checks whether choice is one of cards
            {
               if (choice.toLowerCase().equals(card.toLowerCase())) 
               {
                   outcome = true;
               }
            }
            
            if (outcome)
            {
                score++;
                System.out.println("Correct!");
            }
            else
            {
                System.out.println("False!");
            }
            count++; //takes away one guess
        }
        
        for (int i = 0; i < guesses.size(); i++) //resets the guesses ArrayList
        {
            guesses.remove(i);
        }
    }
    
    public void getScore()
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Score " + score + "/" + numGuesses + "\n");
        System.out.println("The cards:");
        for (String c : theCards)
        {
            System.out.println("-   " + c);
        }
        System.out.println();
    }
}
