package com.example.pointless;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class QuoteList {
    private Stack<String> quotes;
    private List<String> usedQuotes;
    private String currentQuote;

    public QuoteList(Stack<String> quotes) {
        this.quotes = quotes;
        usedQuotes = new ArrayList<>();
        currentQuote =  quotes.pop();
        usedQuotes.add(currentQuote);
    }

    public String getCurrentQuote() {
        return currentQuote;
    }

    public void setCurrentQuote() {
        if (quotes.empty()) {
            Collections.shuffle(usedQuotes);
            int originalSize = usedQuotes.size();
            for (int i = 0; i < originalSize; i++) {
                quotes.push(usedQuotes.get(0));
                usedQuotes.remove(0);
            }
        }

        currentQuote = quotes.pop();
        usedQuotes.add(currentQuote);
    }

}
