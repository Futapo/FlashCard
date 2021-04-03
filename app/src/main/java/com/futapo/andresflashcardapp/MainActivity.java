package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Flashcard Database
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.Flash_Question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.Flash_Answer)).setText(allFlashcards.get(0).getAnswer());
        }


        //Variables
        TextView Answer = findViewById(R.id.Flash_Answer);
        TextView Question = findViewById(R.id.Flash_Question);
        ImageView Add = findViewById(R.id.add_Flash);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        View answerSideView = findViewById(R.id.Flash_Answer);
        View questionSideView = findViewById(R.id.Flash_Question);
        //making question Invisible and Answer visible on touch

        Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the center for the clipping circle
                int cx = questionSideView.getWidth() / 2;
                int cy = questionSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(questionSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();


            }
        });
        Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                answerSideView.setVisibility(View.INVISIBLE);
                questionSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
            }
        });

        //Add Button
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, added_flashcard.class);
                intent.putExtra("flashKeyQ", "hi");
                intent.putExtra("flashKeyA", "bye");
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                MainActivity.this.startActivityForResult(intent, 100);


            }
        });
        //next button

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // don't try to go to next card if you have no cards to begin with
                if (allFlashcards.size() == 0)
                    return;
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(findViewById(R.id.Flash_Question),
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.Flash_Question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.Flash_Answer)).setText(flashcard.getAnswer());

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                (findViewById(R.id.Flash_Question)).startAnimation(leftOutAnim);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts



                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        (findViewById(R.id.Flash_Question)).startAnimation(rightInAnim);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });


        //Trash button

        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlashcards = flashcardDatabase.getAllCards();


                if (allFlashcards.size() >= currentCardDisplayedIndex) {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.Flash_Question)).getText().toString());


                    currentCardDisplayedIndex--;


                }


            }


        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String new_Question = data.getExtras().getString("new_Question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String new_Answer = data.getExtras().getString("new_Answer");

            ((TextView) findViewById(R.id.Flash_Question)).setText(new_Question);
            ((TextView) findViewById(R.id.Flash_Answer)).setText(new_Answer);

            flashcardDatabase.insertCard(new Flashcard(new_Question, new_Answer));
        }
        allFlashcards = flashcardDatabase.getAllCards();


    }

}

