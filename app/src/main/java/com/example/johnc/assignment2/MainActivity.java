//John Cetin 100955200 COMP3074 Assignment 2

package com.example.johnc.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startButton (View view){
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Start Accelerometer")){
            Intent intent = new Intent(this,ShakerActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
