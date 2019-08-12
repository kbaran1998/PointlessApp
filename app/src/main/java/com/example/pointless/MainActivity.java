package com.example.pointless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static Language defaultLanguage = Language.ENGLISH;
    Button pointlessBtn;
    TextView pointlessText;
    //int presses;
    String textForQuote;
    String buttonText;
    QuoteList listQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //presses = 0;
        QuotesListTool tool = new QuotesListTool("quotesDB.txt");
        listQuotes = new QuoteList(tool.placeQuotesFromDBInStack(), tool);
        buttonText = "Press Me";
        pointlessBtn = findViewById(R.id.pointlessButton);
        pointlessText = findViewById(R.id.pointlessText);
        textForQuote = listQuotes.getCurrentQuote();
        pointlessBtn.setText(buttonText);
        pointlessText.setText(textForQuote);
        pointlessBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        if (view.getId() == R.id.pointlessButton) {
//            presses++;
//            textForQuote = "Pointless: "+ presses +" times";
//            pointlessText.setText(textForQuote);
            listQuotes.nextQuote();
            textForQuote = listQuotes.getCurrentQuote();
        }
    }

}
