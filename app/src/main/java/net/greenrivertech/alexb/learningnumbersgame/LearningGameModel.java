/*
    Author: Alex Ball
    Date: 04/07/2016
    Filename: LearningGameModel.java

    This activity is the main activity in the project. The player will select one
    of two buttons with numbers on them. If the bigger number is selected,
    the player's score will increase and they will get a toast message telling
    them that they were correct.
 */

package net.greenrivertech.alexb.learningnumbersgame;

import java.util.Random;

/**
 * This model controls the game logic for the Learning Numbers Game.
 *
 * @author Alex ball
 */
public class LearningGameModel {
    //Random object for generating the numbers in the game.
    private Random rand;

    //the left and right number for the game
    private int leftNumber;
    private int rightNumber;

    //number of times the user chose the bigger number.
    private int userScore;

    //number of times the  user has played
    private int userTimesPlayed;

    //constants representing which number was chosen
    public final static int LEFT_CHOICE = 0;
    public final static int RIGHT_CHOICE = 1;

    //the max number that can be assigned to leftNumber or rightNumber
    public final static int MAX_NUMBER = 10;

    /**
     * Constructor that builds a new LearningGameModel.
     */
    public LearningGameModel() {
        //assign user's score and times played to 0.
        userScore = 0;
        userTimesPlayed = 0;

        //create Random object for this game.
        rand = new Random();

        //generate numbers for the buttons and assign their text to those numbers.
        generateNumbers();
    }

    /**
     * Checks if the user was correct by comparing the numbers in each button.
     * The first parameter is the button that the user clicked. If that button's
     * number is bigger, then the user's score will increase.
     *
     * @param choice Which number was chosen.
     */
    public boolean checkAnswer(int choice) {
        //increment the number of times the user has played.
        userTimesPlayed++;

        //if the user's choice is the bigger number, increase their score and return true.
        if ((choice == LEFT_CHOICE && leftNumber > rightNumber) ||
                (choice == RIGHT_CHOICE && rightNumber > leftNumber)){
            userScore++;
            return true;
        }

        //otherwise, return false.
        return false;
    }


    /**
     * Generates two numbers between 1 and 10 (inclusive) and assigns each number in
     * this model to one of them.
     */
    public void generateNumbers() {

        //randomly generate numbers between 1 and maxNumber (default 10).
        int num1 = rand.nextInt(MAX_NUMBER) + 1;
        int num2 = rand.nextInt(MAX_NUMBER) + 1;

        //if the numbers are the same, try again until they are different.
        while (num1 == num2) {
            num1 = rand.nextInt(MAX_NUMBER) + 1;
        }

        leftNumber = num1;
        rightNumber = num2;
    }

    /**
     * returns userScore.
     *
     * @return The number of times the user won the game.
     */
    public int getUserScore() {
        return userScore;
    }

    /**
     * returns userTimesPlayed.
     *
     * @return The number of times the game has been played.
     */
    public int getUserTimesPlayed() {
        return userTimesPlayed;
    }

    /**
     * returns leftNumber.
     *
     * @return The leftNumber.
     */
    public int getLeftnumber() {
        return leftNumber;
    }

    /**
     * returns rightNumber.
     *
     * @return The rightNumber.
     */
    public int getRightNumber() {
        return rightNumber;
    }
}