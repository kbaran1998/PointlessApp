package com.example.pointless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.IOException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static Language defaultLanguage = Language.ENGLISH;
    Button pointlessBtn;
    TextView pointlessText;
    String textForQuote;
    String buttonText;
    QuoteList listQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuotesListTool tool;
        try {
            tool = new QuotesListTool(getAssets().open("quotesDB.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            tool = new QuotesListTool(null);
        }
        Stack<Quote> quotesStack = tool.placeQuotesFromDBInStack();
        listQuotes = new QuoteList(quotesStack, tool);
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
            listQuotes.nextQuote();
            textForQuote = listQuotes.getCurrentQuote();
            pointlessText.setText(textForQuote);
        }
    }

}
