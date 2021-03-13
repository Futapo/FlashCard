package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class added_flashcard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_flashcard);

        // cancel button
        ImageView Cancelbutton = findViewById(R.id.cancel_button);
            Cancelbutton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });


            //save button
         ImageView saveButton = findViewById(R.id.saveButton);
         saveButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 ((EditText) findViewById(R.id.Text_Question)).getText().toString();
                 ((EditText) findViewById(R.id.Text_Answer)).getText().toString();


                 Intent data = new Intent(); // create a new Intent, this is where we will put our data
                 data.putExtra("new_Question", ((EditText) findViewById(R.id.Text_Question)).getText().toString()); // Text_Question is changed unto the card
                 data.putExtra("new_Answer", ((EditText) findViewById(R.id.Text_Answer)).getText().toString()); // Text_Answer is changed unto the card
                 setResult(RESULT_OK, data); // set result code and bundle data for response
                 finish(); // closes this activity and pass data to the original activity that launched this activity



             }
         });





        }
    }
