package com.example.pointless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button pointlessBtn;
    TextView pointlessText;
    int presses;
    String textForBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presses = 0;
        pointlessBtn = findViewById(R.id.pointlessButton);
        pointlessText = findViewById(R.id.pointlessText);
        textForBtn = "Pointless: 0 times";
        pointlessText.setText(textForBtn);
        pointlessBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        if (view.getId() == R.id.pointlessButton) {
            presses++;
            textForBtn = "Pointless: "+ presses +" times";
            pointlessText.setText(textForBtn);
        }
    }

}
