package com.example.pointless;

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
    private final static Quote EMPTY_QUOTE_STACK = new Quote("Hello There!",
            Language.UNKNOWN);
    private Stack<Quote> quotesStack;
    private LinkedList<Quote> usedQuotes;
    private String currentQuote;
    private QuotesListTool tool;

    /**
     * A constructor for Quote.
     * @param quotes stack with quotes
     * @param tool helper for getting and formatting quotes
     */
    public QuoteList(Stack<Quote> quotes, QuotesListTool tool) {
        this.quotesStack = quotes;
        this.tool = tool;
        if (this.quotesStack.isEmpty()) {
            quotes.push(EMPTY_QUOTE_STACK);
        }
        usedQuotes = new LinkedList<>();
        currentQuote =  quotes.peek().getQuoteContent();
        usedQuotes.add(quotes.pop());
    }

    /**
     * Getter for the current quote in the stack.
     * @return current Quote
     */
    public String getCurrentQuote() {
        return currentQuote;
    }

    /**
     * Used Quotes list getter for mainly testing purposes.
     * @return linked list of used quotes.
     */
    public LinkedList<Quote> getUsedQuotes() {
        return usedQuotes;
    }

    /**
     * Getter for the Empty Stack Message.
     * @return string message
     */
    public String getEmptyQuoteStack() {
        return EMPTY_QUOTE_STACK.getQuoteContent();
    }

    /**
     * Setting the next quote to use. Once the stack is empty, list of used quotes is shuffled and
     * placed back to the stack.
     */
    public void nextQuote() {
        if (quotesStack.empty()) {
            usedQuotes = tool.shuffleQuotesLinkedList(usedQuotes);
            while(!usedQuotes.isEmpty()) {
                quotesStack.push(usedQuotes.poll());
            }
        }
        Quote thisQuote = quotesStack.pop();
        currentQuote = thisQuote.getQuoteContent();
        usedQuotes.add(thisQuote);
    }

}
