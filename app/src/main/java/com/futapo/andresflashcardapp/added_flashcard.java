package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class added_flashcard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_flashcard);


        // cancel button
        ImageView CancelButton = findViewById(R.id.cancel_button);
        CancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(added_flashcard.this, MainActivity.class);
                added_flashcard.this.startActivity(intent);
                finish();


            }
        });


        //save button
        ImageView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent data = new Intent(added_flashcard.this, MainActivity.class); // create a new Intent, this is where we will put our data
                data.putExtra("new_Question", ((EditText) findViewById(R.id.Text_Question)).getText().toString()); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("new_Answer", ((EditText) findViewById(R.id.Text_Answer)).getText().toString()); // puts another string into the Intent, with the key as 'string2
              // set result code and bundle data for response
                setResult(RESULT_OK, data);
                 // closes this activity and pass data to the original activity that launched this activity
                finish();


            }
        });

        //





    }





}