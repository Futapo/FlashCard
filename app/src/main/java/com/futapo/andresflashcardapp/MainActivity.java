package com.futapo.andresflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //simplifying "findViewById(R.id.nameOfView)"
      TextView Answer =  findViewById(R.id.Flash_Answer);
      TextView Question =  findViewById(R.id.Flash_Question);

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

    }

}