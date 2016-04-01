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

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

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

    //Random object for generating the numbers in the game.
    private Random rand;

    //maximum number that can be generated for the buttons (inclusive).
    private final int MAX_NUMBER = 10;

    /**
     * When created, this activity assigns the buttons new numbers and resets
     * the players score.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //assign the two buttons to variables.
        Button numButton1 = (Button) findViewById(R.id.numButton1);
        Button numButton2 = (Button) findViewById(R.id.numButton2);

        //create Random object for this game.
        rand = new Random();

        //generate numbers for the buttons and assign their text to those numbers.
        generateNumbers(numButton1, numButton2);



    }

    /**
     * Generates two numbers between 1 and 10 (inclusive) and assigns each button's
     * text to be one of the generated numbers.
     *
     * @param b1 One of two buttons to have a new number generated for.
     * @param b2 One of two buttons to have a new number generated for.
     */
    private void generateNumbers(Button b1, Button b2)
    {
        //randomly generate numbers between 1 and MAX_NUMBER (default 10).
        int num1 = rand.nextInt(MAX_NUMBER) + 1;
        int num2 = rand.nextInt(MAX_NUMBER) + 1;

        //if the numbers are the same, try again until they are different.
        while (num1 == num2)
        {
            num1 = rand.nextInt(MAX_NUMBER) + 1;
        }

        //assign each button a number
        b1.setText("" + num1);
        b2.setText("" + num2);
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
