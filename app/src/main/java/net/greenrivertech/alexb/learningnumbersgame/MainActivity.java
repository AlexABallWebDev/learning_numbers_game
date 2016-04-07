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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity is the main activity in the project. The player will select one
 * of two buttons with numbers on them. If the bigger number is selected,
 * the player's score will increase and they will get a toast message telling
 * them that they were correct.
 *
 * @author Alex ball
 */
public class MainActivity extends AppCompatActivity {

    //model object that keeps track of the game logic
    private final LearningGameModel model = new LearningGameModel();

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

        //assign the score and timesPlayed TextViews to constants
        final TextView scoreView = (TextView) findViewById(R.id.userScore);
        final TextView timesPlayedView = (TextView) findViewById(R.id.userTimesPlayed);

        //assign the two buttons to constants.
        final Button numButton1 = (Button) findViewById(R.id.numButton1);
        final Button numButton2 = (Button) findViewById(R.id.numButton2);

        //set the views
        updateViews(scoreView, timesPlayedView, numButton1, numButton2);

        //check to make sure each button is not null, then
        //add event listeners. When a button is clicked, the number in its text field
        //is compared to the number in the other button's text field.
        if (numButton1 != null) {
            numButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (model.checkAnswer(LearningGameModel.LEFT_CHOICE)) {
                        showToastText(getString(R.string.correct));
                    } else {
                        //otherwise, show a "wrong" toast.
                        showToastText(getString(R.string.wrong));
                    }
                    model.generateNumbers();
                    updateViews(scoreView, timesPlayedView, numButton1, numButton2);
                }
            });
        }

        if (numButton2 != null) {
            numButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //check if the number the user clicked is bigger than the other number.
                    if (model.checkAnswer(LearningGameModel.RIGHT_CHOICE)) {
                        showToastText(getString(R.string.correct));
                    } else {
                        //otherwise, show a "wrong" toast.
                        showToastText(getString(R.string.wrong));
                    }
                    model.generateNumbers();
                    updateViews(scoreView, timesPlayedView, numButton1, numButton2);
                }
            });
        }
    }

    private void showToastText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Updates the score and times played views so the player can see them.
     *
     * @param scoreView       The scoreView to be updated.
     * @param timesPlayedView The timesPlayedView to be updated.
     */
    private void updateViews(TextView scoreView, TextView timesPlayedView,
                             Button numButton1, Button numButton2) {
        //create the text that will be shown. In this case, the text will look like "Score: 2".
        String userScoreString = String.format("%s %d",
                getString(R.string.score_text), model.getUserScore());

        String userTimesPlayedString = String.format("%s %d",
                getString(R.string.times_played_text), model.getUserTimesPlayed());

        //update the textViews
        scoreView.setText(userScoreString);
        timesPlayedView.setText(userTimesPlayedString);

        //update the buttons
        numButton1.setText(String.format("%d", model.getLeftnumber()));
        numButton2.setText(String.format("%d", model.getRightNumber()));
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
