package com.example.pointless;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * A data structure of quotes that will be used in the application. The QuoteList has a stack
 * of quotes, like a stack of cards, where you pop the quote that is on top of the stack and
 * place it in a LinkedList of used quotes which is shuffled after the stack is empty and put
 * back to the stack. That means that the likelihood of getting the same quote twice is small.
 * You can only get current quote.
 *
 * @author Krzysztof Baran
 */
public class QuoteList {
    private final static String EMPTY_QUOTE_STACK = "Hello There!";
    private Stack<String> quotesStack;
    private LinkedList<String> usedQuotes;
    private String currentQuote;

    /**
     * A constructor for Quote.
     * @param quotes stack with quotes
     */
    public QuoteList(Stack<String> quotes) {
        this.quotesStack = quotes;
        if (this.quotesStack.isEmpty()) {
            quotes.push(EMPTY_QUOTE_STACK);
        }
        usedQuotes = new LinkedList<>();
        currentQuote =  quotes.pop();
        usedQuotes.add(currentQuote);
    }

    /**
     * Getter for the current quote in the stack.
     * @return current Quote
     */
    public String getCurrentQuote() {
        return currentQuote;
    }

    /**
     * Setting the next quote to use. Once the stack is empty, list of used quotes is shuffled and
     * placed back to the stack.
     */
    public void nextQuote() {
        if (quotesStack.empty()) {
            Collections.shuffle(usedQuotes);
            while(!usedQuotes.isEmpty()) {
                quotesStack.push(usedQuotes.poll());
            }
        }

        currentQuote = quotesStack.pop();
        usedQuotes.add(currentQuote);
    }

}
