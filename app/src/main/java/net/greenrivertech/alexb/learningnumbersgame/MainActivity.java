/*
    Author: Alex Ball
    Date: 04/01/2016
    Filename: MainActivity.java

    This activity is the main activity in the project. The player will select one
    of two buttons with numbers on them. If the bigger number is selected,
    the player's score will increase and they will get a toast message telling
    them that they were correct.
 */
package net.greenrivertech.alexb.learningnumbersgame;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * This activity is the main activity in the project. The player will select one
 * of two buttons with numbers on them. If the bigger number is selected,
 * the player's score will increase and they will get a toast message telling
 * them that they were correct.
 *
 * @author Alex ball
 */
public class MainActivity extends AppCompatActivity {

    //resources object to access saved constants (such as the integer value
    //for the maxNumber that can be generated in this game).
    private Resources res;

    //Random object for generating the numbers in the game.
    private Random rand;

    //number of times the user chose the bigger number.
    private int userScore;

    //number of times the  user has played
    private int userTimesPlayed;

    /**
     * When created, this activity assigns the buttons new numbers and resets
     * the players score.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //assign resources field
        res = getResources();

        //assign user's score and times played to 0.
        userScore = 0;
        userTimesPlayed = 0;

        //assign the score and timesPlayed TextViews to constants
        final TextView scoreView = (TextView) findViewById(R.id.userScore);
        final TextView timesPlayedView = (TextView) findViewById(R.id.userTimesPlayed);

        //set score and timesPlayed textView
        updateScore(scoreView, timesPlayedView);

        //assign the two buttons to constants.
        final Button numButton1 = (Button) findViewById(R.id.numButton1);
        final Button numButton2 = (Button) findViewById(R.id.numButton2);

        //create Random object for this game.
        rand = new Random();

        //generate numbers for the buttons and assign their text to those numbers.
        generateNumbers(numButton1, numButton2);

        //check to make sure each button is not null, then
        //add event listeners. When a button is clicked, the number in its text field
        //is compared to the number in the other button's text field.
        if (numButton1 != null) {
            numButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(numButton1, numButton2);
                    updateScore(scoreView, timesPlayedView);
                    generateNumbers(numButton1, numButton2);
                }
            });
        }

        if (numButton2 != null) {
            numButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(numButton2, numButton1);
                    updateScore(scoreView, timesPlayedView);
                    generateNumbers(numButton1, numButton2);
                }
            });
        }
    }

    /**
     * Checks if the user was correct by comparing the numbers in each button.
     * The first parameter is the button that the user clicked. If that button's
     * number is bigger, then the user's score will increase.
     *
     * @param b1 The button clicked by the user.
     * @param b2 The other button.
     */
    private void checkAnswer(Button b1, Button b2) {
        //increment the number of times the user has played.
        userTimesPlayed++;

        //get the numbers from the buttons
        int userNum = Integer.parseInt(b1.getText().toString());
        int otherNum = Integer.parseInt(b2.getText().toString());

        //check if the number the user clicked is bigger than the other number.
        if (userNum > otherNum) {
            //if the button that the user clicked had a bigger number,
            //increase their score and show a "correct" toast.
            userScore++;

            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
        } else {
            //otherwise, show a "wrong" toast.
            Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Updates the score and times played views so the player can see them.
     *
     * @param scoreView       The scoreView to be updated.
     * @param timesPlayedView The timesPlayedView to be updated.
     */
    private void updateScore(TextView scoreView, TextView timesPlayedView) {
        //create the text that will be shown. In this case, the text will look like "Score: 2".
        String userScoreString = String.format("%s %d",
                getString(R.string.score_text), userScore);

        String userTimesPlayedString = String.format("%s %d",
                getString(R.string.times_played_text), userTimesPlayed);

        //update the textViews
        scoreView.setText(userScoreString);
        timesPlayedView.setText(userTimesPlayedString);
    }

    /**
     * Generates two numbers between 1 and 10 (inclusive) and assigns each button's
     * text to be one of the generated numbers.
     *
     * @param b1 One of two buttons to have a new number generated for.
     * @param b2 One of two buttons to have a new number generated for.
     */
    private void generateNumbers(Button b1, Button b2) {
        //get the maximum number that can be generated in the game.
        int maxNumber = res.getInteger(R.integer.max_number);

        //randomly generate numbers between 1 and maxNumber (default 10).
        int num1 = rand.nextInt(maxNumber) + 1;
        int num2 = rand.nextInt(maxNumber) + 1;

        //if the numbers are the same, try again until they are different.
        while (num1 == num2) {
            num1 = rand.nextInt(maxNumber) + 1;
        }

        //assign each button a number
        b1.setText(String.format("%d", num1));
        b2.setText(String.format("%d", num2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
