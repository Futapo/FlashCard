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

        String Q = getIntent().getStringExtra("flashKeyQ"); // this string will be 'harry potter`
        String A = getIntent().getStringExtra("flashKeyA"); // this string will be 'voldemort'

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

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("new_Question", ((EditText) findViewById(R.id.Text_Question)).getText().toString());
                data.putExtra("new_Answer", ((EditText) findViewById(R.id.Text_Answer)).getText().toString()); // Text_Answer is changed unto the card
                setResult(RESULT_OK, data);
                finish();


            }
        });


    }

}