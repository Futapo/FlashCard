package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //simplifying "findViewById(R.id.nameOfView)
        TextView Answer = findViewById(R.id.Flash_Answer);
        TextView Question = findViewById(R.id.Flash_Question);
        ImageView Add = findViewById(R.id.add_Flash);

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

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, added_flashcard.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("new_Question"); //Pulls newQuestion and prints to screen when saved.
            String string2 = data.getExtras().getString("new_Answer");

            ((TextView) findViewById(R.id.Flash_Question)).setText(string1);
            ((TextView) findViewById(R.id.Flash_Answer)).setText(string2);







        }

    }



}

