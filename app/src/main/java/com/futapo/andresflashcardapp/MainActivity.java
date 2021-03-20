package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        //making question Invisible and Answer visible on touch
        Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Answer.setVisibility(View.VISIBLE);
                Question.setVisibility(View.INVISIBLE);
            }
        });
        Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Answer.setVisibility(View.INVISIBLE);
                Question.setVisibility(View.VISIBLE);
            }
        });

        //Add Button
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, added_flashcard.class);
                intent.putExtra("flashKeyQ", "hi");
                intent.putExtra("flashKeyA", "bye");
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
            }
        });
        //Trash button

        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlashcards = flashcardDatabase.getAllCards();


             if( allFlashcards.size()>=currentCardDisplayedIndex) {
                 flashcardDatabase.deleteCard(((TextView) findViewById(R.id.Flash_Question)).getText().toString());


                 currentCardDisplayedIndex--;



             }


            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String new_Question = data.getExtras().getString("new_Question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String new_Answer = data.getExtras().getString("new_Answer");

            ((TextView) findViewById(R.id.Flash_Question)).setText(new_Question);
            ((TextView) findViewById(R.id.Flash_Answer)).setText(new_Answer);

       flashcardDatabase.insertCard(new Flashcard(new_Question, new_Answer));
            allFlashcards = flashcardDatabase.getAllCards();





        }



    }

}

